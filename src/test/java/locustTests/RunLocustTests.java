package locustTests;

import com.github.myzhan.locust4j.Locust;
import locustTests.master.locustMaster;
import locustTests.tasks.exampleTask;

import java.io.IOException;

public class RunLocustTests {
    public static void main(String[] args) throws IOException {
        // http://localhost:8089/
        locustMaster masterServer = new locustMaster("C:/Users/lenovo/IdeaProjects/untitled/src/test/java/locustTests/master/master.py")
                .redirectIO()
                .start();

        Locust locust = Locust.getInstance();
        locust.setMasterHost(masterServer.host);
        locust.setMasterPort(masterServer.port); //some free port to run the Locust slave
        locust.run(new exampleTask(1)); // <- You custom performance tasks should be here


    }
}
