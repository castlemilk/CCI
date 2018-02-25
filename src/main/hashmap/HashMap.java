package main.hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The HashMap datastructure is used for highly efficient lookups of key/value pair information. This lookup can be done
 * in O(1) time as the goal is to have a unique key for each item in the map. The Hashmap's values are inserted via
 * the following steps:
 * 1. the value of the key will be hashed to produce a unique value
 * 2. The hashed value of the key will then be mapped to an index of an array using something like
 *          hash(key) % array_length
 * 3. At the determined index there may be multiple KV(HashEntry) pairs stored within a linked list. On the get of an
 *    entry within the hashmap it will hit the determined index, then carry out an interation untill the specified
 *    key in the .get matches the key in the HashEntry, which will then be returned.
 *    This allows us to handle the collisions that will potentially occur when the hashed values of the keys are not
 *    unique when mapping to an index within the list structure.
 */
public class HashMap {
    int SIZE_OF_TABLE = 128;
    private final static long BIG_PRIME = 175365371; // The 9,786,124th prime number
    List<List<HashEntry>> table = new ArrayList<>(SIZE_OF_TABLE);
    public HashMap() {
        for (int i = 0; i < SIZE_OF_TABLE; i++) {
            table.add(null);
        }
    }
    public int get(int key) {
        int index = hashCodeNew(key);
        if (table.get(index) != null ) {
            for (HashEntry entry : table.get(index)) {
                if (entry.key == key)
                    return entry.value;
            }
        }
        throw new NoSuchElementException("The key: \"" + key + "\" was not found in the map!");
    }
    public void put(int key, int value) {
        int index = hashCodeNew(key);
        HashEntry hashEntry = new HashEntry(key, value);
        List<HashEntry> chain = table.get(index);
        if (chain == null) {
            chain = new ArrayList<>();
        }
        Iterator<HashEntry> it = chain.iterator();
        while(it.hasNext()) {
            if(it.next().key == key) {
                it.remove();
                break;
            }
        }
        chain.add(hashEntry);
        table.set(index, chain);
    }

    public int hashCodeNew(int h) {
        if (h >= 0)
            return (int) (h*BIG_PRIME) % SIZE_OF_TABLE;
        else
            return (int)((Integer.MAX_VALUE-h)*BIG_PRIME) % SIZE_OF_TABLE;
    }
//    public int hashCodeNew(int h) {
//        return (h & Integer.MAX_VALUE) & hash.size();
//    }

    /**
     * HashEntry - holds the key value for each item within the map. The key will correspond to an index in the array
     * which has been calculated as the hashed value of the key.
     */
    private class HashEntry {
        int key;
        int value;
        public HashEntry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
