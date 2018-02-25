package tests;

import main.hashmap.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class HashMapTest {
    @Test
    public void expectedMapping() {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 20);
        hashMap.put(2, 30);
        hashMap.put(3, 40);
        hashMap.put(4, 50);
        int value = hashMap.get(1);
        Assert.assertEquals(20, value);
    }
    @Test
    public void overridValue() {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 20);
        hashMap.put(2, 30);
        hashMap.put(3, 40);
        hashMap.put(4, 50);
        hashMap.put(1, 99);
        int value = hashMap.get(1);
        Assert.assertEquals(99, value);
    }
}
