package com.sai.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


public class BinarySearch {


    public Object binarySearch(int[] list,int item) {

        int low = 0;
        int high = (list.length) - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = list[mid];

            if (guess == item) {
                return mid;
            }
             else if (guess>item){
                high = mid-1;
            }else if (guess<item){
                low = mid+1;
            }
        }return null;

    }

    @Test
    public void test() {

        int[] list = { 10 ,20, 30 ,40, 45 ,50};

        BinarySearch binarySearch= new BinarySearch();

        System.out.println(binarySearch.binarySearch(list,56));





    }

    @Test
    public void test2() {

    }

}
@SpringBootTest
class test222 {


    public static void bbb(){
        long b=0;
        for (long i = 100000; i >= 0 ; i--){
            if (i%3 !=0 && i%7 !=0){
                b++;
            }
        }
        System.out.println(b);
    }

    public static void aaa() {
        long b= 0;
        for (long i = 1000000; i >= 0; i--) {
            if (i % 3 == 0 && i % 7 == 0) {
                b++;

            }


        }

        System.out.println(b);
    }

    public static void main(String args[]) {

        bbb();


    }
}