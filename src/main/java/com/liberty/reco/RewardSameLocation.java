package com.liberty.reco;

import com.graphaware.reco.neo4j.post.RewardSomethingShared;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:02
 */
public class RewardSameLocation extends RewardSomethingShared {

    @Override
    protected RelationshipType type() {
        return Relationships.LIVES_IN;
    }

    @Override
    protected Direction direction() {
        return Direction.OUTGOING;
    }

    @Override
    protected float scoreValue(Node recommendation, Node input, Node sharedThing) {
        return 10;
    }

    @Override
    protected String scoreName() {
        return "sameLocation";
    }
}
