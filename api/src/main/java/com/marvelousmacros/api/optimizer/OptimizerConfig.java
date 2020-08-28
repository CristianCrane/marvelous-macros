package com.marvelousmacros.api.optimizer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class OptimizerConfig {
    private int generationLimit = 300;
    private int populationSize = 300;
    private double mutationRate = .01;
}
