package com.liberty.reco;

import com.graphaware.reco.generic.engine.RecommendationEngine;
import com.graphaware.reco.generic.log.Logger;
import com.graphaware.reco.generic.log.Slf4jRecommendationLogger;
import com.graphaware.reco.generic.log.Slf4jStatisticsLogger;
import com.graphaware.reco.neo4j.engine.Neo4jPrecomputedEngine;
import com.graphaware.reco.neo4j.engine.Neo4jTopLevelDelegatingEngine;
import org.neo4j.graphdb.Node;

import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:15
 */
public final class FriendsRecommendationEngine extends Neo4jTopLevelDelegatingEngine {

    public FriendsRecommendationEngine() {
        super(new FriendsContextFactory());
    }

    @Override
    protected List<RecommendationEngine<Node, Node>> engines() {
        return Arrays.<RecommendationEngine<Node, Node>>asList(
                new Neo4jPrecomputedEngine(),
                new FriendsComputingEngine()
        );
    }

    @Override
    protected List<Logger<Node, Node>> loggers() {
        return Arrays.asList(
                new Slf4jRecommendationLogger<Node, Node>(),
                new Slf4jStatisticsLogger<Node, Node>()
        );
    }
}