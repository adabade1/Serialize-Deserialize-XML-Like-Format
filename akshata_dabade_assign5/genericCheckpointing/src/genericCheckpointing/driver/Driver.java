
package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.*;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.util.ArrayList;

// import the other types used in this file

public class Driver {
    public static StoreRestoreHandler handler = new StoreRestoreHandler();
    public static void main(String[] args) throws Exception {
        String arg1 = "deser";
        int arg2 = 3;
        String arg3 = "C:\\Users\\Akshata\\Desktop\\checkpointfile.txt";
        int NUM_OF_OBJECTS;
        NUM_OF_OBJECTS = arg2;
        // FIXME: read the value of checkpointFile from the command line

        ProxyCreator pc = new ProxyCreator();
        FileProcessor fp = new FileProcessor(arg3);
        handler.setFileName(arg3);

//        handler.openFile();

        // create an instance of StoreRestoreHandler (which implements
        // the InvocationHandler

        // create a proxy
        StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
                new Class[] {
                        StoreI.class, RestoreI.class
                },
                new StoreRestoreHandler()
        );

        // FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file

        MyAllTypesFirst myFirst;
        MyAllTypesSecond mySecond;

        // Use an if/switch to proceed according to the command line argument
        // For deser, just deserliaze the input file into the data structure and then print the objects
        // The code below is for "serdeser" mode
        // For "serdeser" mode, both the serialize and deserialize functionality should be called.

        // create a data structure to store the objects being serialized
        // NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
        // passed as "N" from the command line.
//
//        for (int i=0; i<NUM_OF_OBJECTS; i++) {
//
//            // FIXME: create these object instances correctly using an explicit value constructor
//            // use the index variable of this loop to change the values of the arguments to these constructors
//            myFirst = new MyAllTypesFirst(...);
//            mySecond = new MyAllTypesSecond(..);
//
//            // FIXME: store myFirst and mySecond in the data structure
//            // authID (13 and 17) is not being used in the assignment, but
//            // is left for future use.
//            ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
//            ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");
//
//        }

        SerializableObject myRecordRet = null;

        // create a data structure to store the returned objects
        ArrayList <SerializableObject> objects = new ArrayList();
        if(arg1.equals("deser"))
        {
            for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

                myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                objects.add(myRecordRet);
                // FIXME: store myRecordRet in the vector (or arrayList)
            }

            // FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)

            // FIXME: compare and confirm that the serialized and deserialzed objects are equal.
            // The comparison should use the equals and hashCode methods. Note that hashCode
            // is used for key-value based data structures

        }



        // FIXME
        // Create an instance of the PrimeVisitorImpl and use it to determine the number of unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond


        // FIXME
        // Create an instance of the PalindromeVisitorImpl and use it to determine the number of unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond

    }
}