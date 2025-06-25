import org.example.dto.ClienteDto;
import org.example.entities.Cliente;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.repositories.ClienteRepository;
import org.example.repositories.EnderecoRepository;
import org.example.services.ClienteService;
import org.example.services.exeptions.ResourceNotFoundException;
import org.example.services.exeptions.ValueBigForAtributeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setCliId(1L);
        cliente.setCliNome("João Souza");
        cliente.setCliCpf("00011122233");

        Endereco endereco = new Endereco(1L, cliente, "Rua Central", "101", "Cidade Alfa", "11111-111", "MG");
        Contato contato = new Contato(1L, cliente, "90000-0001", "4002-8922", "joao@exemplo.com");

        cliente.setEnderecos(Arrays.asList(endereco));
        cliente.setContatos(Arrays.asList(contato));
    }

    @Test
    void deveBuscarClientePorIdExistente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.findById(1L);

        assertNotNull(resultado);
        assertEquals("João Souza", resultado.getCliNome());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExiste() {
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clienteService.findById(2L));
    }

    @Test
    void deveConverterDeDtoParaEntidade() {
        ClienteDto dto = new ClienteDto();
        dto.setCliNome("Ana Ferreira");
        dto.setCliCpf("99988877766");
        dto.setEndRua("Rua Beta");
        dto.setEndNumero("456");
        dto.setEndCidade("Cidade Beta");
        dto.setEndCep("22222-222");
        dto.setEndEstado("SP");
        dto.setConCelular("91111-2222");
        dto.setConTelefoneComercial("3131-1313");
        dto.setConEmail("ana@exemplo.com");

        Cliente convertido = clienteService.fromDTO(dto);

        assertEquals("Ana Ferreira", convertido.getCliNome());
        assertEquals("Rua Beta", convertido.getEnderecos().get(0).getEndRua());
        assertEquals("91111-2222", convertido.getContatos().get(0).getConCelular());
    }

    @Test
    void deveConverterDeEntidadeParaDto() {
        ClienteDto dto = clienteService.toNewDto(cliente);

        assertEquals("João Souza", dto.getCliNome());
        assertEquals("Rua Central", dto.getEndRua());
        assertEquals("90000-0001", dto.getConCelular());
    }

    @Test
    void deveInserirClienteComSucesso() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.insert(cliente);

        assertNotNull(resultado);
        assertEquals("João Souza", resultado.getCliNome());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
        verify(enderecoRepository, times(1)).saveAll(cliente.getEnderecos());
    }

    @Test
    void deveLancarExcecaoAoInserirComViolacaoDeIntegridade() {
        when(clienteRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(ValueBigForAtributeException.class, () -> clienteService.insert(cliente));
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        ClienteDto dto = new ClienteDto();
        dto.setCliNome("Lucas Martins");
        dto.setCliCpf("55544433322");
        dto.setEndRua("Rua Nova Esperança");
        dto.setEndNumero("999");
        dto.setEndCidade("Cidade Gama");
        dto.setEndCep("33333-333");
        dto.setEndEstado("RJ");
        dto.setConCelular("92222-3333");
        dto.setConTelefoneComercial("3222-3333");
        dto.setConEmail("lucas@empresa.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente atualizado = clienteService.update(1L, dto);

        assertNotNull(atualizado);
        assertEquals("Lucas Martins", atualizado.getCliNome());
        assertEquals("Rua Nova Esperança", atualizado.getEnderecos().get(0).getEndRua());
        assertEquals("92222-3333", atualizado.getContatos().get(0).getConCelular());
    }

    @Test
    void deveDeletarClienteComSucesso() {
        doNothing().when(clienteRepository).deleteById(1L);

        assertDoesNotThrow(() -> clienteService.deleteCliente(1L));
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarClienteInexistente() {
        doThrow(new EmptyResultDataAccessException(1)).when(clienteRepository).deleteById(99L);

        assertThrows(ResourceNotFoundException.class, () -> clienteService.deleteCliente(99L));
    }
}