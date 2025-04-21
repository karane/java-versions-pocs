package org.karane;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // var
        var names = List.of("Alice", "Bob", "Charlie");

        for (var name : names) {
            System.out.println(name);
        }

        // New String methods
        var str = "   \n   ";
        System.out.println("Is blank: " + str.isBlank());

        // HttpClient
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://httpbin.org/get"))
            .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response.body());
    }
}
