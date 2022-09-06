package com.widgetmicroservice.widgetprocessor.util;

import com.widgetmicroservice.widgetprocessor.models.Widget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "widget-service-feign", url = "http://localhost:8080/widgets")
public interface FeignServiceUtil {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    Widget getWidgetById(@PathVariable("id") Long id);
}
