package com.nevexis.classes;

public class Array {
	void printRepeating(int arr[], int size)
    {
        int i;
        System.out.println("The repeating elements are : ");
          
        for (i = 0; i < size; i++) {
            int j = Math.abs(arr[i]);
            if (arr[j] >= 0)
                arr[j] = -arr[j];
            else
                System.out.print(j + " ");
        }
    }
}
