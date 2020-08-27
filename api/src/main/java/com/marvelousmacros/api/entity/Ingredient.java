package com.marvelousmacros.api.entity;

import com.marvelousmacros.api.constants.MeasurementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private MeasurementType measurementType;

    // intended for use with ingredients that can be weighed on a continuous scale
    private long proteinConversionFactor;
    private long carbConversionFactor;
    private long fatConversionFactor;

    // intended for use with ingredients that are discrete in nature, eg. a package of yogurt is likely not measured by weight
    private int discreteProteins;
    private int discreteCarbs;
    private int discreteFats;
    private int discreteCalories;
}
