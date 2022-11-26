package skypro.hw2_14;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        stringList.add(2, "9");
        System.out.println(Arrays.toString(stringList.toArray()));

        stringList.set(0, "8");
        System.out.println(Arrays.toString(stringList.toArray()));

        stringList.remove(0);
        System.out.println(Arrays.toString(stringList.toArray()));

        System.out.println(stringList.contains("9"));

        System.out.println(stringList.indexOf("3"));

        stringList.clear();
        System.out.println(Arrays.toString(stringList.toArray()));

    }
}
