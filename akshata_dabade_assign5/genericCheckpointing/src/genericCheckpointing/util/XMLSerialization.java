package genericCheckpointing.util;

import genericCheckpointing.driver.Driver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XMLSerialization implements Strategy
{
    @Override
    public SerializableObject processInput(SerializableObject sObject)
    {
//       System.out.println(sObject.toString().split(" ")[0]);
        String className = sObject.getClass().getName();
        Field allFieldNames[] = sObject.getClass().getDeclaredFields();
        Method get = null;
//        System.out.println(allFieldNames[0]);
        Driver.handler.writeToFile("<DPSerialization>");
        Driver.handler.writeToFile("\n" + " <complexType xsi:type="+"\""+className +"\""+">");
        for(int i = 0 ; i < allFieldNames.length ; i++)
        {


//                if((sObject.getClass().getMethod("get"+allFieldNames[i].getName()).getReturnType()) == (Integer.TYPE))
//                {
                    writeDataTypes(i, get, allFieldNames, sObject, className);

//                    get = Class.forName(className).getMethod("get" + allFieldNames[i].getName());
//                    Driver.handler.writeToFile("\n"+"  <"+allFieldNames[i].getName()+" xsi:type=\"xsd:int\">"+get.invoke(sObject)+"</"+allFieldNames[i].getName()+">");
//                }
//                else if((sObject.getClass().getMethod("get"+allFieldNames[i].getName()).getReturnType()) == (long.class))
//                {
//                    writeDataTypes(i, get, allFieldNames, sObject, className);
////                    get = Class.forName(className).getMethod("get" + allFieldNames[i].getName());
////                    Driver.handler.writeToFile("\n"+"  <"+allFieldNames[i].getName()+" xsi:type=\"xsd:int\">"+get.invoke(sObject)+"</"+allFieldNames[i].getName()+">");
//                }



        }

        Driver.handler.writeToFile("\n </complexType>");
        Driver.handler.writeToFile("\n</DPSerialization>\n");
        return sObject;
    }
    public void writeDataTypes(int j, Method getIn, Field myAllFields[], SerializableObject sObjectIn, String clsName)
    {
        try {
            getIn = Class.forName(clsName).getMethod("get" + myAllFields[j].getName());
            Driver.handler.writeToFile("\n"+"  <"+myAllFields[j].getName()+" xsi:type=\"xsd:int\">"+getIn.invoke(sObjectIn)+"</"+myAllFields[j].getName()+">");
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
