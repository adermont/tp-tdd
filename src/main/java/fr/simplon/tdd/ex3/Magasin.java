package fr.simplon.tdd.ex3;

public class Magasin
{
    private Stock mStock;
    private String mNomProduits;
    private int mPrixDeBase;

    public Magasin(String pNomProduits, int pPrixDeBase)
    {
        this(pNomProduits, pPrixDeBase, 100);
    }

    public Magasin(String pNomProduits, int pPrixDeBase, int pStockMax)
    {
        super();
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

    public void acheter(int pQuantite)
    {
        mStock.destocker(pQuantite);
    }

    public int getStock()
    {
        return mStock.getQuantiteRestante();
    }
}
