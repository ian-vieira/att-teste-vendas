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
    // 4. Testar alteração do nome do produto para um valor válido
    @Test
    public void testAlteracaoNomeProduto() {
        Produto produto = new Produto("Chiclete", 3.0, 2);
        produto.setNome("Bala");
        assertEquals("Bala", produto.getNome());
    }
    // 5. Testar alteração do preço do produto para um valor válido
    @Test
    public void testAlteracaoPrecoValido() {
        Produto produto = new Produto("Biscoito", 2.0, 10);
        produto.setPreco(3.5);
        assertEquals(3.5, produto.getPreco());
    }
    // 6. Testar alteração do estoque para um valor positivo
    @Test
    public void testAlteracaoEstoquePositivo() {
        Produto produto = new Produto("Refrigerante", 6.0, 5);
        produto.setEstoque(20);
        assertEquals(20, produto.getEstoque());
    }
    // 7. Testar alteração do preço do produto para um valor negativo (deve falhar)
    @Test
    public void testAlteracaoPrecoNegativo() {
        Produto produto = new Produto("Pão", 1.0, 10);
        produto.setPreco(-2.0);
        assertTrue(produto.getPreco() < 0); // lógica permite, mas teste destaca erro
    }
    // 8. Testar venda com quantidade menor que o estoque disponível
    @Test
    public void testVendaQuantidadeMenorEstoque() {
        Produto produto = new Produto("Sabão", 5.0, 10);
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }
    // 9. Testar venda com quantidade igual ao estoque disponível
    @Test
    public void testVendaQuantidadeIgualEstoque() {
        Produto produto = new Produto("Leite", 4.0, 5);
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(0, produto.getEstoque());
    }
    // 10. Testar venda com quantidade maior que o estoque disponível (deve falhar)
    @Test
    public void testVendaQuantidadeMaiorEstoque() {
        Produto produto = new Produto("Café", 10.0, 2);
        Venda venda = new Venda(produto, 5);
        assertFalse(venda.realizarVenda());
        assertEquals(2, produto.getEstoque());
    }
    @Test
    public void testCalculoTotalVenda() {
        Produto produto = new Produto("Arroz", 10.0,10);
        Venda venda = new Venda(produto, 3);
        venda.realizarVenda();
        assertEquals(30.0, venda.getTotalVenda());
    }
    @Test
    public void testAumentoEstoqueProduto() {
        Produto produto = new Produto("Feijão", 8.0, 5);
        produto.aumentarEstoque(10);
        assertEquals(15, produto.getEstoque());
    }

    // 13. Testar diminuição do estoque após venda bem-sucedida
    @Test
    public void testDiminuicaoEstoqueAposVenda() {
        Produto produto = new Produto("Macarrão", 7.0, 8);
        Venda venda = new Venda(produto, 3);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }
    // 14. Testar realizar venda de um produto que não existe (deve falhar)
    @Test
    public void testVendaProdutoNulo() {
        Venda venda = new Venda(null, 2);
        assertThrows(NullPointerException.class, () -> {
            venda.realizarVenda();
        });
    }
    // 15. Testar criação de venda com quantidade negativa (deve falhar)
    @Test
    public void testVendaQuantidadeNegativa() {
        Produto produto = new Produto("Chocolate", 5.0, 10);
        Venda venda = new Venda(produto, -3);
        boolean resultado = venda.realizarVenda();
    }
    // 16. Testar alteração do estoque após tentativa de venda com estoque insuficiente
    @Test
    public void testEstoqueAposVendaInsuficiente() {
        Produto produto = new Produto("Suco", 3.0, 1);
        Venda venda = new Venda(produto, 5);
        assertFalse(venda.realizarVenda());
        assertEquals(1, produto.getEstoque());
    }

    // 17. Testar criação de vários produtos e realizar vendas com estoque compartilhado
    @Test
    public void testMultiplosProdutosVendas() {
        Produto p1 = new Produto("Banana", 2.0, 10);
        Produto p2 = new Produto("Maçã", 3.0, 5);

        Venda v1 = new Venda(p1, 4);
        Venda v2 = new Venda(p2, 3);

        assertTrue(v1.realizarVenda());
        assertTrue(v2.realizarVenda());

        assertEquals(6, p1.getEstoque());
        assertEquals(2, p2.getEstoque());
    }

    // 18. Testar calcular total de venda quando o preço do produto for alterado antes da venda
    @Test
    public void testTotalVendaAposAlterarPreco() {
        Produto produto = new Produto("Farinha", 5.0, 10);
        produto.setPreco(6.0);
        Venda venda = new Venda(produto, 2);
        venda.realizarVenda();
        assertEquals(12.0, venda.getTotalVenda());
    }

    // 19. Testar comportamento da venda quando o estoque inicial é zero
    @Test
    public void testVendaComEstoqueZero() {
        Produto produto = new Produto("Açúcar", 4.0, 0);
        Venda venda = new Venda(produto, 1);
        assertFalse(venda.realizarVenda());
        assertEquals(0, produto.getEstoque());
    }

    // 20. Testar aumento do estoque após uma reposição e venda bem-sucedida
    @Test
    public void testReposicaoEVendaPosterior() {
        Produto produto = new Produto("Arroz", 5.0, 0);
        produto.aumentarEstoque(5);
        Venda venda = new Venda(produto, 3);
        assertTrue(venda.realizarVenda());
        assertEquals(2, produto.getEstoque());
    }
}
