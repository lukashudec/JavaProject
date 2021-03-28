package locustTests;

import com.github.myzhan.locust4j.Locust;
import locustTests.tasks.exampleTask;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python","-m","locust","-f","C:/Users/lenovo/IdeaProjects/untitled/src/test/java/locustTests/master/master.py","--master","--master-bind-host=127.0.0.1","--master-bind-port=5557");
     //   ProcessBuilder processBuilder = new ProcessBuilder("python","-m","locust","-f","C:/Users/lenovo/IdeaProjects/untitled/src/test/java/locustTests/master/masterAlt.py");
        processBuilder.redirectErrorStream(true);
        processBuilder.inheritIO();
        processBuilder.start();
        Locust locust = Locust.getInstance();
        locust.setMasterHost("127.0.0.1");
        locust.setMasterPort(5557); //some free port to run the Locust slave
        locust.run(new exampleTask(1)); // <- You custom performance tasks should be here

        // http://localhost:8089/
    }
}
