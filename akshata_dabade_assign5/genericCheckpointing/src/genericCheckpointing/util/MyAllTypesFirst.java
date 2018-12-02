package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {

    public int myInt;
    public long myLong;
    public String myString;
    public boolean myBool;
    public int myOtherInt;
    public long myOtherLong;


    public MyAllTypesFirst()
    {
    }
    public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn, long myOtherLongIn)
    {
        myInt = myIntIn;
        myOtherInt = myOtherIntIn;
        myLong = myLongIn;
        myString = myStringIn;
        myBool = myBoolIn;
        myOtherLong = myOtherLongIn;
    }

    public int getmyInt() {
        return myInt;
    }

    public void setmyInt(int myInt) {
        this.myInt = myInt;
    }

    public long getmyLong() {
        return myLong;
    }

    public void setmyLong(long myLongIn)
    {
        this.myLong = myLongIn;
    }

    public String getmyString() {
        return myString;
    }

    public void setmyString(String myString) {
        this.myString = myString;
    }

    public Boolean getmyBool() {
        return myBool;
    }

    public void setmyBool(boolean myBoolean) {
        this.myBool = myBoolean;
    }

    public long getmyOtherLong() {
        return myOtherLong;
    }

    public void setmyOtherLong(long myOtherLongIn) {
        this.myOtherLong = myOtherLongIn;
    }

    public int getmyOtherInt() {
        return myOtherInt;
    }

    public void setmyOtherInt(int myOtherInt1) {
        this.myOtherInt = myOtherInt1;
    }

    @Override
    public String toString() {
        return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong + ", myString=" + myString + ", myBool="
                + myBool + ", myOtherInt=" + myOtherInt + ", myOtherLong=" + myOtherLong + "]";
    }
}
