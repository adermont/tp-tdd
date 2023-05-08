package fr.simplon.tdd.ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlaindromeTest
{
    @Test
    public void testIsPalindrome(){
        Palindrome palindrome = new Palindrome();
        Assertions.assertTrue(palindrome.isPalindrome("radar"));
    }
}
