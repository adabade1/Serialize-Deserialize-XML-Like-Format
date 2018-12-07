package genericCheckpointing.util;

import genericCheckpointing.driver.Driver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XMLSerialization implements Strategy
{
    private FileProcessor fp;
    public XMLSerialization(FileProcessor fpIn)
    {
        fp = fpIn;
    }
    @Override
    public SerializableObject processInput(SerializableObject sObject) {
        String className = sObject.getClass().getName();
        Field allFieldNames[] = sObject.getClass().getDeclaredFields();
        Method get = null;
        fp.writeToFile("<DPSerialization>");
        fp.writeToFile("\n" + " <complexType xsi:type="+"\""+className +"\""+">");
        for(int i = 0 ; i < allFieldNames.length ; i++)
        {
            try {
                get = Class.forName(className).getMethod("get" + allFieldNames[i].getName());

                if (allFieldNames[i].getName().equals("myInt")) {
                        int val = Integer.parseInt(get.invoke(sObject).toString());
                        String dataType = "int";
                        if (val >= 10)
                            writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                    }

                if (allFieldNames[i].getName().equals("myOtherInt")) {
                    try {
                        int val = Integer.parseInt(get.invoke(sObject).toString());
                        String dataType = "int";
                        if (val >= 10)
                            writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else if (allFieldNames[i].getName().equals("myLong")) {

                        long val = Long.parseLong(get.invoke(sObject).toString());
                        if(val >= 10)
                        {
                            String dataType = "long";
                            writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                        }
                } else if (allFieldNames[i].getName().equals("myString")) {
                    String dataType = "string";
                    writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                } else if (allFieldNames[i].getName().equals("myBool")) {
                    String dataType = "boolean";
                    writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                } else if (allFieldNames[i].getName().equals("myOtherLong")) {
                    long val = Long.parseLong(get.invoke(sObject).toString());
                    if( val >= 10)
                    {
                        String dataType = "long";
                        writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                    }

                } else if (allFieldNames[i].getName().equals("myFloatT")) {
                    String dataType = "float";
                    writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                } else if (allFieldNames[i].getName().equals("myShortT")) {
                    String dataType = "short";
                    writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                } else if (allFieldNames[i].getName().equals("myCharT")) {
                    String dataType = "char";
                    writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                } else if (allFieldNames[i].getName().equals("myOtherDoubleT"))
                {
                    double val = Double.parseDouble(get.invoke(sObject).toString());
                    if(val >= 10)
                    {
                        String dataType = "double";
                        writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                    }
                } else if (allFieldNames[i].getName().equals("myDoubleT")) {
                    double val = Double.parseDouble(get.invoke(sObject).toString());
                    if(val >= 10)
                    {
                        String dataType = "double";
                        writeDataTypes(i, get, allFieldNames, sObject, className, dataType);
                    }
                }
            }
            catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        fp.writeToFile("\n </complexType>");
        fp.writeToFile("\n</DPSerialization>\n");
        return sObject;
    }
    public void writeDataTypes(int j, Method getIn, Field myAllFields[], SerializableObject sObjectIn, String clsName, String dataType)
    {
        try {
            getIn = Class.forName(clsName).getMethod("get" + myAllFields[j].getName());


            fp.writeToFile("\n"+"  <"+myAllFields[j].getName()+" xsi:type=\"xsd:"+dataType+"\">"+getIn.invoke(sObjectIn)+"</"+myAllFields[j].getName()+">");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
