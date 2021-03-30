package locustTests;

import com.github.myzhan.locust4j.Locust;
import locustTests.master.locustMaster;
import locustTests.tasks.exampleTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;


class RunLocustTests {
    private static locustMaster masterServer;
    private static Locust slave;

    @BeforeAll
    static void startLocustServer() throws IOException {
        // http://localhost:8089/
        masterServer = new locustMaster("C:/Users/lenovo/IdeaProjects/untitled/src/test/java/locustTests/master/master.py")
                .redirectIO()
                .start();

        slave = Locust.getInstance();
        slave.setMasterHost(masterServer.host);
        slave.setMasterPort(masterServer.port); //some free port to run the Locust slave
    }

    @Test
    void testStartSlaveTask() {
        slave.run(new exampleTask(1)); // <- You custom performance tasks should be here
    }
}
