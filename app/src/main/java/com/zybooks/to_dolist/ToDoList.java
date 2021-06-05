package com.zybooks.to_dolist;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    public static final String FILENAME = "todolist.txt";

    private Context mContext;
    private List<String> mTaskList;

    public ToDoList(Context context) {
        mContext = context;
        mTaskList = new ArrayList<>();
    }

    public void addItem(String item) throws IllegalArgumentException {
        mTaskList.add(item);
    }

    public String[] getItems() {
        String[] items = new String[mTaskList.size()];
        return mTaskList.toArray(items);
    }

    public void clear() {
        mTaskList.clear();
    }

    public void saveToFile() throws IOException {

        // Write list to internal file
        FileOutputStream outputStream = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        writeListToStream(outputStream);
    }

    public void readFromFile() throws IOException {

        BufferedReader reader = null;

        try {
            // Read in list from internal file
            FileInputStream inputStream = mContext.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            mTaskList.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                mTaskList.add(line);
            }
        }
        catch (FileNotFoundException ex) {
            // Ignore
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void writeListToStream(FileOutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        for (String item : mTaskList) {
            writer.println(item);
        }
        writer.close();
    }
}