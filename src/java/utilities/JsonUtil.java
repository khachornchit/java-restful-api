/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author kajornjit.songsaen
 */
public class JsonUtil {

    public static JsonObject ReadObjectToJsonObject(JsonValue value) {
        JsonReader reader = Json.createReader(new StringReader(value.toString()));
        JsonObject obj = reader.readObject();
        reader.close();
        return obj;
    }
    
    public static JsonObject ReadDataToJsonObject(String data) {
        JsonReader reader = Json.createReader(new StringReader(data));
        JsonObject obj = reader.readObject();
        reader.close();
        return obj;
    }
}
