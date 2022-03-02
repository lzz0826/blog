package com.sai.blog;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort {

    void sort (int arr[]){
        int n=arr.length;
        for (int i=0; i<n-1;i++){
            int min_idx=i;
            for (int j=i+1; j<n ;j++){
                if (arr[j] <arr[min_idx] ){
                    min_idx=j;
                }
            }int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }

    }
    public static void main(String args[] ){

        SelectionSort selectionSort=new SelectionSort();
        int arr[] = {90,20,70,10,50,44,22,98,11};


        selectionSort.sort(arr);

        System.out.println(Arrays.toString(arr));







    }

}