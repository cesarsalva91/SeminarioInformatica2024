import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class MailgunEmailSender {

    private static final String API_KEY = "899b886bd8cee164b847c5d0da843203-79295dd0-84dad4ef"; // clave API
    private static final String DOMINIO = "sandbox95fb81138d6b4c80912df734f11cfa0d.mailgun.org"; // dominio

    public static String sendSimpleMessage(String to, String subject, String text) throws Exception {
        String postData =  "from=ClassRoom <direccion@" + DOMINIO + ">&" +
                          "to=" + to +
                          "&subject=" + subject +
                          "&text=" + text;
        return sendPostRequest(postData);
    }

    private static String sendPostRequest(String postData) throws Exception {
        URL url = new URL("https://api.mailgun.net/v3/" + DOMINIO + "/messages");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(("api:" + API_KEY).getBytes()));
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(postData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    private static String readResponse(HttpURLConnection connection) throws Exception {
        StringBuilder response = new StringBuilder();
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } else {
            throw new Exception("Error en la solicitud: " + responseCode);
        }

        return response.toString();
    }
}