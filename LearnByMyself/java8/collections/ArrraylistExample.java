package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ArrraylistExample {
    public static void main(String[] args) {
        ArrayList<String> thunuoi = new ArrayList<>();
        thunuoi.add(0, "meo");
        thunuoi.add(1, "cho");
        thunuoi.add(2, "ho");

        System.out.println(thunuoi);

        ArrayList<String> dongvat = new ArrayList<>();
        dongvat.add("ca sau");

        dongvat.addAll(thunuoi);
        System.out.println(dongvat);

        ArrayList<String> friends = new ArrayList<>(Arrays.asList("khoa", "huong", "sang", "long"));
        System.out.println("danh sach ban be: " + friends);

        // change element in ArrayList
        friends.set(1, "manh");
        System.out.println("List after update: " + friends);

        for (String fString : friends) {
            System.out.print(fString);
            System.out.print(", ");
        }
        System.out.println();

        // convert ArrayList to Array
        String[] arr = new String[friends.size()];
        friends.toArray(arr);
        System.out.println(Arrays.toString(arr));
      
        System.out.println("danh sach sap xep: ");
        Collections.sort(friends);
        System.out.println(friends);

    }
}
