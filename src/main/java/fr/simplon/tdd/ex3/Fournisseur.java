package fr.simplon.tdd.ex3;

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
        if ( pQuantite < 0) {
            throw new IllegalArgumentException("On ne peut pas commander une quantité négative");
        }
        return pQuantite;
    }
}
