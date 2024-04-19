package com.javadoubts.core.servlets;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/texttospeech"
)
public class TextToSpeechServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        if (text == null || text.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Replace this with your Rapid API endpoint
        String rapidApiEndpoint = "https://text-to-speech-multiple-languages-voices1.p.rapidapi.com/voice_type/Studio/convert";

        // Replace 'apiKey' with your Rapid API key
        String apiKey = "d65343a67fmsh3984b6954167bf6p18a119jsne17941d2da2e";

        try {
            URL url = new URL(rapidApiEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-RapidAPI-Key", apiKey);
            connection.setDoOutput(true);

            String requestBody = "{\n" +
                    "\t\tvoice_name: 'en-US-Studio-M',\n" +
                    "\t\ttext: 'Hello world!'\n" +
                    "\t}";
            connection.getOutputStream().write(requestBody.getBytes());

            InputStream inputStream = connection.getInputStream();
            byte[] audioBytes = IOUtils.toByteArray(inputStream);

            response.setContentType("audio/mpeg");
            response.setContentLength(audioBytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(audioBytes);
            outputStream.flush();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(response.getWriter());
        }
    }
}