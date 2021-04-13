package locustTests;

import com.github.myzhan.locust4j.Locust;
import locustTests.master.LocustMaster;
import locustTests.tasks.ExampleTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;


class RunLocustTests {
    private static LocustMaster masterServer;
    private static Locust slave;

    @BeforeAll
    static void startLocustServer() throws IOException {
        // http://localhost:8089/
        masterServer = new LocustMaster()
                .redirectIO()
                .start();

        slave = Locust.getInstance();
        slave.setMasterHost(masterServer.host);
        slave.setMasterPort(masterServer.port); //some free port to run the Locust slave
    }

    @Test
    void testStartSlaveTask() {
        slave.run(new ExampleTask(1)); // <- Your custom performance tasks should be here
    }
}
