package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/kds/TestServlet"
)
public class TestServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    private static String SECRET_KEY = "";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        StringBuffer responseValue=null;
        try {
            URL obj = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                responseValue = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseValue.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(responseValue.toString());
            } else {
                System.out.println("GET request did not work.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String payload = responseValue.toString();

        // Encrypt the payload
        String encryptedPayload = null;
        try {
            encryptedPayload = encryptPayload(payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOG.info("Encrypted Payload: " + encryptedPayload);

        // Decrypt the encrypted payload
        String decryptedPayload = null;
        try {
            decryptedPayload = decryptPayload(encryptedPayload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOG.info("Decrypted Payload: " + decryptedPayload);
    }

    public static String encryptPayload(String payload) throws Exception {
        SECRET_KEY=secretKey();
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(payload.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptPayload(String encryptedPayload) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPayload));
        return new String(decryptedBytes);
    }

    public static String secretKey(){
        StringBuilder keyBuilder=null;
        try {
            // Generate a secure random key
            SecureRandom secureRandom = new SecureRandom();
            byte[] keyBytes = new byte[16]; // 16 bytes for AES-128, 24 bytes for AES-192, 32 bytes for AES-256
            secureRandom.nextBytes(keyBytes);

            // Convert the key bytes to a hexadecimal string
            keyBuilder = new StringBuilder();
            for (byte b : keyBytes) {
                keyBuilder.append(String.format("%02x", b));
            }

            // Print the generated secret key
            LOG.info("Generated Secret Key: " + keyBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyBuilder.toString();
    }
}
