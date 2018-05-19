package com.phamtrong.api.test;

import com.phamtrong.model.BookTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class reviewHasMap {

    @Test
    public void checkHasMap(){
//        BookTest b1 = returnBookModel(101,"Let us C","Yashwant Kanetkar","BPB",8);
//        BookTest b2 = returnBookModel(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
//        BookTest b3 = returnBookModel(103,"Operating System","Galvin","Wiley",6);
//        Map<Integer,BookTest> map = new HashMap<Integer, BookTest>();
//        map.put(1,b1);
//        map.put(2,b2);
//        map.put(3,b3);
//        for(Map.Entry<Integer,BookTest> item: map.entrySet()){
//            System.out.println(item.getKey() + " detail");
//            BookTest value = item.getValue();
//            System.out.println(value.getId() + " " + value.getName() + " " + value.getAuthor() + " "+ value.getPublisher() + " "+ value.getQuantity());
//        }

        BookTest b1 = returnBookModel(101,"Let us C","Yashwant Kanetkar","BPB",8);
        BookTest b2 = returnBookModel(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
        BookTest b3 = returnBookModel(103,"Operating System","Galvin","Wiley",6);
        BookTest b4 = returnBookModel(103,"java web","Galvin","Wiley",6);
        Map<Integer,BookTest> map = new HashMap<Integer, BookTest>();
        map.put(1,b1);
        map.put(2,b2);
        map.put(3,b3);
        map.put(3,b4);
        for(Map.Entry<Integer,BookTest> item: map.entrySet()){
            System.out.println(item.getKey() + " detail");
            BookTest value = item.getValue();
            System.out.println(value.getId() + " " + value.getName() + " " + value.getAuthor() + " "+ value.getPublisher() + " "+ value.getQuantity());
        }
    }

    private BookTest returnBookModel(int id, String name,String author, String publisher, int quantity){
        BookTest b22 = new BookTest();
        b22.setId(id);
        b22.setName(name);
        b22.setAuthor(author);
        b22.setPublisher(publisher);
        b22.setQuantity(quantity);
        return b22;
    }
}
