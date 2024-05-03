package com.javadoubts.core.servlets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/ks/TextToSpeech"
)
public class TextToSpeechServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");

        if (text != null && !text.isEmpty()) {
            // Call OpenAI TTS API
            String apiKey = "d65343a67fmsh3984b6954167bf6p18a119jsne17941d2da2e";
            String openAIEndpoint = "https://open-ai-text-to-speech1.p.rapidapi.com/";

            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(openAIEndpoint);
            httpPost.addHeader("X-RapidAPI-Key",  apiKey);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("X-RapidAPI-Host", "open-ai-text-to-speech1.p.rapidapi.com");

            // Set request body
            StringEntity entity = new StringEntity("{\"text\": \"" + text + "\"}");
            httpPost.setEntity(entity);

            // Send request
            HttpResponse apiResponse = httpClient.execute(httpPost);

            // Process response
            if (apiResponse.getStatusLine().getStatusCode() == 200) {
                byte[] audioBytes = IOUtils.toByteArray(apiResponse.getEntity().getContent());
                response.setContentType("audio/mpeg");
                response.getOutputStream().write(audioBytes);
            } else {
                // Handle error
                response.setStatus(apiResponse.getStatusLine().getStatusCode());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}