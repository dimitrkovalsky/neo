package com.liberty.reco;

import com.graphaware.reco.generic.engine.RecommendationEngine;
import com.graphaware.reco.generic.post.PostProcessor;
import com.graphaware.reco.neo4j.engine.Neo4jTopLevelDelegatingEngine;
import org.neo4j.graphdb.Node;

import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:08
 */
public final class FriendsComputingEngine extends Neo4jTopLevelDelegatingEngine {

    public FriendsComputingEngine() {
        super(new FriendsContextFactory());
    }

    @Override
    protected List<RecommendationEngine<Node, Node>> engines() {
        return Arrays.<RecommendationEngine<Node, Node>>asList(
                new FriendsInCommon(),
                new RandomPeople()
        );
    }

    @Override
    protected List<PostProcessor<Node, Node>> postProcessors() {
        return Arrays.asList(
                new RewardSameLabels(),
                new RewardSameLocation(),
                new PenalizeAgeDifference()
        );
    }
}
