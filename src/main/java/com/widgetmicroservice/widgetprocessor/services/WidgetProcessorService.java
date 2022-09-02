package com.widgetmicroservice.widgetprocessor.services;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import com.widgetmicroservice.widgetprocessor.models.ProcessedWidget;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import org.springframework.stereotype.Service;

@Service
public class WidgetProcessorService {
    public ProcessedWidget processWidget(Widget widget) {
        return new ProcessedWidget(widget.getId(), widget.getFirstName(), widget.getLastName(), widget.getAge(), widget.getGender(), widget.getHeight(), widget.getWeight(), calculateBmr(widget));
    }
    public Integer calculateBmr(Widget widget){
        Double calc = 10 * widget.getWeight() + 6.25 * widget.getHeight() - 5 * widget.getAge();
        return widget.getGender() == Gender.MALE ? Integer.parseInt(String.valueOf(Math.round(calc + 5))) : Integer.parseInt(String.valueOf(Math.round(calc - 16)));
    }  
}
