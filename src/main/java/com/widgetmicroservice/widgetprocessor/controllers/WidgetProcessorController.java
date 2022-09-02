package com.widgetmicroservice.widgetprocessor.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "processor")
public class WidgetProcessorController {

    @RequestMapping(method = RequestMethod.POST, path = "/{num}")
    private String helloWorld(@PathVariable Long num){
        return "Hello number " + num;
    }
}
