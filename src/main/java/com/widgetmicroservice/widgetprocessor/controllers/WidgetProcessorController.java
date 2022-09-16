package com.widgetmicroservice.widgetprocessor.controllers;

import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
