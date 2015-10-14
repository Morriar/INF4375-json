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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * This example shows how to create a JsonObject from scratch.
 */
public class Exercice4 {

    public static void main(String[] args) {
        JsonObjectBuilder commandBuilder = Json.createObjectBuilder();
        commandBuilder.add("id", "123456789");
        commandBuilder.add("total", 9.9);
        commandBuilder.add("date", "11/11/2011");
        commandBuilder.add("validated", true);

        JsonObjectBuilder albumBuilder = Json.createObjectBuilder();
        albumBuilder.add("id", "1");
        albumBuilder.add("title", "Hide your heart");

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        arrayBuilder.add(albumBuilder.build());

        commandBuilder.add("albums", arrayBuilder.build());

        JsonObject command = commandBuilder.build();
        System.out.println(command.toString());
    }
}
