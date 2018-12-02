package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.*;


import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Scanner;

public class StoreRestoreHandler implements InvocationHandler {
     public String fileName;
//    FileWriter file = null;
//    FileReader fileReader;
//    BufferedReader brReader;
//    FileProcessor fp ;
public BufferedReader input;
    FileWriter fileWriter;
public File f;



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("here in invoke");
        if(method.getName().equals("readObj"))
        {
            if(args[0].equals("XML"))
            {
                SerializableObject obj = null;
                Strategy deserial = new XMLDeserialization();
                obj = deserializeData(deserial);
                return obj;
            }
        }
        else if(method.getName().equals("writeObj"))
        {
            if(args[2].equals("XML"))
            {
//                System.out.println(args[0]);
                Strategy serial = new XMLSerialization();
                serializeData((SerializableObject) args[0], serial);
            }
        }

        return null;
    }
    public void serializeData(SerializableObject sObject, Strategy sStrategy) throws IOException {
        sStrategy.processInput(sObject);
    }

    public SerializableObject deserializeData(Strategy dStrategy) throws IOException {
        SerializableObject sObject = null;
        sObject = dStrategy.processInput(sObject);
//       System.out.println("in handler :"+ sObject.toString());
        return sObject;
    }
    public void initializeWrite() {

        try {
            fileWriter = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setFileName(String filenameIn) throws FileNotFoundException {
        fileName = filenameIn;
        System.out.println("file name set");
    }

    public void initialziseReadWrite()
    {
        f = new File(fileName);
    }
    public void openFile() throws FileNotFoundException {

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
    public void closeFile()
    {
        try {
            System.out.println("Closing file");
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeToFile(String str)
    {

        try {
//            System.out.println("writing to file" + str);
            fileWriter.write(str);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
