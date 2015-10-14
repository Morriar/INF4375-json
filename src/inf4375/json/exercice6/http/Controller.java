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
 * A Controller executes an `action` when it matches a Request.
 */
public interface Controller {

    // Does this Route accept to respond to this request?
    public abstract Boolean accept(Request request);

    // Action to be executed when this Route matches a Request.
    public abstract void action(Router router, Request request);
}
