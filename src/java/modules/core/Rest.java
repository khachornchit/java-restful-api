/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class Rest {

    private static URL getUrl(String UrlString) throws MalformedURLException, URISyntaxException {
        URL url = new URL(UrlString);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        return uri.toURL();
    }

    public static HttpURLConnection getRequestJson(String name, String password, String urlstring) throws IOException {
        try {
            URL url = getUrl(urlstring);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            String authString = name + ":" + password;
            String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes("utf-8"));
            String basicAuth = "Basic " + authStringEnc;
            request.setRequestProperty("Authorization", basicAuth);
            request.setRequestMethod("GET");
            request.setRequestProperty("Accept", "application/json");
            return request;

        } catch (URISyntaxException | IOException e) {
            return null;
        }
    }

    public static HttpURLConnection getRequestTextCsv(String name, String password, String urlstring) throws IOException {
        try {
            URL url = getUrl(urlstring);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            String authString = name + ":" + password;
            String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes("utf-8"));
            String basicAuth = "Basic " + authStringEnc;
            request.setRequestProperty("Authorization", basicAuth);
            request.setRequestMethod("GET");
            request.setRequestProperty("Accept", "text/csv");
            return request;

        } catch (URISyntaxException | IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static BufferedReader getResponse(String name, String password, String urlstring) throws IOException {
        try {
            HttpURLConnection request = getRequestJson(name, password, urlstring);

            if (request.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : " + request.getResponseCode());
            } else {
                BufferedReader response = new BufferedReader(new InputStreamReader((request.getInputStream())));
                return response;
            }
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static JsonObject getJson(String name, String password, String urlstring) throws IOException {
        try {
            HttpURLConnection request = getRequestJson(name, password, urlstring);

            if (request.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : " + request.getResponseCode());
            } else {
                BufferedReader response = new BufferedReader(new InputStreamReader((request.getInputStream())));
                JsonObject json;
                try (JsonReader reader = Json.createReader(new StringReader(response.readLine()))) {
                    json = reader.readObject();
                }
                return json;
            }
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static HttpURLConnection getTextCsv(String name, String password, String urlstring) throws IOException {
        try {
            HttpURLConnection request = getRequestTextCsv(name, password, urlstring);
            if (request.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : " + request.getResponseCode());
            } else {
                return request;
            }
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
