
package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.*;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

// import the other types used in this file

public class Driver {
    public static void main(String[] args) throws Exception {


        if (args.length != 3 || (args[0].equals("${arg0}")) || (args[1].equals("${arg1}")) || (args[2].equals("${arg2}"))) {
            System.out.println("Error: Incorrect number of arguments. Program accepts 3 arguments. Mode(serdesr/desr), no of objects, file path");
            System.exit(0);
        }
        
        String arg1 = args[0];
        if(!arg1.equalsIgnoreCase("deser") && !arg1.equalsIgnoreCase("serdeser"))
        {
            System.out.println("Mode should be either serdeser or deser");
            System.exit(0);
        }
        arg1 = arg1.toLowerCase();
        
        int arg2=0;
        try{
            arg2 = Integer.parseInt(args[1]);
        }catch(NumberFormatException e)
        {
            System.out.println("No of objects should be an integer.");
            System.exit(0);
        }
//        String arg3 = "C:\\Users\\Akshata\\akshata_dabade_assign5\\genericCheckpointing\\checkpointfile.txt";
        String arg3 = args[2];
       if(arg1.equals("deser"))
       {
           File file = new File(arg3);
           if(!file.exists())
           {
               System.out.println("\nFile does not exist");
               System.exit(0);
           }
           if(file.length()==0)
           {
               System.out.println("\nFile is empty. Nothing to deserialize.");
               System.exit(0);
           }
       }
       if(arg1.equals("serdeser"))
       {
           File file = new File(arg3);
           if(file.exists() && file.length()!=0)
           {
               System.out.println("\nFile needs to be empty.");
               System.exit(0);
           }
		else if(!file.exists())
			file.createNewFile();
       }



        // FIXME: read the value of checkpointFile from the command line

        ProxyCreator pc = new ProxyCreator();
        FileProcessor fp = new FileProcessor(arg3);
        // create an instance of StoreRestoreHandler (which implements
        // the InvocationHandler

        // create a proxy
        StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
                new Class[] {
                        StoreI.class, RestoreI.class
                },
                new StoreRestoreHandler(fp)
        );


        // FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file
        MyAllTypesFirst myFirst;
        MyAllTypesSecond mySecond;

        // Use an if/switch to proceed according to the command line argument
        // For deser, just deserliaze the input file into the data structure and then print the objects
        // The code below is for "serdeser" mode
        // For "serdeser" mode, both the serialize and deserialize functionality should be called.

        // create a data structure to store the objects being serialized
        // arg2 refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
        // passed as "N" from the command line.
//
        if(arg1.equals("serdeser"))
        {

            ArrayList<SerializableObject> oldList = new ArrayList<SerializableObject>();
            for (int i=0; i<arg2; i++) {

                // FIXME: create these object instances correctly using an explicit value constructor
                // use the index variable of this loop to change the values of the arguments to these constructors
                Random rand = new Random();
                int myIntIn = rand.nextInt(50) + 1;
                long myLongIn = new Random().nextLong();

                String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                        + "abcdefghijklmnopqrstuvwxyz"
                        + "0123456789";
                String myStringIn = new Random().ints(10, 0, chars.length())
                        .mapToObj(loc -> "" + chars.charAt(loc))
                        .collect(Collectors.joining());
                boolean myBoolIn = rand.nextBoolean();
                int myOtherIntIn = rand.nextInt(50) + 1;
                long myOtherLongIn = new Random().nextLong();
                double myDoubleTIn = rand.nextDouble();
                float myFloatTIn = rand.nextFloat();
                short myShortTIn = (short) (Math.random() * 1000 + 200);
                char myCharTIn = (char) (rand.nextInt(26) + 'a');
                double myOtherDoubleTIn = rand.nextDouble();
                myFirst = new MyAllTypesFirst(myIntIn, myLongIn, myStringIn, myBoolIn, myOtherIntIn, myOtherLongIn);
                mySecond = new MyAllTypesSecond(myDoubleTIn, myFloatTIn, myShortTIn, myCharTIn, myOtherDoubleTIn);
                oldList.add(myFirst);
                oldList.add(mySecond);
                ((StoreI) cpointRef).writeObj(myFirst, 13, "XML");
                ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");
            }
            SerializableObject myRecordRet;
            // create a data structure to store the returned objects
            ArrayList<SerializableObject> newList = new ArrayList<SerializableObject>();
            int wrong = 0;
            for (int j=0; j<2*arg2; j++)
            {
                myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                newList.add(myRecordRet);
            }
            for(int k = 0; k < newList.size() ; k++)
            {
                if(!(newList.get(k).equals(oldList.get(k))))
                {
                    wrong++;
                }
                System.out.println("Serialized   : "+oldList.get(k).toString());
                System.out.println("Deserialized : "+newList.get(k).toString());
                System.out.println("\n");
            }


            System.out.println("Number of mismatched objects: " + wrong);

            int palindromeCount = 0;
            for(int k=0 ; k < oldList.size() ; k++)
            {
                PalindromeVisitor palindrome = new PalindromeImpl();
                Method j = oldList.get(k).getClass().getMethod("accept", PalindromeVisitor.class);
                String i =  j.invoke(oldList.get(k),palindrome).toString();
                int z = Integer.parseInt(i);
                palindromeCount = palindromeCount  + z;
            }
            System.out.println("Palindrome Count for Serialized objects : " + palindromeCount);

            int primeCount = 0;
            for(int k=0 ; k < oldList.size() ; k++)
            {
                PrimeVisitor prime = new PrimeVisitorImpl();
                Method j = oldList.get(k).getClass().getMethod("accept", PrimeVisitor.class);
                String i =  j.invoke(oldList.get(k),prime).toString();
                int z = Integer.parseInt(i);
                primeCount = primeCount + z;
            }
            System.out.println("Prime Count for Serialized objects : " + primeCount);

//
//                // FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
//
//                // FIXME: compare and confirm that the serialized and deserialzed objects are equal.
//                // The comparison should use the equals and hashCode methods. Note that hashCode
//                // is used for key-

        }
        else if(arg1.equals("deser"))
        {
            SerializableObject myRecordRet;

            ArrayList<SerializableObject> objects = new ArrayList<SerializableObject>();
            System.out.println("Deserialized objects are : \n");
            for(int j=0; j<arg2; j++)
            {
                try {
                    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                    objects.add(myRecordRet);
                    System.out.println(objects.get(j));
                }
                catch(Exception e){
                    System.out.println("\nTotal objects in file < Number of objects to be read.");
                    System.exit(0);
                }
            }

            System.out.println("\n");
            int palindromeCount = 0;
            for(int k=0 ; k < objects.size() ; k++)
            {
                PalindromeVisitor palindrome = new PalindromeImpl();
                Method j = objects.get(k).getClass().getMethod("accept", PalindromeVisitor.class);
                String i =  j.invoke(objects.get(k),palindrome).toString();
                int z = Integer.parseInt(i);
                palindromeCount = palindromeCount  + z;
            }
            System.out.println("Palindrome Count for Deserialized objects : " + palindromeCount);

            int primeCount = 0;
            for(int k=0 ; k < objects.size() ; k++)
            {
                PrimeVisitor prime = new PrimeVisitorImpl();
                Method j = objects.get(k).getClass().getMethod("accept", PrimeVisitor.class);
                String i =  j.invoke(objects.get(k),prime).toString();
                int z = Integer.parseInt(i);
                primeCount = primeCount + z;
            }
            System.out.println("Prime Count for Deserialized objects : " + primeCount);
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