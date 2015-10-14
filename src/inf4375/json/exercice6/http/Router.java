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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * A HTTP Router that use controllers to respond to requests.
 */
public class Router {

    // Port to listen.
    public Integer port;

    // List of controllers that can be executed.
    public List<Controller> controllers = new ArrayList<>();

    public Router(Integer port) {
        this.port = port;
    }

    private BufferedReader in;
    private PrintWriter out;

    // Start listening
    //
    // This will start an infinite listening loop and you have no power to stop it!
    public void start() {
        System.out.println("Listening on port " + port + "...");
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                try (Socket socket = server.accept()) {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    Request request = null;
                    try {
                        // New request received
                        request = new Request(in);
                    } catch (Exception ex) {
                        System.err.println("Warning: malformed request");
                        ex.printStackTrace(System.err);
                        continue;
                    }
                    // Try to find a route that matches the request
                    Boolean match = false;
                    for (Controller controller : controllers) {
                        if (controller.accept(request)) {
                            controller.action(this, request);
                            match = true;
                            break;
                        }
                    }
                    // No route found, send 404
                    if (!match) {
                        sendError(404, "Resource not found");
                    }
                    
                    in = null;
                    out = null;
                } catch (Exception ex) {
                    System.err.println("Warning: Cannot accept connection");
                    ex.printStackTrace(System.err);
                }
            }
        } catch (IOException ex) {
            System.err.println("Error: Cannot open server socket on port " + port);
            System.err.println(ex);
        }
    }

    // Send a HTTP response.
    public void sendResponse(Integer status, String reason, String body) {
        Response response = new Response("HTTP/1.1", status, reason, body);
        response.headers.put("Content-Type", "text/html");
        response.send(out);
    }

    // Send an HTTP response containing an error message formated with HTML.
    public void sendError(Integer status, String reason) {
        String body = "<h1>" + status + ": " + reason + "</h1>";
        sendResponse(status, reason, body);
    }

    // Send a HTTP response in JSON format.
    public void sendJsonResponse(Integer status, String reason, JsonObject body) {
        sendJsonResponse(status, reason, body.toString());
    }

    // Send a HTTP response in JSON format.
    public void sendJsonResponse(Integer status, String reason, JsonArray body) {
        sendJsonResponse(status, reason, body.toString());
    }

    // Send a HTTP response in JSON format.
    public void sendJsonResponse(Integer status, String reason, String body) {
        Response response = new Response("HTTP/1.1", status, reason, body);
        response.headers.put("Content-Type", "text/json");
        response.send(out);
    }

    // Send an HTTP response containing an error message formated with JSON.
    public void sendJsonError(Integer status, String reason) {
        JsonObjectBuilder body = Json.createObjectBuilder();
        body.add("error", reason);
        sendJsonResponse(status, reason, body.build());
    }
}
