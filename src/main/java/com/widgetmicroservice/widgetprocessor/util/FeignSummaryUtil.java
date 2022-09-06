package com.widgetmicroservice.widgetprocessor.util;

import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "widget-summary-feign", url = "http://localhost:8082/summary")
public interface FeignSummaryUtil {

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    void sendProcessedWidgetToSummary(@RequestBody ProcessedWidget processedWidget);
}
