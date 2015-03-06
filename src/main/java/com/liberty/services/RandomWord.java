package com.liberty.services;

import com.graphaware.common.policy.NodeInclusionPolicy;
import com.graphaware.reco.neo4j.engine.RandomRecommendations;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;

/**
 * User: Dmytro_Kovalskyi
 * Date: 03.03.2015
 * Time: 15:52
 */
public class RandomWord extends RandomRecommendations {

    @Override
    public String name() {
        return "random";
    }

    @Override
    protected NodeInclusionPolicy getPolicy() {
        return new NodeInclusionPolicy() {
            @Override
            public boolean include(Node node) {
                return node.hasLabel(DynamicLabel.label("Word"));
            }
        };
    }
}
