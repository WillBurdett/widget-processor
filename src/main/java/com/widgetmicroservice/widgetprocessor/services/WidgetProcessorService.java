package com.widgetmicroservice.widgetprocessor.services;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import com.widgetmicroservice.widgetprocessor.util.FeignServiceUtil;
import com.widgetmicroservice.widgetprocessor.util.FeignSummaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WidgetProcessorService {


    private final FeignServiceUtil feignServiceUtil;

    private final FeignSummaryUtil feignSummaryUtil;

    @Autowired
    public WidgetProcessorService(FeignServiceUtil feignServiceUtil, FeignSummaryUtil feignSummaryUtil) throws Exception {
        this.feignServiceUtil = feignServiceUtil;
        this.feignSummaryUtil = feignSummaryUtil;
    }

    public void processWidget(Long id) throws Exception {
        try {
           feignSummaryUtil.sendProcessedWidgetToSummary(createProcessedWidget(feignServiceUtil.getWidgetById(id), id));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public static Integer calculateBmr(Widget widget){
        Double calc = (10 * widget.getWeight()) + (6.25 * widget.getHeight()) - (5 * widget.getAge());
        return widget.getGender() == Gender.MALE ? Integer.parseInt(String.valueOf(Math.round(calc + 5))) : Integer.parseInt(String.valueOf(Math.round(calc - 161)));
    }

    public static ProcessedWidget createProcessedWidget(Widget widget, Long id){
        return new ProcessedWidget(id, widget.getFirstName(), widget.getLastName(), widget.getAge(), widget.getGender(), widget.getHeight(), widget.getWeight(), calculateBmr(widget));
    }
}
