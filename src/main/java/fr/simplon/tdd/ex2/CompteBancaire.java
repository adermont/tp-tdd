package fr.simplon.tdd.ex2;

public class CompteBancaire
{
    public CompteBancaire(int solde)
    {
        if (solde <= 0)
        {
            throw new IllegalArgumentException("Le solde doit être obligatoirement positif");
        }
    }
}
