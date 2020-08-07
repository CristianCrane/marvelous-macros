package com.marvelousmacros.api.entity;

import com.marvelousmacros.api.constants.FitnessGoal;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private int targetCalories;
    private int targetProteins;
    private int targetCarbs;
    private int targetFats;
    private FitnessGoal fitnessGoal;
    private int weightInLbs;
    private int age;
}
