/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.reader;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author kajornjit.songsaen
 */
public class SampleJson {

    public void SampleJsonReader() {
        String personJSONData
                = "  {"
                + "   \"name\": \"Jack\", "
                + "   \"age\" : 13, "
                + "   \"isMarried\" : false, "
                + "   \"address\": { "
                + "     \"street\": \"#1234, Main Street\", "
                + "     \"zipCode\": \"123456\" "
                + "   }, "
                + "   \"phoneNumbers\": [\"011-111-1111\", \"11-111-1111\"] "
                + " }";

        JsonReader reader = Json.createReader(new StringReader(personJSONData));
        JsonObject personObject = reader.readObject();

        reader.close();

        System.out.println("Name   : " + personObject.getString("name"));
        System.out.println("Age    : " + personObject.getInt("age"));
        System.out.println("Married: " + personObject.getBoolean("isMarried"));

        JsonObject addressObject = personObject.getJsonObject("address");
        System.out.println("Address: ");
        System.out.println(addressObject.getString("street"));
        System.out.println(addressObject.getString("zipCode"));

        System.out.println("Phone  : ");
        JsonArray phoneNumbersArray = personObject.getJsonArray("phoneNumbers");
        for (JsonValue jsonValue : phoneNumbersArray) {
            System.out.println(jsonValue.toString());
        }
    }

    public void SampleReadFullMessage() {
        String personJSONData = "{\"type\":\"error handling\",\"action\":\"mail\",\"cycle\":\"second\",\"applicantid\"\":null,\"successful\":\"false\"}";

        JsonReader reader = Json.createReader(new StringReader(personJSONData));
        JsonObject personObject = reader.readObject();

        reader.close();

        System.out.println("Type: " + personObject.getString("type"));
    }

    public void SampleJsonBuilder() {
        JsonObject personObject = Json.createObjectBuilder()
                .add("name", "John")
                .add("age", 13)
                .add("isMarried", false)
                .add("address",
                        Json.createObjectBuilder().add("street", "Main Street")
                        .add("city", "New York")
                        .add("zipCode", "11111")
                        .build()
                )
                .add("phoneNumber",
                        Json.createArrayBuilder().add("00-000-0000")
                        .add("11-111-1111")
                        .add("11-111-1112")
                        .build()
                )
                .build();

        System.out.println("Object: " + personObject);
    }
}
