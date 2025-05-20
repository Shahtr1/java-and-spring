package comparator_IO_dataTimeAPI;

import java.util.ArrayList;
import java.util.List;

public class Reifiable {
    public static void main(String[] args) {
        System.out.println(int.class);
        System.out.println(String.class);

        List<?> wildcards = new ArrayList<>();
        List rawList = new ArrayList();

        System.out.println(wildcards.getClass());
        System.out.println(rawList.getClass());
        System.out.println(wildcards.getClass() == rawList.getClass());
    }
}
