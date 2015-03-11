import com.graphaware.common.util.IterableUtils;
import com.graphaware.reco.generic.context.Mode;
import com.graphaware.reco.generic.result.Recommendation;
import com.graphaware.reco.neo4j.engine.Neo4jTopLevelDelegatingEngine;
import com.graphaware.test.integration.WrappingServerIntegrationTest;
import com.liberty.reco.FriendsRecommendationEngine;
import org.junit.Test;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: Dmytro_Kovalskyi
 * Date: 11.03.2015
 * Time: 16:10
 */
public class ModuleIntegrationTest extends WrappingServerIntegrationTest {

    private Neo4jTopLevelDelegatingEngine recommendationEngine;
   // private RecommendationsRememberingLogger rememberingLogger = new RecommendationsRememberingLogger();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        recommendationEngine = new FriendsRecommendationEngine();
     //   rememberingLogger.clear();
    }

    @Override
    protected void populateDatabase(GraphDatabaseService database) {
//        new ExecutionEngine(database).execute(
//                "CREATE " +
//                        "(m:Person:Male {name:'Michal', age:30})," +
//                        "(d:Person:Female {name:'Daniela', age:20})," +
//                        "(v:Person:Male {name:'Vince', age:40})," +
//                        "(a:Person:Male {name:'Adam', age:30})," +
//                        "(l:Person:Female {name:'Luanne', age:25})," +
//                        "(b:Person:Male {name:'Bob', age:60})," +
//
//                        "(lon:City {name:'London'})," +
//                        "(mum:City {name:'Mumbai'})," +
//
//                        "(m)-[:FRIEND_OF]->(d)," +
//                        "(m)-[:FRIEND_OF]->(l)," +
//                        "(m)-[:FRIEND_OF]->(a)," +
//                        "(m)-[:FRIEND_OF]->(v)," +
//                        "(d)-[:FRIEND_OF]->(v)," +
//                        "(b)-[:FRIEND_OF]->(v)," +
//                        "(d)-[:LIVES_IN]->(lon)," +
//                        "(v)-[:LIVES_IN]->(lon)," +
//                        "(m)-[:LIVES_IN]->(lon)," +
//                        "(l)-[:LIVES_IN]->(mum)");
    }

    @Test
    public void shouldRecommendRealTime() {
        try (Transaction tx = getDatabase().beginTx()) {

            //verify Vince

            List<Recommendation<Node>> recoForVince = recommendationEngine.recommend(getPersonByName("Vince"), Mode.REAL_TIME, 2);

            String expectedForVince = "Computed recommendations for Vince: (Adam {total:19.338144,ageDifference:-5.527864,friendsInCommon:14.866008,sameGender:10.0}),(Luanne {total:7.856705,ageDifference:-7.0093026,friendsInCommon:14.866008})";

            //assertEquals(expectedForVince, rememberingLogger.get(getPersonByName("Vince")));

            //verify Adam

            List<Recommendation<Node>> recoForAdam = recommendationEngine.recommend(getPersonByName("Adam"), Mode.REAL_TIME, 2);

            String expectedForAdam = "Computed recommendations for Adam: (Vince {total:19.338144,ageDifference:-5.527864,friendsInCommon:14.866008,sameGender:10.0}),(Luanne {total:11.553411,ageDifference:-3.312597,friendsInCommon:14.866008})";

          //  assertEquals(expectedForAdam, rememberingLogger.get(getPersonByName("Adam")));

            //verify Luanne

            List<Recommendation<Node>> recoForLuanne = recommendationEngine.recommend(getPersonByName("Luanne"), Mode.REAL_TIME, 4);

            assertEquals("Daniela", recoForLuanne.get(0).getItem().getProperty("name"));
            assertEquals(22, recoForLuanne.get(0).getScore().getTotalScore(), 0.5);

            assertEquals("Adam", recoForLuanne.get(1).getItem().getProperty("name"));
            assertEquals(12, recoForLuanne.get(1).getScore().getTotalScore(), 0.5);

            assertEquals("Vince", recoForLuanne.get(2).getItem().getProperty("name"));
            assertEquals(8, recoForLuanne.get(2).getScore().getTotalScore(), 0.5);

            assertEquals("Bob", recoForLuanne.get(3).getItem().getProperty("name"));
            assertEquals(-9, recoForLuanne.get(3).getScore().getTotalScore(), 0.5);

            tx.success();
        }
    }

    private Node getPersonByName(String name) {
        return IterableUtils.getSingle(getDatabase().findNodesByLabelAndProperty(DynamicLabel.label("Person"), "name", name));
    }
}
