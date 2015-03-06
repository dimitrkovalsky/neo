package com.liberty;

import com.graphaware.reco.generic.context.Mode;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.neo4j.engine.Neo4jTopLevelDelegatingEngine;
import com.liberty.services.WordsComputingEngine;
import org.neo4j.graphdb.Node;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * User: Dmytro_Kovalskyi
 * Date: 27.02.2015
 * Time: 11:39
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//        System.out.println("Context read");
        GalaxyService service = context.getBean(GalaxyService.class);
//        //        context.getBean(GalaxyService.class).getAllWorlds();
//        service.makeSomeWorlds();
//
//        for (World w : service.getAllWorlds())
//            System.out.println(w);
//        System.out.println("End");

        Node node = service.getNode(17);
        System.out.println(node);
        Neo4jTopLevelDelegatingEngine engine = new WordsComputingEngine();
        List<Recommendation<Node>> recommend = engine.recommend(node, Mode.REAL_TIME, 2);
        for (Recommendation<Node> nodeRecommendation : recommend) {
            System.out.println(nodeRecommendation);
        }
    }

}
