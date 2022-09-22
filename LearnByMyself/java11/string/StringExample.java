package java11.string;

import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.Line;

public class StringExample {
    public static void main(String[] args) {
        String multilineString = "Deft helps \n \n developers \n explore Java.";
        System.out.println(multilineString);
        List<String>  lines = multilineString.lines()  // phan tich string ban dau thanh cac string nho cai dien cho cac dong 
                            .filter(line -> !line.isBlank()) // loai bo cac String rong tuong ung voi cac dong nhan duoc tu line
                            .map(String::strip) // tuong tu nhu trim () nhung no cc kha nang kiem soat tot hon va ho tro unicode
                .collect(Collectors.toList());

        System.out.println(lines);

        
    }
}
