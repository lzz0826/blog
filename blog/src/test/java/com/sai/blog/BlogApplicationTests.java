package com.sai.blog;

import com.sai.blog.Service.BlogsService;
import com.sai.blog.Service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private BlogsService blogsService;


//    @Autowired
//    TypeService typeService;
//    @Test
//    public void tet() {
//        System.out.println( typeService.getType(1l));
//    }
//
//    @Test
    void contextLoads() {

        class BinarySearch {


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


            public void main(String[] args) {

                int[] list = { 10 ,20, 30 ,40, 45 ,50};

                com.sai.blog.BinarySearch binarySearch= new com.sai.blog.BinarySearch();

                System.out.println(binarySearch.binarySearch(list,56));





            }
        }

    }

}




