
package com.ds_intelligence_arm.utils;

import com.ds_intelligence_arm.storage.model.auto_am_DataRecord;

public class DataStructures {
 /* Implement the SingleLinkedList, queue, stack which wil store the DataRecords. */
     public static class SingleLinkedList {
         static class Node {
             auto_am_DataRecord data;
             Node next;

             public Node(auto_am_DataRecord data) {
                 this.data = data;
             }
         }

         Node head;

         public void insertBeginning(auto_am_DataRecord data) {
             Node newNode = new Node(data);
             newNode.next = head;
             head = newNode;
         }

         public void deleteBeginning() {
             if (head != null) {
                 head = head.next;
             }
         }

     public void display() {
         Node current = head;
         while (current != null) {
             System.out.println(current.data);
             current = current.next;
         }
     }

     public Node search(auto_am_DataRecord key) {
         Node current = head;
         while (current != null) {
             if (current.data == key) {
                 return current;
             }
             current = current.next;
         }
         return null;
     }

     public void delete(auto_am_DataRecord key) {
         Node current = head;
         Node prev = null;

         if (current != null && current.data == key) {
             head = current.next;
             return;
         }

         while (current != null && current.data != key) {
             prev = current;
             current = current.next;
         }

         if (current != null) {
             prev.next = current.next;
         }
     }

     public void reverse() {
         Node prev = null;
         Node current = head;
         Node next = null;
         while (current != null) {
             next = current.next;
             current.next = prev;
             prev = current;
             current = next;
         }
         head = prev;
         }
     }

    public static class Queue {
        Node front, rear;

        public Queue() {
            this.front = this.rear = null;
        }

        static class Node {
            auto_am_DataRecord data;
            Node next;

            public Node(auto_am_DataRecord data) {
                this.data = data;
                this.next = null;
            }
        }

        public void enqueue(auto_am_DataRecord data) {
            Node temp = new Node(data);

            if (this.rear == null) {
                this.front = this.rear = temp;
                return;
            }

            this.rear.next = temp;
            this.rear = temp;
        }

        public auto_am_DataRecord dequeue() {
            if (this.front == null) {
                System.out.println("Queue is empty");
            }

            auto_am_DataRecord data = this.front.data;
            this.front = this.front.next;

            if (this.front == null) {
                this.rear = null;
            }
            return data;
        }

        public auto_am_DataRecord peek() {
            if (this.front == null) {
                System.out.println("Queue is empty");
            }
            return this.front.data;
        }

        public void display() {
            Node current = this.front;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
    }

    public static class Stack {
        Node top;

        public Stack() {
            this.top = null;
        }

        static class Node {
            auto_am_DataRecord data;
            Node next;

            public Node(auto_am_DataRecord data) {
                this.data = data;
            }
        }

        public void push(auto_am_DataRecord data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
        }

        public auto_am_DataRecord pop() {
            if (top == null) {
                System.out.println("Stack is empty");
            }
            auto_am_DataRecord data = top.data;
            top = top.next;
            return data;
        }

        public auto_am_DataRecord peek() {
            if (top == null) {
                System.out.println("Stack is empty");
            }
            return top.data;
        }

        public void display() {
            Stack.Node current = this.top;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
    }
}
