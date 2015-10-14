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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * An object oriented HTTP request representation.
 */
public class Request {

    public String method;
    public String uri;
    public String version;
    public Map<String, String> headers = new HashMap<>();
    public String body;

    // Initialize a new request with values.
    public Request(String method, String uri, String version, String body) {
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.body = body;
    }

    // Initialize a new request from an input stream.
    //
    // This will consume the input stream.
    // This means you can't reread the reaquest data from the stream after calling this method.
    public Request(BufferedReader in) throws Exception {
        // Parse request line
        String line = in.readLine();
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new Exception("Malformed request line '" + line + "'");
        }
        this.method = parts[0];
        this.uri = parts[1];
        this.version = parts[2];
        // Parse headers
        while (in.ready()) {
            line = in.readLine();
            if (line.isEmpty()) {
                break; // we have read all the header
            }
            parts = line.split(": ");
            if (parts.length != 2) {
                throw new Exception("Malformed header line '" + line + "'");
            }
            this.headers.put(parts[0], parts[1]);
        }
        // Parse body
        StringBuilder bodyStr = new StringBuilder();
        while (in.ready()) {
            line = in.readLine();
            bodyStr.append(line);
            bodyStr.append("\n");
        }
        if (bodyStr.length() > 0) {
            this.body = bodyStr.toString();
        }
    }

    // Does this request has a body?
    public Boolean hasBody() {
        return body != null;
    }

    // Return the first line of the request in string format
    private String getRequestLine() {
        return method + " " + uri + " " + version;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getRequestLine());
        str.append("\r\n");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            str.append(entry.getKey());
            str.append(": ");
            str.append(entry.getValue());
            str.append("\r\n");
        }
        if (hasBody()) {
            str.append("\r\n");
            str.append(body);
            str.append("\r\n");
        }
        return str.toString();
    }

    // Send this response on an output stream.
    public void send(PrintWriter out) {
        out.append(toString());
        out.flush();
    }
}
