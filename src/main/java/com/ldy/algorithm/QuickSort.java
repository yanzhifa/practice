package com.ldy.algorithm;

/**
 * Created by yanz3 on 4/26/18.
 */
public class QuickSort {

    public static void sort(int[] array, int left, int right) {

        int i = left, j = right;

        if(left >= right) return;
        int temp = array[left];
        int middle = (left + right) >> 1;

        while (i != j) {

            while (i < j && temp <= array[j])
                j--;
            while (i < j && temp >= array[i])
                i++;

            if (i < j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }

        }

        array[left] = array[i];
        array[i] = temp;

        sort(array, left, middle);
        sort(array, middle + 1, right);

    }

    public static void main(String[] args) {

        int[] array = {3, 5, 2, 4, 1, 6};
        sort(array, 0, 5);

        System.out.println(array);

    }
}
