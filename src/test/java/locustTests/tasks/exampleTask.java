package locustTests.tasks;

import calendar.legacy.CalendarClass;
import com.github.myzhan.locust4j.AbstractTask;
import com.github.myzhan.locust4j.stats.RequestSuccess;
import com.github.myzhan.locust4j.stats.Stats;
import locustTests.master.locustTimer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class exampleTask extends AbstractTask {
    public int weight;
    public String name = "success";
    private locustTimer timer = new locustTimer(name);
    long startTime;

    // test data
    CalendarClass calendar3 = new CalendarClass("12:00-13:00,16:00-18:00", "09:00-20:00");
    CalendarClass calendar4 = new CalendarClass("10:00-11:45,12:30-14:30", "10:00-18:30");

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
        startTime = System.currentTimeMillis();
        CalendarClass result = new CalendarClass("10:00-11:45,12:00-13:00,12:30-14:30,16:00-18:00", "10:00-18:30");
        recordSuccess( "constructor creation",startTime);

        CalendarClass merged = timer.measure(()->calendar3.mergeWithCalendar(calendar4),"merge creation");
        assertEquals(result.toString(), merged.toString(),"Not equal");
    }

    public void recordSuccess(String name, long startTime) {
        RequestSuccess success = new RequestSuccess();
        success.setRequestType(this.name);
        success.setName(name);
        success.setResponseTime(System.currentTimeMillis() - startTime);
        success.setContentLength(1);
        Stats.getInstance().getReportSuccessQueue().offer(success);
        Stats.getInstance().wakeMeUp();
    }
}
