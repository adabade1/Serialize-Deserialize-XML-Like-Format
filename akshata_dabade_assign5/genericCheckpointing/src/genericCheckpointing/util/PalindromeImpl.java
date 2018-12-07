package genericCheckpointing.util;

public class PalindromeImpl implements PalindromeVisitor{

    @Override
    public int visit(MyAllTypesFirst matf)
    {
        String checkPalindrome = matf.getmyString();
        String reverse;
        try {
             reverse = new StringBuffer(checkPalindrome).reverse().toString();
        }
        catch (Exception e)
        {
            return  0;
        }
        if (checkPalindrome.equals(reverse))
            return 1;
        return 0;
    }

    @Override
    public int visit(MyAllTypesSecond mats)
    {
        return 0;
    }
}
