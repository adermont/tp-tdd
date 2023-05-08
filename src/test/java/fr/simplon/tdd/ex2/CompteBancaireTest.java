package fr.simplon.tdd.ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}