
package com.ds_intelligence_arm.utils;

import com.ds_intelligence_arm.storage.model.auto_am_DataRecord;

public class SortingAlgorithms {
 /* Implement the bubble, insertion, selection sort which will
  sort descending the array objects based on the price_in_amd. */

    public static void bubbleSort(auto_am_DataRecord[] myArray){
        int myArrayLength = myArray.length;

        for (int i = 0; i < myArrayLength - 1; i++){
            for (int j = 0; j < myArrayLength - i - 1; j++){

                double currentPrice = getPrice(myArray[j]);
                double nextPrice = getPrice(myArray[j+1]);

                if(currentPrice < nextPrice){
                    auto_am_DataRecord temp = myArray[j];
                    myArray[j]  =  myArray[j+1];
                    myArray[j+1] = temp;
                }
            }
        }
    }

    public static void insertionSort(auto_am_DataRecord[] myArray) {
        int myArrayLength = myArray.length;

        for (int i = 1; i < myArrayLength; i++) {
            auto_am_DataRecord key = myArray[i];
            double keyPrice = getPrice(key);

            int j = i - 1;

            while (j >= 0 && getPrice(myArray[j]) < keyPrice) {
                myArray[j + 1] = myArray[j--];
            }
            myArray[j + 1] = key;
        }
    }

    public static void selectionSort(auto_am_DataRecord[] myArray){
        int myArrayLength = myArray.length;

        for (int i = 0; i < myArrayLength - 1; ++i){
            int maxIndex = i;
            double maxPrice = getPrice(myArray[maxIndex]);

            for (int j = i + 1; j < myArrayLength; ++j){
                double nextPrice = getPrice(myArray[j]);

                if(nextPrice > maxPrice){
                    maxIndex = j;
                    maxPrice = nextPrice;
                }
            }
            if (maxIndex != i) {
                auto_am_DataRecord temp = myArray[maxIndex];
                myArray[maxIndex] = myArray[i];
                myArray[i] = temp;
            }
        }
    }

    private static double getPrice(auto_am_DataRecord price) {
        return price.getPriceInAMD().isEmpty() ? 0 : formatPrice(price.getPriceInAMD());
    }

    private static double formatPrice(String price){
        price = price.replace(" ", "").replace("֏", "");
        return Double.parseDouble(price);
    }
}
