package com.liberty.reco;

import com.graphaware.reco.generic.transform.ParetoScoreTransformer;
import com.graphaware.reco.generic.transform.ScoreTransformer;
import com.graphaware.reco.neo4j.engine.SomethingInCommon;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;

public class FriendsInCommon extends SomethingInCommon {

    @Override
    protected ScoreTransformer scoreTransformer() {
        return new ParetoScoreTransformer(100, 10);
    }

    @Override
    protected RelationshipType getType() {
        return Relationships.FRIEND_OF;
    }

    @Override
    protected Direction getDirection() {
        return Direction.BOTH;
    }

    @Override
    public String name() {
        return "friendsInCommon";
    }
}
