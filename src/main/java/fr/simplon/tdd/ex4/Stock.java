package fr.simplon.tdd.ex4;

import java.math.BigDecimal;

public class Stock
{
    private int         mStockMax;
    private int         mStockCourant;
    private Fournisseur mFournisseur;

    public Stock(int pStockMax, Fournisseur pFournisseur)
    {
        if (pStockMax <= 0)
        {
            throw new IllegalArgumentException("Le stock maximum doit être strictement positif");
        }
        if (pFournisseur == null)
        {
            throw new IllegalArgumentException("Le fournisseur ne doit pas être nul");
        }
        mStockCourant = mStockMax = pStockMax;
        mFournisseur = pFournisseur;
    }
    public int getQuantiteMax()
    {
        return mStockMax;
    }

    public int getQuantiteRestante()
    {
        return mStockCourant;
    }

    public void destocker(int pQuantiteMax)
    {
        if (pQuantiteMax > mStockCourant)
        {
            throw new IllegalArgumentException("La quantité déstockée doit être inférieure ou égale au stock courant");
        }
        mStockCourant -= pQuantiteMax;
        if (new BigDecimal(mStockCourant).divide(BigDecimal.valueOf(mStockMax)).compareTo(BigDecimal.valueOf(0.1)) < 0)
        {
            reapprovisionner();
        }
    }

    private void reapprovisionner()
    {
        mStockCourant += mFournisseur.commander(mStockMax - mStockCourant);
    }

}
