package com.widgetmicroservice.widgetprocessor.controllers;

import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping(path = "processor")
public class WidgetProcessorController {

    private final WidgetProcessorService widgetProcessorService;

    @Autowired
    public WidgetProcessorController(WidgetProcessorService widgetProcessorService) {
        this.widgetProcessorService = widgetProcessorService;
    }

    @RequestMapping(method = RequestMethod.POST)
    private ProcessedWidget helloWorld(@RequestBody Widget widget){
        return widgetProcessorService.processWidget(widget);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    private void getWidgetById(@PathVariable Long id){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/widgets/"+id)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
