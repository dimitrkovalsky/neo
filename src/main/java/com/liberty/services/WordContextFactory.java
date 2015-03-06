package com.liberty.services;

import com.graphaware.reco.generic.filter.Filter;
import com.graphaware.reco.neo4j.context.Neo4jContextFactory;
import com.graphaware.reco.neo4j.filter.ExcludeSelf;
import org.neo4j.graphdb.Node;

import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 03.03.2015
 * Time: 17:31
 */
public class WordContextFactory extends Neo4jContextFactory {
    @Override
    protected List<Filter<Node, Node>> filters() {
        return Arrays.<Filter<Node, Node>>asList(new ExcludeSelf());
    }


}
