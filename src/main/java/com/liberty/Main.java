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
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//        GalaxyService service = context.getBean(GalaxyService.class);
//     //   service.makeSomeWorlds();
//        World world = service.findWorldById(49l);
//        System.out.println("RECOMMENDED FOR " + world);
//        WorldRecommendationEngine engine = new WorldRecommendationEngine();
//        List<Recommendation<World>> recommend = engine.recommend(world, Mode.REAL_TIME, 1);
//        for (Recommendation<World> worldRecommendation : recommend) {
//            System.out.println(worldRecommendation.getItem());
//        }
        nodes();
    }

    private static void nodes() {
        Neo4jTopLevelDelegatingEngine recommendationEngine = new FriendsRecommendationEngine();

        GraphDatabaseService graphDb = new RestGraphDatabase("http://localhost:7474/db/data");
        Node node = graphDb.getNodeById(13); // Luanne from github docs
        for (String key : node.getPropertyKeys()) {
            System.out.println(key + "\t" + node.getProperty(key));
        }

        List<Recommendation<Node>> recommend = recommendationEngine.recommend(node, Mode.REAL_TIME, 4);
        if (recommend.size() != 0)
            for (Recommendation<Node> n : recommend) {
                for (String key : n.getItem().getPropertyKeys()) {
                    System.out.println(key + "\t" + n.getItem().getProperty(key));
                }
                //for (Label label : n.getItem().getLabels())
                //  System.out.println(label);
            }
        else
            System.out.println("Empty");
    }

}
