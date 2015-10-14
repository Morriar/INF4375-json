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

/**
 * A Controller that responds with a HTML *Hello World*.
 */
public class HelloController extends UriMatchController {

    public HelloController() {
        this.uriMatch = "^/hello/?$";
    }

    @Override
    public void action(Router router, Request request) {
        StringBuilder html = new StringBuilder();
        html.append("<h1>Hello World!</h1>");
        html.append("<p>You sent a ");
        html.append(request.method);
        html.append(" request.</p>");
        router.sendResponse(200, "OK", html.toString());
    }
}
