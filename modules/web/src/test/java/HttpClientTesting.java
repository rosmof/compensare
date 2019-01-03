import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpClientTesting {
    @Test
    public void resultJsonTest() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8083/home");
        try (CloseableHttpResponse response = client.execute(httpget)) {
            String reply = getResponse(response);

            if (StringUtils.contains("Login Page", "UTF-8")) {

            }

        }
    }

    private String getResponse(HttpResponse response) {
        HttpEntity body = response.getEntity();
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[16];
        int k = 0;
        try (InputStreamReader reader = new InputStreamReader(body.getContent(), StandardCharsets.UTF_8)) {
            while ((k = reader.read(buff)) > 0) {
                sb.append(buff);
            }
        } catch (IOException e) {
            System.out.println("failed to read response from server");
        }

        return sb.toString();
    }
}