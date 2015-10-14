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
package inf4375.json.exercice6.controllers;

import inf4375.json.exercice6.http.Request;
import inf4375.json.exercice6.http.Router;
import inf4375.json.exercice6.http.UriMatchController;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * A Controller that responds with a JSON *Hello World*.
 */
public class HelloJsonController extends UriMatchController {

    public HelloJsonController() {
        this.uriMatch = "^/json/?$";
    }

    @Override
    public void action(Router router, Request request) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("message", "Hello World!");
        router.sendJsonResponse(200, "OK", builder.build());
    }
}
