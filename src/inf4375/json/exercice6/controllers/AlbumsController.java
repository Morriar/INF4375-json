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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * A Controller that displays the catalog in JSON format.
 */
public class AlbumsController extends UriMatchController {

    // Json array used to store the albums list.
    JsonArray catalog;

    public AlbumsController(JsonArray catalog) {
        this.uriMatch = "^/albums/?.*";
        this.catalog = catalog;
    }

    @Override
    public void action(Router router, Request request) {
        String uri = request.uri;
        if (uri.matches("^/albums/?$")) {
            actionListAlbums(router);
            return;
        } else if (uri.matches("^/albums/(\\d)$")) {
            Pattern pattern = Pattern.compile("(\\d)");
            Matcher matcher = pattern.matcher(uri);
            if (matcher.find()) {
                actionShowAlbum(router, matcher.group(1));
                return;
            }
        }
        router.sendJsonError(400, "Bad request");
    }

    // Display in stock albums as a JsonArray.
    private void actionListAlbums(Router router) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (JsonObject album : catalog.getValuesAs(JsonObject.class)) {
            if (album.getBoolean("instock")) {
                builder.add(album);
            }
        }
        router.sendJsonResponse(200, "OK", builder.build());
    }

    // Display the album with `id` as a JsonObject.
    private void actionShowAlbum(Router router, String id) {
        for (JsonObject album : catalog.getValuesAs(JsonObject.class)) {
            if (album.getString("id").equals(id)) {
                router.sendJsonResponse(200, "OK", album);
                return;
            }
        }
        router.sendJsonError(404, "Not found");
    }
}
