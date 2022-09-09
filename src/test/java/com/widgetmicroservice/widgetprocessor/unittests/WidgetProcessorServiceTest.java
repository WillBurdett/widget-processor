package com.widgetmicroservice.widgetprocessor.unittests;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import com.widgetmicroservice.widgetprocessor.models.Widget;
import com.widgetmicroservice.widgetprocessor.services.WidgetProcessorService;
import com.widgetmicroservice.widgetprocessor.util.FeignServiceUtil;
import com.widgetmicroservice.widgetprocessor.util.FeignSummaryUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(WidgetProcessorService.class)
class WidgetProcessorServiceTest {

    @Autowired
    WidgetProcessorService undertest;

    @MockBean
    FeignSummaryUtil feignSummaryUtil;

    @MockBean
    FeignServiceUtil feignServiceUtil;

    @Test
    void calculateBmr_MaleMifflinStJeorEquation() {
        // given
        Widget widget = new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        // when
        Integer actual = undertest.calculateBmr(widget);
        Integer expected = 1643;
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateBmr_FemaleMifflinStJeorEquation() {
        // given
        Widget widget = new Widget(1L, "Sally", "Smith", 20, Gender.FEMALE, 150.0, 80.0);
        // when
        Integer actual = undertest.calculateBmr(widget);
        Integer expected = 1477;
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createProcessedWidget() {
    }
}