package com.widgetmicroservice.widgetprocessor.controllers;

import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
