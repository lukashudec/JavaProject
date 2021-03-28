package locustTests.tasks;

import com.github.myzhan.locust4j.AbstractTask;
import com.github.myzhan.locust4j.Locust;

public class exampleTask extends AbstractTask {
    public int weight;
    public String name = "success";

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

    public exampleTask(int weight){
        this.weight = weight;
    }

    @Override
    public void execute() {
        try {
            long startTime = System.currentTimeMillis();
            Thread.sleep(10);
            System.out.println("Im running");
            long elapsed = System.currentTimeMillis() - startTime;
            Locust.getInstance().recordSuccess("http", "success", elapsed, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
