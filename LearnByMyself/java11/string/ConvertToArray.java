package java11.string;

import java.util.Arrays;
import java.util.List;

public class ConvertToArray {
    public static void main(String[] args) {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray =  sampleList.toArray(String[]::new);
        System.out.println(Arrays.toString(sampleArray));
    }
}
