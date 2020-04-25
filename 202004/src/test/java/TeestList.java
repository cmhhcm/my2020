import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:起舞的日子
 * Date:  2020/4/20 下午2:42
 */
public class TeestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        System.out.println(list.size()+"\t"+list.toString()+"\t"+list.get(0));
        list.add(1, "fdaf");
        System.out.println(list.toString());


    }
}

