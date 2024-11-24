package com.ds_intelligence_arm.for_tasks;

import com.ds_intelligence_arm.utils.DataStructures.SingleLinkedList;
import com.ds_intelligence_arm.utils.DataStructures.Queue;
import com.ds_intelligence_arm.utils.DataStructures.Stack;
import com.ds_intelligence_arm.storage.model.auto_am_DataRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataRecordDataStructures {

    public static List<auto_am_DataRecord> readDataRecordsFromJson(String filePath) {
        List<auto_am_DataRecord> dataRecords = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Define the type for a List of auto_am_DataRecord
            Type listType = new TypeToken<List<auto_am_DataRecord>>() {}.getType();

            // Deserialize JSON to List<auto_am_DataRecord>
            dataRecords = gson.fromJson(reader, listType);

        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return dataRecords;
    }

    public static void main(String[] args) {
        // Path to the JSON file
        String filePath = "src/resources/auto_am.json";

        // Read the data records from the JSON file
        List<auto_am_DataRecord> records = readDataRecordsFromJson(filePath);

        // Given the List save the records in SingleLinked List defined in DataStructures.
        // Print out using the SingleLinked List: URL, Title, Description, Price in USD, Price in AMD.
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        for (auto_am_DataRecord record: records) {
            singleLinkedList.insertBeginning(record);
        }
        System.out.println("SingleLinkedList contents:");
        singleLinkedList.display();

        // Given the List save the records in Queue defined in DataStructures.
        // Print out using the SingleLinked List: URL, Title, Description, Price in USD, Price in AMD.\
        Queue queue = new Queue();
        for (auto_am_DataRecord record: records) {
            queue.enqueue(record);
        }
        System.out.println("Queue contents:");
        queue.display();

        // Given the List save the records in Stack defined in DataStructures.
        // Print out using the SingleLinked List: URL, Title, Description, Price in USD, Price in AMD.
        Stack stack = new Stack();
        for (auto_am_DataRecord record: records) {
            stack.push(record);
        }
        System.out.println("Stack contents:");
        stack.display();

    }
}
