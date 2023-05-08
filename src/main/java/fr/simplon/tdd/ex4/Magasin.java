package fr.simplon.tdd.ex4;

import fr.simplon.tdd.ex2.CompteBancaire;

public class Magasin
{
    private CompteBancaire mCompte;
    private Stock          mStock;
    private String         mNomProduits;
    private int            mPrixDeBase;

    public Magasin(String pNomProduits, int pPrixDeBase)
    {
        this(pNomProduits, pPrixDeBase, 100);
    }

    public Magasin(String pNomProduits, int pPrixDeBase, int pStockMax)
    {
        super();
        mCompte = new CompteBancaire(100);
        mNomProduits = pNomProduits;
        mPrixDeBase = pPrixDeBase;
        mStock = new Stock(pStockMax, new Fournisseur(pNomProduits));
    }

    public String getNomProduits()
    {
        return mNomProduits;
    }

    public int getPrixDeBaseProduits()
    {
        return mPrixDeBase;
    }

    /**
     * @return prixFinal = prixDeBase x quantiteMax / quantiteDisponible
     */
    public int getPrixReelProduits()
    {
        return mPrixDeBase * mStock.getQuantiteMax() / mStock.getQuantiteRestante();
    }

    public void acheter(int pQuantite, CompteBancaire pClient)
    {
        int prix = getPrixReelProduits();
        mStock.destocker(pQuantite);
        pClient.transferer(this.mCompte, pQuantite * prix);
    }

    public int getStock()
    {
        return mStock.getQuantiteRestante();
    }

    public int getSolde()
    {
        return mCompte.getSolde();
    }
}
