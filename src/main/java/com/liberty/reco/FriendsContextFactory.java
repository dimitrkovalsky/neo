package com.liberty.reco;

import com.graphaware.reco.generic.filter.BlacklistBuilder;
import com.graphaware.reco.generic.filter.Filter;
import com.graphaware.reco.neo4j.context.Neo4jContextFactory;
import com.graphaware.reco.neo4j.filter.ExcludeSelf;
import com.graphaware.reco.neo4j.filter.ExistingRelationshipBlacklistBuilder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;

import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:06
 */
public final class FriendsContextFactory extends Neo4jContextFactory {

    @Override
    protected List<BlacklistBuilder<Node, Node>> blacklistBuilders() {
        return Arrays.<BlacklistBuilder<Node, Node>>asList(
                new ExistingRelationshipBlacklistBuilder(Relationships.FRIEND_OF, Direction.BOTH)
        );
    }

    @Override
    protected List<Filter<Node, Node>> filters() {
        return Arrays.<Filter<Node, Node>>asList(
                new ExcludeSelf()
        );
    }
}
