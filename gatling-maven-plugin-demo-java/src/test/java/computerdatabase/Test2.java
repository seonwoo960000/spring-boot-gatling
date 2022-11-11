package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

/**
 * This sample is based on our official tutorials:
 * <ul>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/quickstart">Gatling quickstart tutorial</a>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/advanced">Gatling advanced tutorial</a>
 * </ul>
 */
public class Test2 extends Simulation {
    ChainBuilder hello = exec(http("Example").get("/test2/param")
                                             .check(status().is(200))
                                             .check(bodyString().is("param")));

    ScenarioBuilder helloScenario = scenario("request to example endpoint").exec(hello);

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080/example")
                                           .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

    {
//        setUp(helloScenario.injectOpen(rampUsersPerSec(10).to(800).during(120)))
//                .protocols(httpProtocol);

        setUp(helloScenario.injectClosed(constantConcurrentUsers(400).during(120)))
                .protocols(httpProtocol);
    }
}

//class Test1 extends Simulation {
//    ChainBuilder hello = exec(http("Example").get("/test1"));
//
//    ScenarioBuilder helloScenario = scenario("request to example endpoint").exec(hello);
//
//    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080/example")
//                                           .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//
//    {
//        setUp(helloScenario.injectClosed(constantConcurrentUsers(100).during(20)))
//                .protocols(httpProtocol);
//    }
//}
