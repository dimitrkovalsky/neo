package com.liberty.worlds;

import com.graphaware.reco.generic.engine.RecommendationEngine;
import com.graphaware.reco.generic.engine.TopLevelDelegatingRecommendationEngine;
import com.graphaware.reco.generic.log.Logger;
import com.graphaware.reco.generic.log.Slf4jRecommendationLogger;
import com.graphaware.reco.generic.log.Slf4jStatisticsLogger;
import com.graphaware.reco.generic.post.PostProcessor;
import com.liberty.domain.World;

import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 12.03.2015
 * Time: 11:57
 */
public class WorldRecommendationEngine extends TopLevelDelegatingRecommendationEngine<World, World> {

    public WorldRecommendationEngine() {
        super(new WorldsContextFactory());
    }


    @Override
    protected List<Logger<World, World>> loggers() {
        return Arrays.asList(
                new Slf4jRecommendationLogger<World, World>(),
                new Slf4jStatisticsLogger<World, World>()
        );
    }

    @Override
    protected List<RecommendationEngine<World, World>> engines() {
        return Arrays.<RecommendationEngine<World, World>>asList(
                new ReachableWorld());
    }

    @Override
    protected List<PostProcessor<World, World>> postProcessors() {
        return Arrays.<PostProcessor<World, World>>asList(new RewardSameMoons());
    }
}
