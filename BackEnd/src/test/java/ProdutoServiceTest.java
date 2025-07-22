import org.example.entities.Produto;
import org.example.repositories.ProdutoRepository;
import org.example.services.ProdutoService;
import org.example.services.exeptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = new Produto();
        produto.setProId(1L);
        produto.setProNome("Bateria 60Ah");
        produto.setProDescricao("Bateria automotiva 60 amperes");
        produto.setProPrecoCusto(new BigDecimal("150.00"));
        produto.setProPrecoVenda(new BigDecimal("220.00"));
        produto.setProQuantidadeEstoque(10);
        produto.setProCategoria("Elétrica");
        produto.setProCodigoBarras("7891234567890");
        produto.setProMarca("Moura");
        produto.setProUnidadeMedida("Unidade");
        produto.setProAtivo("Ativo"); // String, não boolean
        produto.setProDataCadastro(LocalDateTime.now());
        produto.setProDataAtualizacao(LocalDateTime.now());
    }

    @Test
    void testFindByIdComSucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Bateria 60Ah", resultado.getProNome());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdComFalha() {
        when(produtoRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> produtoService.findById(2L));
        verify(produtoRepository, times(1)).findById(2L);
    }

    @Test
    void testInsertComSucesso() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = produtoService.insert(produto);

        assertNotNull(resultado);
        assertEquals("Bateria 60Ah", resultado.getProNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testUpdateComSucesso() {
        Produto novoProduto = new Produto();
        novoProduto.setProId(1L);
        novoProduto.setProNome("Amortecedor Dianteiro");
        novoProduto.setProDescricao("Amortecedor dianteiro esportivo");
        novoProduto.setProPrecoCusto(new BigDecimal("100.00"));
        novoProduto.setProPrecoVenda(new BigDecimal("180.00"));
        novoProduto.setProQuantidadeEstoque(20);
        novoProduto.setProCategoria("Suspensão");
        novoProduto.setProCodigoBarras("7890000000001");
        novoProduto.setProMarca("Cofap");
        novoProduto.setProUnidadeMedida("Unidade");
        novoProduto.setProAtivo("Ativo");
        novoProduto.setProDataCadastro(LocalDateTime.now());
        novoProduto.setProDataAtualizacao(LocalDateTime.now());

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(novoProduto);

        boolean atualizado = produtoService.update(1L, novoProduto);

        assertTrue(atualizado);
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void testUpdateComFalha() {
        Produto inexistente = new Produto();
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        boolean atualizado = produtoService.update(99L, inexistente);

        assertFalse(atualizado);
        verify(produtoRepository, never()).save(any(Produto.class));
    }

    @Test
    void testDeleteComSucesso() {
        doNothing().when(produtoRepository).deleteById(1L);

        assertDoesNotThrow(() -> produtoService.delete(1L));
        verify(produtoRepository, times(1)).deleteById(1L);
    }
}