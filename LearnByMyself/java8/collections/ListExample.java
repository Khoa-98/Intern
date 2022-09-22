package collections;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> listString = new ArrayList<>();
        listString.add("khoa");
        listString.add("Nam");
        listString.add("mai");
        listString.add("hoa");
        listString.add("manh");

        listString.forEach(l -> System.out.println(l));

        System.out.println("get element in list");
        String element = listString.get(0);
        System.out.println("Lay ra phan tu : " + element);

        String rmEl = listString.remove(2);
        System.out.println("xoa phan tu khoi list: " + rmEl);

        String[] toArray = (String[]) listString.toArray();

    }
}
