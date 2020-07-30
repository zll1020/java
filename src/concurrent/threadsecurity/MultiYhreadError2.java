package concurrent.threadsecurity;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 返回一个副本，解决线程安全问题
 * User: zhangll
 * Date: 2020-07-29
 * Time: 17:19
 */
public class MultiYhreadError2 {

    private Map<String,String> states;

    public MultiYhreadError2(){
        states = new HashMap<>();
        states.put("1","周一");
    }

    public Map<String,String> getStates(){
        return states;
    }

    // 返回副本，解决线程安全问题
    public Map<String,String> getStatesImproved(){
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiYhreadError2 multiYhreadError2 = new MultiYhreadError2();
        Map<String,String> states = multiYhreadError2.getStates();
        System.out.println(states.get("1"));
        states.put("2","周二");
        System.out.println(states.get("2"));
    }
}
