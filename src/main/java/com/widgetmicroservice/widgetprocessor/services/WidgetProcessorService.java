package com.widgetmicroservice.widgetprocessor.services;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class WidgetProcessorService {
    public ProcessedWidget processWidget(Long id) throws Exception {
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

                return parse(informationString.toString(), id);
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
}
