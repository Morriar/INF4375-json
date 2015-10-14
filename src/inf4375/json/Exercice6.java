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

import inf4375.json.exercice6.controllers.AlbumsController;
import inf4375.json.exercice6.controllers.HelloController;
import inf4375.json.exercice6.controllers.HelloJsonController;
import inf4375.json.exercice6.http.Router;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

/**
 * Example of REST service based on JSON.
 */
public class Exercice6 {

    public static void main(String[] args) {
        File jsonFile = new File("json/catalog.json");
        try (FileInputStream inputStream = new FileInputStream(jsonFile)) {
            JsonReader reader = Json.createReader(inputStream);
            JsonArray catalog = reader.readArray();

            Router router = new Router(8080);
            router.controllers.add(new HelloController());
            router.controllers.add(new HelloJsonController());
            router.controllers.add(new AlbumsController(catalog));
            router.start();

        } catch (IOException ex) {
            System.err.println("Error: unable to load catalog at " + jsonFile);
            System.err.println(ex.getMessage());
        }
    }
}
