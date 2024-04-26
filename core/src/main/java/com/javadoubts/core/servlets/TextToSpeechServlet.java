package com.javadoubts.core.servlets;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        value = "/bin/kds/texttospeech"
)
public class TextToSpeechServlet extends SlingAllMethodsServlet {

    private Logger logger = LoggerFactory.getLogger(TextToSpeechServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String textValue = request
                .getParameter("textValue");
        if (textValue == null || textValue.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Replace this with your Rapid API endpoint
        String rapidApiEndpoint = "https://text-to-speech67.p.rapidapi.com/tts";

        // Replace 'apiKey' with your Rapid API key
        String apiKey = "d65343a67fmsh3984b6954167bf6p18a119jsne17941d2da2e";

        try {
            URL url = new URL(rapidApiEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
            connection.setRequestProperty("X-RapidAPI-Key", apiKey);
            connection.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("text",textValue);
            jsonObject.put("voice","en-GB-SoniaNeural");
            String requestBody = jsonObject.toString();
            logger.info("payload Value Check:{}",requestBody);
            connection.getOutputStream().write(requestBody.getBytes());

            InputStream inputStream = connection.getInputStream();
            byte[] audioBytes = IOUtils.toByteArray(inputStream);

            response.setContentType("audio/mpeg");
            response.setContentLength(audioBytes.length);

            ServletOutputStream outputStream = response.getOutputStream();
            logger.info("output data{}",audioBytes);
            outputStream.write(audioBytes);
            outputStream.flush();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(response.getWriter());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}