package genericCheckpointing.util;

public class PrimeVisitorImpl implements PrimeVisitor
{
    @Override
    public int visit(MyAllTypesFirst matf)
    {
        int n = matf.getmyInt(),i,flag = 1,count = 0;
		
        for(i=2; i<=n-1; i++)
        {
            if(n%i==0)
            {
                flag = 0;
                break;
            }
        }
		
        if (n==0 || n==1)
        {
            flag=0;
        }
        if(flag == 1)
            count++;

        n = matf.getmyOtherInt();

        if (n==0 || n==1)
        {
            return count;
        }

        for(i=2; i<=n-1; i++)
        {
            if(n%i==0)
            {
                return count;
            }
        }
        return count+1;

    }

    @Override
    public int visit(MyAllTypesSecond mats)
    {
       return 0;
    }
}
