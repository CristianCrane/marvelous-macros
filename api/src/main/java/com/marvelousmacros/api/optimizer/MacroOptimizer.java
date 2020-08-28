package com.marvelousmacros.api.optimizer;

import com.marvelousmacros.api.entity.Ingredient;
import com.marvelousmacros.api.optimizer.dto.MacroTargets;
import com.marvelousmacros.api.optimizer.model.Candidate;
import com.marvelousmacros.api.optimizer.model.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * The sole purpose of this class is to take in a list of ingredients and optimize
 * the quantities of each ingredient to meet the target macro goals.
 *
 * This implementation uses a genetic algorithm approach as the optimization engine.
 */
@Service
public class MacroOptimizer {
    final private OptimizerConfig config;

    @Autowired
    public MacroOptimizer(OptimizerConfig config) {
        this.config = config;
    }

    public Map<Ingredient, Integer> optimize(MacroTargets targets, List<Ingredient> ingredientList) {
        Population population = new Population(targets, ingredientList, config.getPopulationSize());

        while (!population.hasSolution() && population.getGenerationCount() < config.getGenerationLimit()) {
            population.evolve();
        }

        Candidate solution = population.getCandidateSolution();
        return solution.getQuantities();
    }
}
