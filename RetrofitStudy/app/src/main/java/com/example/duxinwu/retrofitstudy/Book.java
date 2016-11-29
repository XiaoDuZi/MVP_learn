package com.example.duxinwu.retrofitstudy;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class Book {

    @SerializedName("name")
    private String name;

    public static Book objectFromData(String string){
        return new Gson().fromJson(string,Book.class);
    }

    public static List<Book> arrayBookFromData(String string){
        Type listType=new TypeToken<ArrayList<Book>>(){
        }.getType();
        return new Gson().fromJson(string,listType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
