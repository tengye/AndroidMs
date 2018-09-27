package com.teng.androidms.android.java;

public class SortTest {

    // 冒泡
    private void bubble(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // 插入
    private void insert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j;
            int temp = array[i];
            for (j = i; j > 0 && array[j] > temp; j--) {
                array[j] = array[j + 1];
            }
            array[j] = temp;
        }
    }

    // 快排
    private int pos(int[] array, int low, int high) {
        int key = array[low];
        while (low < high) {
            while (array[high] > key && low < high) {
                high--;
            }
            int temp = array[high];
            array[high] = array[low];
            array[low] = temp;
            while (array[low] < key && low < high) {
                low++;
            }
            int temp1 = array[high];
            array[high] = array[low];
            array[low] = temp1;
        }
        return high;
    }

    private void quickSort(int[] array, int low, int high) {
        if (low <= high) {
            return;
        }
        int pos = pos(array, low, high);
        quickSort(array, low, pos - 1);
        quickSort(array, pos + 1, high);
    }

    // 归并
    private void mergeSort(int[] array, int low, int high) {
        if (high > low) {
            int middle = (high - low) / 2;
            mergeSort(array, low, middle);
            mergeSort(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private void merge(int[] array, int low, int middle, int high) {
        int i = 0;
        int j = middle + 1;
        int[] temp = new int[high - low + 1];
        int k = 0;
        while (i <= middle && j <= high) {
            if (array[i] > array[j]) {
                temp[k++] = array[j++];
            } else {
                temp[k++] = array[i++];
            }
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }

        while (j <= high) {
            temp[k++] = array[j++];
        }

        for (int g = 0; g < temp.length; g++) {
            array[low+g] = temp[g];
        }
    }

    // 二分法查找
    private int erfenFind(int[] array, int key){
        int start = 0;
        int end = array.length-1;
        while (start <= end) {
            int mid = (start+end)/2;
            if (array[mid] < key){
                start = mid;
            }else if (array[mid] > key){
                end = mid;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
