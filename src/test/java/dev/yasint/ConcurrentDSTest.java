package dev.yasint;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Record {


    private final int id;
    private final String name;
    private final int age;

    public Record(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class ConcurrentDSTest {

    @Test
    public void test() {


        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
//        Map<Integer, String> map = new HashMap<>();

        Record r1 = new Record(1, "Yasin1", 25);
        Record r2 = new Record(2, "Yasin2", 25);
        Record r3 = new Record(3, "Yasin3", 25);
        Record r4 = new Record(4, "Yasin4", 25);
        Record r5 = new Record(5, "Yasin5", 25);

        Thread t1 = new Thread(() -> {
//            try {
//                Thread.sleep(2000);
            map.put(r1.getId(), r1.getName());
            map.put(r2.getId(), r2.getName());
            map.put(r3.getId(), r3.getName());
            map.put(r4.getId(), r4.getName());
            map.put(r5.getId(), r5.getName());
            int i = 10;
            while (i > 0) {
                map.put(i % 3, "Some Random String");
                i--;
            }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        });

        Thread t2 = new Thread(() -> {

            while (true) {
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        t1.start();
        t2.start();

    }

}
