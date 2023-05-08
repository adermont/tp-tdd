package fr.simplon.tdd.ex1;

public class Palindrome
{
    public boolean isPalindrome(String s)
    {
        int start = 0;
        int end = s.length() - 1;

        while (start < end)
        {
            if (s.charAt(start) != s.charAt(end))
            {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
