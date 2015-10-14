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
package inf4375.json.exercice6.http;

/**
 * A Controller that matches a Request URI using a regular expression.
 *
 * You should subclass this if you want your route to be executed given a
 * certain URI pattern.
 */
public abstract class UriMatchController implements Controller {

    // Regex used to match request URIs.
    public String uriMatch;

    @Override
    public Boolean accept(Request request) {
        return request.uri.matches(uriMatch);
    }
}
