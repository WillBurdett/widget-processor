package com.widgetmicroservice.widgetprocessor.controllers;

import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
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

    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    private void processWidget(@PathVariable Long id) throws Exception {
        widgetProcessorService.processWidget(id);
    }
}
