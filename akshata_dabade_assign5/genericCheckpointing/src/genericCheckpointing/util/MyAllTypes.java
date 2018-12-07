package genericCheckpointing.util;

public interface MyAllTypes
{
     int accept(PrimeVisitor visitor);
     int accept(PalindromeVisitor visit);
}
