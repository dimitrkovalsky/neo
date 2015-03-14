package com.liberty.worlds;

import com.graphaware.reco.generic.post.PostProcessor;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.generic.result.Recommendations;
import com.liberty.domain.World;

/**
 * User: Dmytro_Kovalskyi
 * Date: 12.03.2015
 * Time: 11:50
 */
public class RewardSameMoons implements PostProcessor<World, World> {

    @Override
    public void postProcess(Recommendations<World> recommendations, World input) {
        int moonsAmount = input.getMoons();
        for (Recommendation<World> recommendation : recommendations.get()) {
            if (recommendation.getItem().getMoons() == moonsAmount)
                recommendation.add("moonsAmount", 50);
        }
    }
}
