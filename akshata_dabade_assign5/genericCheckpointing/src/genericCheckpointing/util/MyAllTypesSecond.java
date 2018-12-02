package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {

    public double myDoubleT;
    public float myFloatT;
    public short myShortT;
    public char myCharT;
    public double myOtherDoubleT;


    public MyAllTypesSecond()
    {
    }
    public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn, double myOtherDoubleTIn)
    {
        myDoubleT = myDoubleTIn;
        myFloatT = myFloatTIn;
        myShortT = myShortTIn;
        myCharT = myCharTIn;
        myOtherDoubleT = myOtherDoubleTIn;
    }

    public double getmyDoubleT() {
        return myDoubleT;
    }

    public void setmyDoubleT(double myDoubleTIn) {
        this.myDoubleT = myDoubleTIn;
    }

    public float getmyFloatT() {
        return myFloatT;
    }

    public void setmyFloatT(float myFloatTIn)
    {
        this.myFloatT = myFloatTIn;
    }

    public short getmyShortT()
    {
        return myShortT;
    }

    public void setmyShortT(short myShortTIn) {
        this.myShortT = myShortTIn;
    }

    public char getmyCharT() {
        return myCharT;
    }

    public void setmyCharT(char myCharTIn) {
        this.myCharT = myCharTIn;
    }

    public double getmyOtherDoubleT() {
        return myOtherDoubleT;
    }

    public void setmyOtherDoubleT(double myOtherDoubleTIn) {
        this.myOtherDoubleT = myOtherDoubleTIn;
    }

    @Override
    public String toString() {
        return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT
                + ", myCharT=" + myCharT + ", myOtherDoubleT=" + myOtherDoubleT +"]";
    }
}
