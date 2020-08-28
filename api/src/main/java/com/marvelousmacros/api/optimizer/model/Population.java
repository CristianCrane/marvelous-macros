package com.marvelousmacros.api.optimizer.model;

import com.marvelousmacros.api.entity.Ingredient;
import com.marvelousmacros.api.optimizer.dto.MacroTargets;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Population {
    private List<Candidate> candidates;
    private Candidate bestCandidate;
    private int generationCount;
    
    public Population(MacroTargets targets, List<Ingredient> ingredients, int populationSize) {
        candidates = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            candidates.add(new Candidate(targets, ingredients));
        }
        generationCount = 0;
    }

    public void calculateFitness() {
        // todo
    }

    public void evolve() {
        // todo
    }

    public boolean hasSolution() {
        return false;
    }

    public Candidate getCandidateSolution() {
        return null;
    }

}
