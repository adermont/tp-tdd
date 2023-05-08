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

    public void deposer(int pDepot)
    {
        if (pDepot < 0)
        {
            throw new IllegalArgumentException("Un dépôt doit obligatoirement être positif ou nul");
        }
        mSolde += pDepot;
    }

    public void retirer(int pRetrait)
    {
        if (pRetrait < 0)
        {
            throw new IllegalArgumentException("Un retrait doit être positif ou nul");
        }
        if (mSolde - pRetrait < 0)
        {
            throw new IllegalArgumentException("Solde insuffisant");
        }
        mSolde -= pRetrait;
    }

    public void transferer(CompteBancaire pDestination, int pMontant)
    {
        retirer(pMontant);
        try
        {
            pDestination.deposer(pMontant);
        }
        catch (Exception e)
        {
            // En cas de problème on remet les fonds à leur place !
            deposer(pMontant);
        }
    }
}
