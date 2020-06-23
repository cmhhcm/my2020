
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author huangmc 2020/6/16 下午1:42
 */
public class TestScheduledExecutor {


    private ScheduledExecutorService executorService;



    public void scheduleWithFixedDelay(long delay, TimeUnit unit){
        System.out.println("start to schedule a timer to refresh data items");
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                System.out.println("shua..."+count++);
            }
        },delay,delay,unit);
    };


    @Test
    public void testSchedule(){
        testReplace("2020-06-17");
    }

    public void testReplace(String birthday){
        System.out.println("只替换/ ");
        System.out.println(birthday.trim().replace("/",""));
        System.out.println("只替换 -");
        System.out.println(birthday.trim().replace("-",""));
        System.out.println("/和- 都替换");
        System.out.println(birthday.trim().replace("/","").replace("-",""));
    }

    @Test
    public void testFor(){
        for(int i=0;i<10;i++){
            if(i==3){
                return;
            }else{
                System.out.println(i);
            }
        }
        System.out.println("fdafaf");
    }

}
