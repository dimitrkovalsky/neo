package com.liberty.services;

import com.graphaware.reco.generic.engine.RecommendationEngine;
import com.graphaware.reco.generic.post.PostProcessor;
import com.graphaware.reco.neo4j.engine.Neo4jTopLevelDelegatingEngine;
import org.neo4j.graphdb.Node;

import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 03.03.2015
 * Time: 17:33
 */
public class WordsComputingEngine extends Neo4jTopLevelDelegatingEngine {

    public WordsComputingEngine() {
        super(new WordContextFactory());
    }

    @Override
    protected List<RecommendationEngine<Node, Node>> engines() {
        return Arrays.<RecommendationEngine<Node, Node>>asList(new RandomWord());
    }

    @Override
    protected List<PostProcessor<Node, Node>> postProcessors() {
        PostProcessor<Node, Node> processor = new LooksLike();
        return Arrays.asList(processor);
    }
}
