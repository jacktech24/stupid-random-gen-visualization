package app;

import java.util.List;

/**
 * A help class for sorting series of numbers (integers), all sorts are written in a way that going step by step is possible.
 * @author Filip Prochazka (jacktech24)
 */
public class UltimateSorter {

    public UltimateSorter() {}

    /**
     *
     * @param unsortedList the list of data to sort
     * @return sorted list ${unsortedList}, transformed to array
     */
    public static int[] sortList(List<Integer> unsortedList, SortType sortType) {
        return sortArray(listToArray(unsortedList), sortType);
    }

    public static int[] sortArray(int[] unsortedArray, SortType sortType) {
        SortingAlgorithm algorithm = sortType.get();
        algorithm.passArray(unsortedArray);
        do {
            algorithm.step();
        } while (!algorithm.sorted);
        return algorithm.getArray();
    }

    public static SortingAlgorithm listSorter(List<Integer> unsortedList, SortType sortType) {
        return arraySorter(listToArray(unsortedList), sortType);
    }

    public static SortingAlgorithm arraySorter(int[] unsortedArray, SortType sortType) {
        SortingAlgorithm algorithm = sortType.get();
        algorithm.passArray(unsortedArray);
        return algorithm;
    }

    public static int[] listToArray(List<Integer> list) {
        int[] mWorkArray = new int[list.size()];
        for(int i = 0;i < mWorkArray.length;i++) {
            mWorkArray[i] = list.get(i);
        }
        return mWorkArray;
    }

    /**
     * Bubble Sort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Bubble_sort">Wikipedia</a>
     */
    public static class BubbleSort extends SortingAlgorithm {

        int i = 0;
        boolean pass = true;

        @Override
        public void step() {
            if (array.length <= 1) {
                sorted = true;
                return;
            }
            if(array[i] > array[i+1]) {
                int p = array[i];
                array[i] = array[i+1];
                array[i+1] = p;
                pass = false;
            }
            if(++i >= array.length-1) {
                sorted = pass;
                pass = true;
                i = 0;
            }
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {i, i+1};
        }

    }

    /**
     * Selection Sort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Selection_sort">Wikipedia</a>
     */
    public static class SelectSort extends SortingAlgorithm {

        int i = 0, j = 1, target = 0;

        @Override
        public void step() {
            if (array.length <= 1) {
                sorted = true;
                return;
            }
            if (array[target] > array[j]) {
                target = j;
            }
            j++;
            if (j >= array.length) {
                int p = array[i];
                array[i] = array[target];
                array[target] = p;
                i++;
                target = i;
                j = i+1;

                if(i == array.length-1) {
                    sorted = true;
                }
            }
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {i, j, target};
        }

    }

    /**
     * Insertion Sort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Insertion_sort">Wikipedia</a>
     */
    public static class InsertSort extends SortingAlgorithm {

        @Override
        public void step() {
            //todo
        }

        @Override
        public int[] getHighlighted() {
            return new int[0];
        }

    }

    /**
     * Quicksort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Quicksort">Wikipedia</a>
     */
    public static class QuickSort extends SortingAlgorithm {

        @Override
        public void step() {
            //todo
        }

        @Override
        public int[] getHighlighted() {
            return new int[0];
        }

    }

    public enum SortType {
        SELECT(SelectSort.class), BUBBLE(BubbleSort.class);

        private Class<? extends SortingAlgorithm> sortClass;

        SortType(Class<? extends SortingAlgorithm> sortClass) {
            this.sortClass = sortClass;
        }

        public SortingAlgorithm get() {
            try {
                return sortClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException();
            }
        }
    }

    public static abstract class SortingAlgorithm {

        protected int[] array;
        protected boolean sorted = false;

        public SortingAlgorithm(){}

        public abstract void step();
        public abstract int[] getHighlighted();

        public boolean sorted() {
            return sorted;
        }

        public void passArray(int[] array) {
            this.array = array;
        }

        public int[] getArray() {
            return array;
        }
    }

}

