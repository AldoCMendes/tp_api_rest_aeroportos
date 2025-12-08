package br.com.aldoc.aeroportos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.aldoc.aeroportos.domain.Aeroporto;
import br.com.aldoc.aeroportos.exception.AeroportoNaoEncontradoException;
import br.com.aldoc.aeroportos.repository.AeroportoRepository;
import br.com.aldoc.aeroportos.web.dto.AeroportoRequest;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AeroportoServiceTest {
  private AeroportoRepository repository;
  private AeroportoService service;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(AeroportoRepository.class);
    service = new AeroportoService(repository);
  }

  @Test
  void buscaPorIataInexistenteDisparaExcecao() {
    when(repository.findByCodigoIata("AAA")).thenReturn(Optional.empty());
    assertThrows(AeroportoNaoEncontradoException.class, () -> service.buscarPorIata("AAA"));
  }

  @Test
  void salvarAeroportoComIataInvalidoDisparaExcecao() {
    AeroportoRequest req = novoReq();
    req.setCodigoIata("AAAA");
    assertThrows(IllegalArgumentException.class, () -> service.criar(req));
  }

  @Test
  void salvarAeroportoComAltitudeNegativaDisparaExcecao() {
    AeroportoRequest req = novoReq();
    req.setAltitude(-1.0);
    assertThrows(IllegalArgumentException.class, () -> service.criar(req));
  }

  @Test
  void criaAeroportoValido() {
    AeroportoRequest req = novoReq();
    Aeroporto salvo = new Aeroporto();
    salvo.setCodigoIata("ABC");
    when(repository.save(any())).thenReturn(salvo);
    Aeroporto res = service.criar(req);
    assertEquals("ABC", res.getCodigoIata());
  }

  private AeroportoRequest novoReq() {
    AeroportoRequest r = new AeroportoRequest();
    r.setNomeAeroporto("Teste");
    r.setCodigoIata("ABC");
    r.setCidade("Cidade");
    r.setNomePais("Brazil");
    r.setLatitude(1.0);
    r.setLongitude(2.0);
    r.setAltitude(10.0);
    r.setAltitudeEmPes(false);
    return r;
  }
}

