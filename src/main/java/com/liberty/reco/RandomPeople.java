package com.liberty.reco;

import com.graphaware.common.policy.NodeInclusionPolicy;
import com.graphaware.reco.neo4j.engine.RandomRecommendations;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:01
 */
public class RandomPeople extends RandomRecommendations {

    @Override
    protected NodeInclusionPolicy getPolicy() {
        return new NodeInclusionPolicy() {
            @Override
            public boolean include(Node node) {
                return node.hasLabel(DynamicLabel.label("Person"));
            }
        };
    }

    @Override
    public String name() {
        return "random";
    }
}
