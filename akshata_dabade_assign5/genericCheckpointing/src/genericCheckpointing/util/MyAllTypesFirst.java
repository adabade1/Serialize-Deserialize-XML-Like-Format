package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject implements MyAllTypes{

    private int myInt;
    private long myLong;
    private String myString;
    private boolean myBool;
    private int myOtherInt;
    private long myOtherLong;


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

    public boolean getmyBool() {
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
    public String toString()
    {
        return "MyAllTypesFirst [myInt:" + myInt + ", myOtherInt:" + myOtherInt + ", myLong:" + myLong + ", myString:" + myString + ", myBool:" + myBool + ", myOtherLong:" + myOtherLong + "]";
    }

@Override
	public int hashCode() {
	return this.getmyInt() * 13;
	}


    @Override
    public boolean equals(Object objectIn)
    {
        if(this.myBool == ((MyAllTypesFirst) objectIn).getmyBool() && this.myString == ((MyAllTypesFirst) objectIn).getmyString() && this.myOtherInt == ((MyAllTypesFirst) objectIn).getmyOtherInt() && this.myLong == ((MyAllTypesFirst) objectIn).getmyLong() && this.myOtherLong == ((MyAllTypesFirst) objectIn).getmyOtherLong() && this.myInt == ((MyAllTypesFirst) objectIn).getmyInt())
        {
            return true;
        }
        return false;
    }

    @Override
    public int accept(PrimeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int accept(PalindromeVisitor visitor) {
        return visitor.visit(this);
    }
}
