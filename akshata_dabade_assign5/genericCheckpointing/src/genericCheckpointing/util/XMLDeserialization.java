package genericCheckpointing.util;

import genericCheckpointing.driver.Driver;

import java.io.IOException;

public class XMLDeserialization implements Strategy
{

    @Override
    public SerializableObject processInput(SerializableObject sObject) throws IOException {
        String str1;
        str1 = Driver.handler.getLineFromFile();
        System.out.println("input file :" + str1);
        return null;
    }
}
