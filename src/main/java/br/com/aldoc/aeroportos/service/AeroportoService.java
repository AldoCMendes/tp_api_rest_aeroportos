package br.com.aldoc.aeroportos.service;

import br.com.aldoc.aeroportos.domain.Aeroporto;
import br.com.aldoc.aeroportos.domain.AltitudeConverter;
import br.com.aldoc.aeroportos.domain.CountryIsoMapper;
import br.com.aldoc.aeroportos.exception.AeroportoNaoEncontradoException;
import br.com.aldoc.aeroportos.repository.AeroportoRepository;
import br.com.aldoc.aeroportos.web.dto.AeroportoRequest;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AeroportoService {
  private final AeroportoRepository repository;

  public AeroportoService(AeroportoRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public List<Aeroporto> listarTodos() {
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  public Aeroporto buscarPorIata(String iata) {
    return repository
        .findByCodigoIata(normalizarIata(iata))
        .orElseThrow(() -> new AeroportoNaoEncontradoException(iata));
  }

  @Transactional
  public Aeroporto criar(AeroportoRequest req) {
    validar(req);
    Aeroporto a = new Aeroporto();
    a.setNomeAeroporto(req.getNomeAeroporto());
    a.setCodigoIata(normalizarIata(req.getCodigoIata()));
    a.setCidade(req.getCidade());
    String iso = CountryIsoMapper.obterIsoPais(req.getNomePais());
    if (iso == null) throw new IllegalArgumentException("País inválido");
    a.setCodigoPaisIso(iso);
    a.setLatitude(req.getLatitude());
    a.setLongitude(req.getLongitude());
    double alt = req.isAltitudeEmPes() ? AltitudeConverter.converterPesParaMetros(req.getAltitude()) : req.getAltitude();
    a.setAltitude(validarAltitude(alt));
    return repository.save(a);
  }

  @Transactional
  public Aeroporto atualizar(String iata, AeroportoRequest req) {
    validar(req);
    Aeroporto existente = buscarPorIata(iata);
    existente.setNomeAeroporto(req.getNomeAeroporto());
    existente.setCidade(req.getCidade());
    String iso = CountryIsoMapper.obterIsoPais(req.getNomePais());
    if (iso == null) throw new IllegalArgumentException("País inválido");
    existente.setCodigoPaisIso(iso);
    existente.setLatitude(req.getLatitude());
    existente.setLongitude(req.getLongitude());
    double alt = req.isAltitudeEmPes() ? AltitudeConverter.converterPesParaMetros(req.getAltitude()) : req.getAltitude();
    existente.setAltitude(validarAltitude(alt));
    return repository.save(existente);
  }

  @Transactional
  public void excluir(String iata) {
    Aeroporto existente = buscarPorIata(iata);
    repository.delete(existente);
  }

  private String normalizarIata(String iata) {
    if (iata == null) throw new IllegalArgumentException("IATA inválido");
    String v = iata.trim().toUpperCase(Locale.ROOT);
    if (v.length() != 3 || !v.chars().allMatch(Character::isLetter)) throw new IllegalArgumentException("IATA inválido");
    return v;
  }

  private void validar(AeroportoRequest req) {
    if (req == null) throw new IllegalArgumentException("Dados inválidos");
    if (req.getNomeAeroporto() == null || req.getNomeAeroporto().trim().isEmpty()) throw new IllegalArgumentException("Nome inválido");
    normalizarIata(req.getCodigoIata());
    if (req.getCidade() == null || req.getCidade().trim().isEmpty()) throw new IllegalArgumentException("Cidade inválida");
    if (req.getLatitude() == null || req.getLongitude() == null) throw new IllegalArgumentException("Coordenadas inválidas");
    if (req.getLatitude() < -90 || req.getLatitude() > 90) throw new IllegalArgumentException("Latitude inválida");
    if (req.getLongitude() < -180 || req.getLongitude() > 180) throw new IllegalArgumentException("Longitude inválida");
    if (req.getAltitude() == null) throw new IllegalArgumentException("Altitude inválida");
  }

  private double validarAltitude(double m) {
    if (m < 0) throw new IllegalArgumentException("Altitude inválida");
    return m;
  }
}

