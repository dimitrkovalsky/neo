package com.liberty.reco;

import org.neo4j.graphdb.RelationshipType;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:03
 */
public enum Relationships implements RelationshipType {
    FRIEND_OF, LIVES_IN, REACHABLE_BY_ROCKET
}
