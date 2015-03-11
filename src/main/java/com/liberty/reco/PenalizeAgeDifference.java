package com.liberty.reco;

import com.graphaware.reco.generic.post.PostProcessor;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.generic.result.Recommendations;
import com.graphaware.reco.generic.transform.ParetoScoreTransformer;
import org.neo4j.graphdb.Node;

import static com.graphaware.common.util.PropertyContainerUtils.getInt;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:05
 */
public class PenalizeAgeDifference implements PostProcessor<Node, Node> {

    private final ParetoScoreTransformer transformer = new ParetoScoreTransformer(10, 20);

    @Override
    public void postProcess(Recommendations<Node> recommendations, Node input) {
        int age = getInt(input, "age", 40);

        for (Recommendation<Node> reco : recommendations.get()) {
            int diff = Math.abs(getInt(reco.getItem(), "age", 40) - age);
            reco.add("ageDifference", -transformer.transform(reco, diff));
        }
    }
}
