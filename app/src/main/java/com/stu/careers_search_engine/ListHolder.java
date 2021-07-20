package com.stu.careers_search_engine;

import java.util.ArrayList;

/*
Using the listHolder class until can set up DB. Get all functionality working correctly first
 */

public class ListHolder {

    final ArrayList<String> username = new ArrayList<>();
    





    private ListHolder() {
    }

    static ListHolder getInstance() {
        if (instance == null) {
            instance = new ListHolder();
        }
        return instance;
    }

    private static ListHolder instance;
}

