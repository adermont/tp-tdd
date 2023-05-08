package fr.simplon.tdd.ex4;

public class Fournisseur
{
    private String mNomProduits;

    public Fournisseur(String pNomProduit)
    {
        if (pNomProduit == null || pNomProduit.trim().isEmpty()){
            throw new IllegalArgumentException("Le nom des produits ne doit pas être vide ou null");
        }
        mNomProduits = pNomProduit;
    }

    public String getNomProduits()
    {
        return mNomProduits;
    }

    public int commander(int pQuantite)
    {
        return pQuantite;
    }
}
