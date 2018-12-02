package genericCheckpointing.util;

import genericCheckpointing.driver.Driver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

public class XMLDeserialization implements Strategy
{

    @Override
    public SerializableObject processInput(SerializableObject sObject) throws IOException {
        String str2;

        str2 = Driver.handler.getLineFromFile();
//        System.out.println("input file :" + str2);
        String className;
        className = str2.split("[\"<> ]+")[3];
//        System.out.println(className);
        Class cls = null;
        /* Creating a class object according to the line read */
        try {
            sObject = (SerializableObject) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while(!(str2=Driver.handler.getLineFromFile()).equals("</DPSerialization>"))
        {
            if(str2.equals(" </complexType>"))
            {
                continue;
            }
            String fieldName = str2.split("<")[1].split(" ")[0];
//            System.out.println(fieldName);
            String fieldValue = str2.split(">")[1].split("<")[0];

            Field field = null;
            Method set = null;
            try {
                field = Class.forName(className).getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                continue;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Class fieldType = field.getType();
            try {
                set = Class.forName(className).getMethod("set" + fieldName, fieldType);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

//            System.out.println("Field name : "+ fieldName + " Data type:" + fieldType+ " value" + fieldValue);
            try
            {
                if(fieldName.equals("myInt"))
                {
                    int fldValueInt = Integer.parseInt(fieldValue);
                    set.invoke(sObject, fldValueInt);
                }
                else if((fieldName.equals("myString")))
                {
                    String fldValueString = fieldValue;
                    set.invoke(sObject, fldValueString);
                }
                else if((fieldName.equals("myLong")))
                {
                    long fldValueLong = Long.parseLong(fieldValue);
                    set.invoke(sObject, fldValueLong);
                }
                else if((fieldName.equals("myBool")))
                {
                    boolean fldValueBoolean = (new Boolean(fieldValue));
                    set.invoke(sObject, fldValueBoolean);
                }
                else if((fieldName.equals("myDoubleT")))
                {
                    double fldValueDouble = Double.parseDouble(fieldValue);
                    set.invoke(sObject, fldValueDouble);
                }
                else if((fieldName.equals("myFloatT")))
                {
                    float fldValueFloat = Float.parseFloat(fieldValue);
                    set.invoke(sObject, fldValueFloat);
                }
                else if(fieldName.equals("myShortT"))
                {
                    short fldValueShort = Short.parseShort(fieldValue);
                    set.invoke(sObject, fldValueShort);
                }
                else if(fieldName.equals("myCharT"))
                {
                    char fldValueChar = fieldValue.charAt(0);
                    set.invoke(sObject, fldValueChar);
                }
                else if(fieldName.equals("myOtherLong"))
                {
                    long fldValueLong1 = Long.parseLong(fieldValue);
                    set.invoke(sObject, fldValueLong1);
                }
                else if(fieldName.equals("myOtherDoubleT"))
                {
                    double fldValueDouble1 = Double.parseDouble(fieldValue);
                    set.invoke(sObject, fldValueDouble1);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

//            try
//            {
//                if(fieldType)
//
//            }

        }
        System.out.println(sObject);
        return sObject;
    }
}
