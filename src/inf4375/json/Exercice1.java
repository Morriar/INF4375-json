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
import javax.json.JsonReader;

/**
 * This example shows how to load a JSON document from a file using the
 * javax.json library.
 */
public class Exercice1 {

    public static void main(String[] args) {
        File jsonFile = new File("json/catalog.json");
        try (FileInputStream inputStream = new FileInputStream(jsonFile)) {
            JsonReader reader = Json.createReader(inputStream);
            JsonArray catalog = reader.readArray();
            System.out.println("Il y a " + catalog.size() + " album(s) dans catalogue.");
        } catch (IOException ex) {
            System.err.println("Erreur: impossible de charger le fichier JSON " + jsonFile);
            System.err.println(ex.getMessage());
        }

    }
}
