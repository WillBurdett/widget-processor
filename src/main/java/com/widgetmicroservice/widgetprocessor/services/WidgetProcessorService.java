package com.widgetmicroservice.widgetprocessor.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgetmicroservice.widgetprocessor.enums.Gender;
import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Scanner;

@Service
public class WidgetProcessorService {
    public void processWidget(Long id) throws Exception {
        try {
            URL url = new URL("http://localhost:8080/widgets/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // check response code
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                sendProcessedWidget(parse(informationString.toString(), id));
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public static Integer calculateBmr(Widget widget){
        Double calc = 10 * widget.getWeight() + 6.25 * widget.getHeight() - 5 * widget.getAge();
        return widget.getGender() == Gender.MALE ? Integer.parseInt(String.valueOf(Math.round(calc + 5))) : Integer.parseInt(String.valueOf(Math.round(calc - 16)));
    }

    public static ProcessedWidget parse(String responseBody, Long id){
        JSONObject widgetJson = new JSONObject((responseBody));
        return createProcessedWidget(new Widget(id, widgetJson.getString("firstName"), widgetJson.getString("lastName"), widgetJson.getInt("age"), Enum.valueOf(Gender.class, widgetJson.getString("gender")), widgetJson.getDouble("height"), widgetJson.getDouble("weight")));
    }

    public static ProcessedWidget createProcessedWidget(Widget widget){
        return new ProcessedWidget(widget.getId(), widget.getFirstName(), widget.getLastName(), widget.getAge(), widget.getGender(), widget.getHeight(), widget.getWeight(), calculateBmr(widget));
    }

    private void sendProcessedWidget(ProcessedWidget processedWidget) throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("id", processedWidget.getId().toString());
            put("firstName", processedWidget.getFirstName());
            put("lastName", processedWidget.getLastName());
            put("age", processedWidget.getAge().toString());
            put("gender", processedWidget.getGender().toString());
            put("height", processedWidget.getHeight().toString());
            put("weight", processedWidget.getWeight().toString());
            put("bmr", processedWidget.getBmr().toString());
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .uri(URI.create("http://localhost:8082/summary"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
