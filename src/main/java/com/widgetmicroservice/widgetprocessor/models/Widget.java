package com.widgetmicroservice.widgetprocessor.models;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Widget {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private Double height;
    private Double weight;
}

