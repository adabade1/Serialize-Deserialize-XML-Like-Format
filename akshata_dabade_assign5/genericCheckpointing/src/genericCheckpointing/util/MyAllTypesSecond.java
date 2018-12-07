package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject implements MyAllTypes{

    private double myDoubleT;
    private float myFloatT;
    private short myShortT;
    private char myCharT;
    private double myOtherDoubleT;


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
    public String toString()
    {
        return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT + ", myCharT=" + myCharT + ", myOtherDoubleT=" + myOtherDoubleT + "]";
    }

@Override
	public int hashCode() {
	return (int)this.getmyDoubleT() * 13;	}

    @Override
    public boolean equals(Object objectIn)
    {
        if(this.myDoubleT == ((MyAllTypesSecond) objectIn).getmyDoubleT() && this.myCharT == ((MyAllTypesSecond) objectIn).getmyCharT() && this.myFloatT == ((MyAllTypesSecond) objectIn).getmyFloatT() && this.myShortT == ((MyAllTypesSecond) objectIn).getmyShortT() && this.myOtherDoubleT == ((MyAllTypesSecond) objectIn).getmyOtherDoubleT())
        {
            return true;
        }
        return false;
    }

    @Override
    public int accept(PrimeVisitor visitor)
    {
        return visitor.visit(this);
    }

    @Override
    public int accept(PalindromeVisitor visitor)
    {
        return visitor.visit(this);
    }
}
