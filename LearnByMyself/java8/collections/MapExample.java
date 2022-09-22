package collections;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.HandlerBase;

public class MapExample {
    public static void main(String[] args) {
        // init map
        Map<Integer, String> map = new HashMap<>();
        // add element inside map
        map.put(100, "A");
        map.put(101, "B");
        map.put(102, "C");

        // show map
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
