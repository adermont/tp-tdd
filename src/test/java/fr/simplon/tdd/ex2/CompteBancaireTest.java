package fr.simplon.tdd.ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class CompteBancaireTest
{
    /**
     * Test 1: On peut créer un nouveau compte en versant une somme (obligatoirement positive) dessus.
     */
    @Test
    public void testCreationCompteBancaire()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CompteBancaire(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CompteBancaire(-1));
        Assertions.assertDoesNotThrow(() -> new CompteBancaire(1));
    }

    /**
     * Test 2: On peut consulter le solde d'un compte. Après la création du compte, le solde est identique à la somme
     * versée lors de l'ouverture.
     */
    @Test
    public void testConsulterSolde()
    {
        for (int i = 0; i < 100; i++)
        {
            int solde = new Random().nextInt(1, Integer.MAX_VALUE);
            Assertions.assertEquals(solde, new CompteBancaire(solde).getSolde());
        }
    }

    /**
     * Test 3: On peut déposer de l'argent liquide sur un compte ; le nouveau solde après dépôt est égal au solde avant
     * dépôt + la somme déposée.
     */
    @Test
    public void testDepot()
    {
        int soldeInitial = 50;
        int depot = 10;
        int soldeFinal = soldeInitial + depot;
        CompteBancaire compte = new CompteBancaire(soldeInitial);
        compte.deposer(depot);
        Assertions.assertEquals(soldeFinal, compte.getSolde());
    }

    /**
     * Test 4: On peut retirer de l'argent d'un compte (cas où le compte est suffisamment approvisionné).
     */
    @Test
    public void testRetraitAutorise()
    {
        int soldeInitial = Integer.MAX_VALUE;
        int retrait = Integer.MAX_VALUE;
        int soldeApresRetraitAutorise = soldeInitial - retrait;
        CompteBancaire compte = new CompteBancaire(soldeInitial);
        compte.retirer(retrait);
        Assertions.assertEquals(soldeApresRetraitAutorise, compte.getSolde());
    }

    /**
     * Test 4: On peut retirer de l'argent d'un compte (cas où les fonds sont insuffisants).
     */
    @Test
    public void testRetraitNonAutorise()
    {
        CompteBancaire compte = new CompteBancaire(new Random().nextInt(1,Integer.MAX_VALUE));
        Assertions.assertThrows(IllegalArgumentException.class, () -> compte.retirer(compte.getSolde() + 1));
    }

}