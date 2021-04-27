package locustTests.master;

import com.github.myzhan.locust4j.stats.RequestSuccess;
import com.github.myzhan.locust4j.stats.Stats;
import java.util.function.Supplier;


public class LocustTimer {
    private final String type;
    long startTime;

    public LocustTimer(String name) {
        this.type = name;
    }

    public <T> T measure(Supplier<T> s, String name) {
        startTime = System.currentTimeMillis();
        T result = s.get();
        recordSuccess(name, startTime);
        return result;
        // takeTime(() -> method(param))
        // used in functional test
    }

    private void recordSuccess(String name, long startTime) {
        RequestSuccess success = new RequestSuccess();
        success.setRequestType(this.type);
        success.setName(name);
        success.setResponseTime(System.currentTimeMillis() - startTime);
        success.setContentLength(1);
        Stats.getInstance().getReportSuccessQueue().offer(success);
        Stats.getInstance().wakeMeUp();
    }
}
