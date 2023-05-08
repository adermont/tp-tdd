package fr.simplon.tdd.ex2;

public class CompteBancaire
{
    private int mSolde;

    public CompteBancaire(int solde)
    {
        if (solde <= 0)
        {
            throw new IllegalArgumentException("Le solde doit être obligatoirement positif");
        }
        mSolde = solde;
    }

    public int getSolde()
    {
        return mSolde;
    }
}
