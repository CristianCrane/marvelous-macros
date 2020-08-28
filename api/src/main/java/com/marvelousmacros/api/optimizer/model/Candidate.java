package com.marvelousmacros.api.optimizer.model;

import com.marvelousmacros.api.entity.Ingredient;
import com.marvelousmacros.api.optimizer.OptimizerUtils;
import com.marvelousmacros.api.optimizer.dto.MacroTargets;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.marvelousmacros.api.constants.MeasurementType.DISCRETE;

@Getter
public class Candidate {

    private Map<Ingredient, Integer> quantities;
    private final MacroTargets macroTargets;
    private final List<Ingredient> ingredientList;
    private double fitness;
    private int totalProteins;
    private int totalCarbs;
    private int totalFats;

    public Candidate(MacroTargets targets, List<Ingredient> ingredientList) {
        this.macroTargets = targets;
        this.ingredientList = ingredientList;
        this.quantities = new HashMap<>();

        ingredientList.forEach(this::generateRandomQuantity);

        // tally up the macro totals for each ingredient based on the random quantities
        quantities.forEach((ingredient, quantity) -> {
            if (ingredient.getMeasurementType() == DISCRETE) {
                totalProteins += ingredient.getDiscreteProteins() * quantity;
                totalCarbs += ingredient.getDiscreteCarbs() * quantity;
                totalFats += ingredient.getDiscreteFats() * quantity;
            } else {
                totalProteins += ingredient.getProteinConversionFactor() * quantity;
                totalCarbs += ingredient.getCarbConversionFactor() * quantity;
                totalFats += ingredient.getFatConversionFactor() * quantity;
            }
        });
    }

    private void generateRandomQuantity(Ingredient ingredient) {
        // the upper bound for quantity is the first number s.t. one of the macros hits a target. todo: figure out more efficient way to calculate this. this is pretty dumb approach
        int upperBound = 0, p = 0, c = 0, f = 0;
        while (p < macroTargets.getTargetProtein() && c < macroTargets.getTargetCarbs() && f < macroTargets.getTargetFat()) { // todo: potential infinite loop?
            upperBound++;
            if (ingredient.getMeasurementType() == DISCRETE) {
                p = ingredient.getDiscreteProteins() * upperBound;
                c = ingredient.getDiscreteCarbs() * upperBound;
                f = ingredient.getDiscreteFats() * upperBound;
            } else {
                p = (int) ingredient.getProteinConversionFactor() * upperBound;
                c = (int) ingredient.getCarbConversionFactor() * upperBound;
                f = (int) ingredient.getFatConversionFactor() * upperBound;
            }
        }

        // generate random quantity
        quantities.put(ingredient, OptimizerUtils.randomInt(upperBound));
    }

    public double calculateFitness() {
        // todo: i think i should multiply each macro by its calorie contribution for further accuracy
        // perfect score is no overage or deficit of any macro. exactly the macro targets.
        double perfectScore = macroTargets.getTargetProtein() + macroTargets.getTargetCarbs() + macroTargets.getTargetFat();

        // calculate how 'off' each macro is from the target (the abs of the difference)
        double proteinDelta = macroTargets.getTargetProtein() - Math.min(macroTargets.getTargetProtein(), Math.abs(macroTargets.getTargetProtein() - totalProteins));
        double carbDelta = macroTargets.getTargetCarbs() - Math.min(macroTargets.getTargetCarbs(), Math.abs(macroTargets.getTargetCarbs() - totalCarbs));
        double fatDelta = macroTargets.getTargetProtein() - Math.min(macroTargets.getTargetProtein(), Math.abs(macroTargets.getTargetProtein() - totalFats));

        fitness = (proteinDelta + carbDelta + fatDelta) / perfectScore;
        return fitness;
    }

}
