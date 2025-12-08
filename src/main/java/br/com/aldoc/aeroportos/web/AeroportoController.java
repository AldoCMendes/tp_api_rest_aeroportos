package br.com.aldoc.aeroportos.web;

import br.com.aldoc.aeroportos.domain.Aeroporto;
import br.com.aldoc.aeroportos.service.AeroportoService;
import br.com.aldoc.aeroportos.web.dto.AeroportoRequest;
import br.com.aldoc.aeroportos.web.dto.AeroportoResponse;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aeroportos")
public class AeroportoController {
  private final AeroportoService service;

  public AeroportoController(AeroportoService service) {
    this.service = service;
  }

  @GetMapping
  public List<AeroportoResponse> listar() {
    return service.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
  }

  @GetMapping("/{iata}")
  public AeroportoResponse obter(@PathVariable String iata) {
    return toResponse(service.buscarPorIata(iata));
  }

  @PostMapping
  public ResponseEntity<AeroportoResponse> criar(@Validated @RequestBody AeroportoRequest req) {
    Aeroporto criado = service.criar(req);
    AeroportoResponse res = toResponse(criado);
    return ResponseEntity.created(URI.create("/api/v1/aeroportos/" + res.getCodigoIata())).body(res);
  }

  @PutMapping("/{iata}")
  public AeroportoResponse atualizar(@PathVariable String iata, @Validated @RequestBody AeroportoRequest req) {
    return toResponse(service.atualizar(iata, req));
  }

  @DeleteMapping("/{iata}")
  public ResponseEntity<Void> excluir(@PathVariable String iata) {
    service.excluir(iata);
    return ResponseEntity.noContent().build();
  }

  private AeroportoResponse toResponse(Aeroporto a) {
    AeroportoResponse r = new AeroportoResponse();
    r.setIdAeroporto(a.getIdAeroporto());
    r.setNomeAeroporto(a.getNomeAeroporto());
    r.setCodigoIata(a.getCodigoIata());
    r.setCidade(a.getCidade());
    r.setCodigoPaisIso(a.getCodigoPaisIso());
    r.setLatitude(a.getLatitude());
    r.setLongitude(a.getLongitude());
    r.setAltitude(a.getAltitude());
    return r;
  }
}

