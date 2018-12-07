package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.*;


import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Scanner;

public class StoreRestoreHandler implements InvocationHandler {
    private FileProcessor fp;
    public StoreRestoreHandler(FileProcessor fpIn)
    {
        fp = fpIn;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("here in invoke");
        if(method.getName().equals("readObj"))
        {
            if(args[0].equals("XML"))
            {
                SerializableObject obj = null;
                Strategy deserial = new XMLDeserialization(fp);
                obj = deserializeData(deserial);
                return obj;
            }
        }
        else if(method.getName().equals("writeObj"))
        {
            if(args[2].equals("XML"))
            {
                Strategy serial = new XMLSerialization(fp);
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
        return sObject;
    }
}
