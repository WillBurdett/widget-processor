package com.widgetmicroservice.widgetprocessor.unittests;

import com.widgetmicroservice.widgetprocessor.controllers.WidgetProcessorController;
import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(WidgetProcessorController.class)
public class WidgetProcessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetProcessorService widgetProcessorService;

    @Test
    public void processWidget_HappyPath() throws Exception {
        // given
        Long id = 1L;
        // when
        mockMvc.perform(post("/processor/" + id));
        // then
        verify(widgetProcessorService, times(1)).processWidget(id);
    }



}