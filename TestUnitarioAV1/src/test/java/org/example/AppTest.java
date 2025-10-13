package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    // 1. Testar criação de produto com valores válidos
    @Test
    public void testCriacaoProdutoValido() {
        Produto produto = new Produto("Chiclete", 3.00, 2);
        assertEquals("Chiclete", produto.getNome());
        assertEquals(3.00, produto.getPreco());
        assertEquals(2, produto.getEstoque());
    }
    @Test
    public void testCriacaoProdutoPrecoNegativo() {
        Produto produto = new Produto("Bala", -5.0, 10);
        assertTrue(produto.getPreco() < 0); // A classe permite, mas é inválido logicamente
    }
    // 3. Testar criação de produto com estoque negativo (deve ser inválido)
    @Test
    public void testCriacaoProdutoEstoqueNegativo() {
        Produto produto = new Produto("Refri", 5.0, -3);
        assertTrue(produto.getEstoque() < 0); // Também permitido, mas deve ser tratado
    }

}
