package com.widgetmicroservice.widgetprocessor.unittests;

import com.widgetmicroservice.widgetprocessor.controllers.WidgetProcessorController;
import com.widgetmicroservice.widgetprocessor.enums.Gender;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(WidgetProcessorController.class)
class WidgetProcessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetProcessorService widgetProcessorService;

    @Test
    public void processWidget(){
//        // given
//        Widget expected = new Widget(1L,"Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
//        // when
//        mockMvc.perform(post("/processor/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(expected)));
//        // then
//        verify(widgetService, times(1)).addWidget(expected);
    }



}