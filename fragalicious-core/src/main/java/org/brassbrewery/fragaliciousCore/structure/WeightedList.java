package org.brassbrewery.fragaliciousCore.structure;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedList<T> {

    private final List<Entry<T>> entries = new ArrayList<>();
    private final Random random = new Random();
    private int totalWeight = 0;

    public void add(T item, int weight) {
        if (item == null || weight <= 0) return;

        totalWeight += weight;
        entries.add(new Entry<>(item, totalWeight));
    }

    /**
     * Selects a random item from the weighted list based on their weights.
     * If no item is in the list, will return @Null
     */

    public @Nullable T selectRandom() {

        if (entries.isEmpty()) return null;
        int r = random.nextInt(totalWeight) + 1;
        int low = 0, high = entries.size() - 1;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (r <= entries.get(mid).cumulativeWeight) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return entries.get(low).item;
    }

    /**
     * @Returns the number of items in the weighted list
     */

    public int size() {
        return entries.size();
    }
    /**
     * Checks whether there are any items in the weighted list
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Clears all items from the weighted list. Note: Not thread safe
     */
    public void clear() {
        entries.clear();
        totalWeight = 0;
    }

    private static class Entry<T> {
        final T item;
        final int cumulativeWeight;

        Entry(T item, int cumulativeWeight) {
            this.item = item;
            this.cumulativeWeight = cumulativeWeight;
        }
    }
}