package com.widgetmicroservice.widgetprocessor.models;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedWidget {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private Double height;
    private Double weight;
    private Integer bmr;
}
