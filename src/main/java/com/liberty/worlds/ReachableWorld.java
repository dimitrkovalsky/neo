package com.liberty.worlds;

import com.graphaware.reco.generic.context.Context;
import com.graphaware.reco.generic.engine.SingleScoreRecommendationEngine;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.generic.result.Recommendations;
import com.graphaware.reco.generic.transform.ParetoScoreTransformer;
import com.graphaware.reco.generic.transform.ScoreTransformer;
import com.liberty.domain.World;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Dmytro_Kovalskyi
 * Date: 12.03.2015
 * Time: 14:37
 */
public class ReachableWorld extends SingleScoreRecommendationEngine<World, World> {


    public void postProcess(Recommendations<World> recommendations, World input) {
        int moonsAmount = input.getMoons();
        for (Recommendation<World> recommendation : recommendations.get()) {
            for (World reachable : recommendation.getItem().getReachableByRocket())
                if (reachable.getName().equals(input.getName()))
                    recommendation.add("reachable", 30);
        }
    }

    @Override
    protected Map<World, Float> doRecommendSingle(World input, Context<World, World> context) {
        Map<World, Float> result = new HashMap<>();

        for (World world : input.getReachableByRocket()) {
            addToResult(result, world, 1);
        }
        return result;
    }

    @Override
    protected ScoreTransformer scoreTransformer() {
        return new ParetoScoreTransformer(100, 10);
    }

    @Override
    public String name() {
        return "reachable";
    }
}
