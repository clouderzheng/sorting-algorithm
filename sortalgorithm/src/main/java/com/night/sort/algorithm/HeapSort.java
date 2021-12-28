package com.night.sort.algorithm;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;

import java.util.Arrays;


@Deprecated
public class HeapSort {

    public static void swap(int[] array,int k,int j){
        int temp = array[k];
        array[k] = array[j];
        array[j] = temp;
    }



    /**
    * 实现功能描述：最小堆 父节点小于左右节点  左节点大于右节点
    * @Author: zhengjingyun
    * @Date: 2021/12/28
    * @param
    * @return
    */
    public static void adjustHeap(int[] array,int start,int end){

        int left = 2 * start + 1;
        int temp = array[start];


            if( left + 1 < end && array[left + 1] > array[left]){
                swap(array,left + 1,left);
                adjustHeap(array,left,end);
                adjustHeap(array,left + 1,end);
            }

            if(left + 1 < end && temp > array[left + 1 ]){
                swap(array,start,left + 1 );
                adjustHeap(array,start,end);

            }else if(left + 1 == end && temp > array[left  ]){
                swap(array,start,left  );
                adjustHeap(array,start,end);
            }





    }



    /**
    * 实现功能描述：最小堆排序层次遍历  父节点小于左右节点  左右子节点无序
    * @Author: zhengjingyun
    * @Date: 2021/12/28
    * @param
    * @return
    */
    private static void downAdjust(int[] array, int index, int length) {
        // temp保存父节点值，用于最后的赋值
        int temp = array[index];
        int childIndex = 2 * index + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，直接跳出
            if (temp <= array[childIndex])
                break;
            //无需真正交换，单向赋值即可
            array[index] = array[childIndex];
//            array[childIndex] = temp;
            index = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[index] = temp;
    }
//[1,2,3,7,5,8,15,24,9,17,12,20]
    public static int findSpecialNumber(int array[],int k){

        int n = array.length;
        int i ;

        for( i = n / 2 - 1;i>=0 ;i--){
            adjustHeap(array,i,n - 1);
        }
        for(i = n = 1 ; i >= k -1;i--){

            swap(array,0,i);
            adjustHeap(array,0,i-1);
        }

        return array[ k - 1];
    }

    public static void main(String[] args) {
        int arr[] = { 7, 5, 15, 3, 17, 2, 20, 24, 1, 9, 12, 8 };



        for(int i = arr.length / 2 -1; i >= 0 ;i -- ){
            downAdjust(arr,i , arr.length);
        }



        System.out.println(JSON.toJSONString(arr));


        for(int i = arr.length / 2 -1; i >= 0 ;i -- ){
            adjustHeap(arr,i , arr.length);
        }



        System.out.println(JSON.toJSONString(arr));
    }
}
