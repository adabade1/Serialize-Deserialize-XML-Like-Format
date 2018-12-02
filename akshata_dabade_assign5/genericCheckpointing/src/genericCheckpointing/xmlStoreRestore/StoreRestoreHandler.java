package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.Strategy;
import genericCheckpointing.util.XMLDeserialization;


import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Scanner;

public class StoreRestoreHandler implements InvocationHandler {
      String fileName;
//    FileWriter file = null;
//    FileReader fileReader;
//    BufferedReader brReader;
//    FileProcessor fp ;
private BufferedReader input;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("here in invoke");
        if(method.getName().equals("readObj"))
        {
            if(args[0].equals("XML"))
            {
                Strategy deserial = new XMLDeserialization();
                deserializeData(deserial);
            }
        }

        return null;
    }
    public void serializeData(SerializableObject sObject, Strategy sStrategy) throws IOException {
        sStrategy.processInput(sObject);
    }

    public Object deserializeData(Strategy dStrategy) throws IOException {
        SerializableObject sObject = null;
        Object des = dStrategy.processInput(sObject);
        return des;
    }

    public void setFileName(String filenameIn) throws FileNotFoundException {
        fileName = filenameIn;

    }
    public void openFile() throws FileNotFoundException {
        File f = new File(fileName);
        input = new BufferedReader(new FileReader(f));
    }
    public String getLineFromFile() throws IOException {
        String line;
        if((line = input.readLine()) != null)
        {
            if (line.equals("<DPSerialization>"))
            {
                line = input.readLine();
            }
            return line;
        }
        line = null;
        return line;
    }
}
