package mywebapp;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MyWebAppTest {

    @Before
    public void init() throws IOException {

    }

    @Test
    public void testLCSSuccessScenario() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/lcs");
        String JSON_STRING="{\n" +
                "\"setOfStrings\": [\n" +
                "{\"value\": \"comcast\"},\n" +
                "{\"value\": \"comcastic\"},\n" +
                "{\"value\": \"broadcaster\"}\n" +
                "]\n" +
                "}";
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        assertEquals(EntityUtils.toString(response2.getEntity(), "UTF-8"), "{\"lcs\":{\"value\":\"cast\"}}");
        assertEquals(response2.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void testLCSEmptyList() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/lcs");
        String JSON_STRING="{\n" +
                "\"setOfStrings\": [\n" +
                "]\n" +
                "}";
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        assertEquals(response2.getStatusLine().getStatusCode(), 400);
    }

    @Test
    public void testLCSInvalidStringException() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/lcs");
        String JSON_STRING="}";
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        assertEquals(response2.getStatusLine().getStatusCode(), 400);
    }
}
