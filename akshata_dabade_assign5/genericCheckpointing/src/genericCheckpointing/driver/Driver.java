
package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.*;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

// import the other types used in this file

public class Driver {
    public static StoreRestoreHandler handler = new StoreRestoreHandler();
    public static void main(String[] args) throws Exception {
        String arg1 = "serdeser";
        int arg2 = 3;
        String arg3 = "C:\\Users\\Akshata\\Desktop\\checkpointfile.txt";
        String arg4 =  "C:\\Users\\Akshata\\Desktop\\checkpointfile1.txt";
        int NUM_OF_OBJECTS;
        NUM_OF_OBJECTS = arg2;
        // FIXME: read the value of checkpointFile from the command line

        ProxyCreator pc = new ProxyCreator();
        FileProcessor fp = new FileProcessor(arg3);


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
        if(arg1.equals("serdeser"))
        {
            handler.setFileName(arg3);
            handler.initialziseReadWrite();
            handler.initializeWrite();
            for (int i=0; i<NUM_OF_OBJECTS; i++) {

                // FIXME: create these object instances correctly using an explicit value constructor
                // use the index variable of this loop to change the values of the arguments to these constructors
                Random rand = new Random();
                int myIntIn = rand.nextInt(50) + 1;
                long myLongIn = new Random().nextLong();
                byte[] array = new byte[7]; // length is bounded by 7
//                new Random().nextBytes(array);
//                String myStringIn = new String(array, Charset.forName("UTF-8"));
                String myStringIn = "Learning DP";
                boolean myBoolIn = rand.nextBoolean();
                int myOtherIntIn = rand.nextInt(50) + 1;
                long myOtherLongIn = new Random().nextLong();

                double myDoubleTIn = rand.nextDouble();
                float myFloatTIn = rand.nextFloat();
                short myShortTIn = (short)(Math.random() * 1000 + 200);
                char myCharTIn = (char) (rand.nextInt(26) + 'a');
                double myOtherDoubleTIn = rand.nextDouble();

                myFirst = new MyAllTypesFirst(myIntIn,myLongIn,myStringIn,myBoolIn,myOtherIntIn,myOtherLongIn);
                mySecond = new MyAllTypesSecond(myDoubleTIn,myFloatTIn,myShortTIn,myCharTIn,myOtherDoubleTIn);

                // FIXME: store myFirst and mySecond in the data structure
                // authID (13 and 17) is not being used in the assignment, but
                // is left for future use.
                ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
                ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");

            }
        }



        if(arg1.equals("deser"))
        {
            SerializableObject myRecordRet;
            handler.setFileName(arg3);
            handler.initialziseReadWrite();
            handler.openFile();


            // create a data structure to store the returned objects
            ArrayList<SerializableObject> objects = new ArrayList<SerializableObject>();
            for (int j=0; j<2*NUM_OF_OBJECTS; j++)
            {
                myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                objects.add(myRecordRet);
                System.out.println(objects.get(j));
            }
            handler.closeFile();
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