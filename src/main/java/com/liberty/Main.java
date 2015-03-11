package com.liberty;

import com.graphaware.reco.generic.context.Mode;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.neo4j.engine.Neo4jTopLevelDelegatingEngine;
import com.liberty.reco.FriendsRecommendationEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;

import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 27.02.2015
 * Time: 11:39
 */
public class Main {
    public static void main(String[] args) {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        //NodeService service = context.getBean(NodeService.class);
        Neo4jTopLevelDelegatingEngine recommendationEngine =  new FriendsRecommendationEngine();

        GraphDatabaseService graphDb=new RestGraphDatabase("http://localhost:7474/db/data");
        Node node = graphDb.getNodeById(19); // Luanne from github docs
        List<Recommendation<Node>> recommend = recommendationEngine.recommend(node, Mode.REAL_TIME, 4);
        if(recommend.size() != 0)
            for(Recommendation<Node> n : recommend)
                System.out.println(n);
        else
            System.out.println("Empty");

    }

}
