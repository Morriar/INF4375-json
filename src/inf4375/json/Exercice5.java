/*
 * Copyright 2015 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package inf4375.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 * This example shows how to create a JSON document from another one.
 */
public class Exercice5 {

    public static void main(String[] args) {
        File jsonFile = new File("json/catalog.json");
        try (FileInputStream inputStream = new FileInputStream(jsonFile)) {
            JsonReader reader = Json.createReader(inputStream);
            JsonArray catalog = reader.readArray();

            JsonObjectBuilder commandBuilder = Json.createObjectBuilder();
            commandBuilder.add("id", "123456789");
            commandBuilder.add("date", "11/11/2011");
            commandBuilder.add("validated", true);

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            double total = 0.0;
            for (JsonObject album : catalog.getValuesAs(JsonObject.class)) {
                double price = album.getJsonNumber("price").doubleValue();
                if(price >= 10.0) {
                    continue;
                }
                total += price;
                arrayBuilder.add(album);
            }

            commandBuilder.add("albums", arrayBuilder.build());
            commandBuilder.add("total", total);
            JsonObject command = commandBuilder.build();
            System.out.println(command.toString());

        } catch (IOException ex) {
            System.err.println("Erreur: impossible de charger le fichier JSON " + jsonFile);
            System.err.println(ex.getMessage());
        }

    }
}
