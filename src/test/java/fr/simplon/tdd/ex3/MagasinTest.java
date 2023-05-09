package fr.simplon.tdd.ex3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

/**
 * On peut créer un Fournisseur en passant en paramètre le nom d'un produit (ne doit pas être vide ou nul).
 * <p>
 * Une méthode permet de consulter le nom des produits vendus par un Fournisseur.
 * <p>
 * Un Fournisseur garantit qu'il peut livrer autant de produits qu'on souhaite. En d'autres termes, vous écrirez une
 * méthode commander(int nbProduits) qui retourne exactement le même nombre.
 * <p>
 * On peut créer un Stock à partir d'un nombre entier (qui représente sa capacité max, strictement positive) et d'un
 * objet de type Fournisseur.
 * <p>
 * La quantité initiale d'un stock est égale à sa capacité maximum.
 * <p>
 * Une méthode permet de diminuer la taille d'un stock de N produits, à condition que N soit inférieur à la quantité
 * restante de produits en stock.
 * <p>
 * Un Stock peut augmenter sa taille automatiquement en passant une commande auprès du fournisseur lorsque la quantité
 * disponible descend en-dessous de 10% de sa capacité max. Ceci garantit qu'il n'est jamais possible d'avoir un stock
 * nul entre deux achats.
 * <p>
 * On peut "ouvrir" un Magasin en précisant le nom des produits vendus et leur prix de base. Des méthodes de la classe
 * Magasin permettent de récupérer le nom des produits et leur prix de base.
 * <p>
 * La classe Magasin permet de connaître le prix des produits. La formule utilisée pour calculer le prix est proche
 * d'une formule-type "SNCF":
 * <p>
 * prixFinal = prixDeBase x quantiteMax / quantiteDisponible
 * <p>
 * Par exemple si stockMax=500, stockCourant=100, prixDeBase=50€, alors: prixFinal = 50 * 500 / 100 = 250€
 * <p>
 * La classe Magasin permet d'acheter des produits: quand un client achète un produit, le stock diminue d'autant de
 * produits achetés. Bien sûr, on ne peut pas acheter plus que la quantité de produits disponibles en stock.
 */
public class MagasinTest
{
    /**
     * On peut créer un Fournisseur en passant en paramètre le nom d'un produit (ne doit pas être vide ou nul).
     */
    @Test
    public void testCreerFournisseur()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Fournisseur(""));
        Assertions.assertDoesNotThrow(() -> new Fournisseur("Playstation"));
    }

    /**
     * Une méthode permet de consulter le nom des produits vendus par un Fournisseur.
     */
    @Test
    public void testRecupererLeNomDesProduitsVendusParLeFournisseur()
    {
        String nomProduit = UUID.randomUUID().toString();
        Assertions.assertEquals(nomProduit, new Fournisseur(nomProduit).getNomProduits());
    }

    /**
     * Un Fournisseur garantit qu'il peut livrer autant de produits qu'on souhaite. En d'autres termes, vous écrirez une
     * méthode commander(int nbProduits) qui retourne exactement le même nombre.
     */
    @Test
    public void testCommande()
    {
        for (int i = 0; i < 100; i++)
        {
            int quantite = new Random().nextInt(1, Integer.MAX_VALUE);
            Assertions.assertEquals(quantite, new Fournisseur("Playstation").commander(quantite));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Fournisseur("Playstation").commander(quantite*-1));
        }
    }

    /**
     * On peut créer un Stock à partir d'un nombre entier (qui représente sa capacité max, strictement positive) et d'un
     * objet de type Fournisseur.
     */
    @Test
    public void testCreationDuStock()
    {
        Fournisseur fournisseur = new Fournisseur("Playstation");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Stock(0, fournisseur));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Stock(-1, fournisseur));
        Assertions.assertDoesNotThrow(() -> new Stock(10, fournisseur));
    }

    /**
     * La quantité initiale d'un stock est égale à sa capacité maximum.
     */
    @Test
    public void testQuantiteInitialeDuStock()
    {
        Fournisseur fournisseur = new Fournisseur("Playstation");
        int quantiteMax = 100;
        Stock stock = new Stock(quantiteMax, fournisseur);
        Assertions.assertEquals(quantiteMax, stock.getQuantiteRestante());
    }

    /**
     * Une méthode permet de diminuer la taille d'un stock de N produits, à condition que N soit inférieur à la quantité
     * restante de produits en stock.
     */
    @Test
    public void testSortieDuStock()
    {
        Fournisseur fournisseur = new Fournisseur("Playstation");
        int quantiteMax = 100;
        Stock stock = new Stock(quantiteMax, fournisseur);
        Assertions.assertDoesNotThrow(() -> stock.destocker(quantiteMax));
    }

    /**
     * Une méthode permet de diminuer la taille d'un stock de N produits, à condition que N soit inférieur à la quantité
     * * restante de produits en stock.
     */
    @Test
    public void testErreurSiSortieDuStockSuperieureALaQuantiteRestante()
    {
        Fournisseur fournisseur = new Fournisseur("Playstation");
        int quantiteMax = 1;
        Stock stock = new Stock(quantiteMax, fournisseur);
        Assertions.assertThrows(IllegalArgumentException.class, () -> stock.destocker(quantiteMax + 1));
    }

    /**
     * Un Stock peut augmenter sa taille automatiquement en passant une commande auprès du fournisseur lorsque la
     * quantité disponible descend en-dessous de 10% de sa capacité max. Ceci garantit qu'il n'est jamais possible
     * d'avoir un stock nul entre deux achats.
     */
    @Test
    public void testRenouvellementAutomatiqueDuStock()
    {
        Fournisseur fournisseur = new Fournisseur("Playstation");
        int quantiteMax = 100;
        Stock stock = new Stock(quantiteMax, fournisseur);
        stock.destocker(10);
        Assertions.assertEquals(90, stock.getQuantiteRestante());
        stock.destocker(80);
        Assertions.assertEquals(10, stock.getQuantiteRestante());
        stock.destocker(1);
        Assertions.assertTrue(stock.getQuantiteRestante() > 9);
    }

    /**
     * On peut "ouvrir" un Magasin en précisant le nom des produits vendus et leur prix de base. Des méthodes de la
     * classe Magasin permettent de récupérer le nom des produits et leur prix de base.
     */
    @Test
    public void testCreationMagasin()
    {
        String nomProduits = "Playstation";
        int prixDeBase = 350;
        Assertions.assertDoesNotThrow(() -> new Magasin(nomProduits, prixDeBase));
        Assertions.assertEquals(nomProduits, new Magasin(nomProduits, prixDeBase).getNomProduits());
        Assertions.assertEquals(prixDeBase, new Magasin(nomProduits, prixDeBase).getPrixDeBaseProduits());
    }

    /**
     * La classe Magasin permet de connaître le prix des produits. La formule utilisée pour calculer le prix est proche
     * d'une formule-type "SNCF":
     * <pre>
     *  prixFinal = prixDeBase x quantiteMax / quantiteDisponible
     *  </pre>
     * Par exemple si stockMax=500, stockCourant=100, prixDeBase=50€, alors: prixFinal = 50 * 500 / 100 = 250€
     */
    @Test
    public void testPrixVariablesSelonStockDisponible()
    {
        String nomProduits = "Playstation";
        int prixDeBase = 350;
        int stockMax = 100;
        Magasin magasin = new Magasin(nomProduits, prixDeBase, stockMax);
        Assertions.assertEquals(prixDeBase, magasin.getPrixReelProduits());
    }

    /**
     * La classe Magasin permet d'acheter des produits: quand un client achète un produit, le stock diminue d'autant de
     * produits achetés. Bien sûr, on ne peut pas acheter plus que la quantité de produits disponibles en stock.
     */
    @Test
    public void testAchatDiminueQuantiteDisponible()
    {
        String nomProduits = "Playstation";
        int prixDeBase = 350;
        int stockMax = 100;
        Magasin magasin = new Magasin(nomProduits, prixDeBase, stockMax);
        magasin.acheter(90);
        Assertions.assertEquals(10, magasin.getStock());

        // Quanité restante = 10, donc prix = 350 * 100 / 10 = 3500€
        Assertions.assertEquals(3500, magasin.getPrixReelProduits());
    }
}
