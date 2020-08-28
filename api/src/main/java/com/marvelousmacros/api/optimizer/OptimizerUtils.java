package com.marvelousmacros.api.optimizer;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OptimizerUtils {
    private static final Random random = new Random();

    public static int randomInt(int upperBound) {
        return random.nextInt(upperBound);
    }
}
