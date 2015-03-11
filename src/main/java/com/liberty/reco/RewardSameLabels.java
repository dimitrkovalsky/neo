package com.liberty.reco;

import com.graphaware.reco.generic.post.PostProcessor;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.generic.result.Recommendations;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;

import java.util.Arrays;

import static org.neo4j.helpers.collection.Iterables.toArray;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:04
 */
public class RewardSameLabels implements PostProcessor<Node, Node> {

    @Override
    public void postProcess(Recommendations<Node> recommendations, Node input) {
        Label[] inputLabels = toArray(Label.class, input.getLabels());

        for (Recommendation<Node> recommendation : recommendations.get()) {
            if (Arrays.equals(inputLabels, toArray(Label.class, recommendation.getItem().getLabels()))) {
                recommendation.add("sameGender", 10);
            }
        }
    }
}