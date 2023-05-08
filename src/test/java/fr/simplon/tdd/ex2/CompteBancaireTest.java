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
}