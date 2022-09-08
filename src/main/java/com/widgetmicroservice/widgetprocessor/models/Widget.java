package com.widgetmicroservice.widgetprocessor.models;

import com.widgetmicroservice.widgetprocessor.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Widget {
    @NonNull
    private Long id;

    @NonNull
    @NotEmpty
    @Size(min = 2, message = "name must have at least 2 characters")
    private String firstName;

    @NonNull
    @NotEmpty
    @Size(min = 2, message = "name must have at least 2 characters")
    private String lastName;

    @NonNull
    @Max(value = 150)
    private Integer age;

    @NonNull
    @Size(min = 4, message = "name must be MALE, FEMALE or OTHER")
    private Gender gender;

    @NonNull
    @Min(value = 1)
    private Double height;

    @NonNull
    @Min(value = 1)
    private Double weight;
}

