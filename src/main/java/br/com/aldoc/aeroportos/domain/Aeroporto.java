package br.com.aldoc.aeroportos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aeroporto")
public class Aeroporto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_aeroporto")
  private Long idAeroporto;

  @Column(name = "nome_aeroporto", nullable = false)
  private String nomeAeroporto;

  @Column(name = "codigo_iata", nullable = false, length = 3, unique = true)
  private String codigoIata;

  @Column(name = "cidade", nullable = false)
  private String cidade;

  @Column(name = "codigo_pais_iso", nullable = false, length = 2)
  private String codigoPaisIso;

  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @Column(name = "longitude", nullable = false)
  private Double longitude;

  @Column(name = "altitude", nullable = false)
  private Double altitude;

  public Long getIdAeroporto() {
    return idAeroporto;
  }

  public void setIdAeroporto(Long idAeroporto) {
    this.idAeroporto = idAeroporto;
  }

  public String getNomeAeroporto() {
    return nomeAeroporto;
  }

  public void setNomeAeroporto(String nomeAeroporto) {
    this.nomeAeroporto = nomeAeroporto;
  }

  public String getCodigoIata() {
    return codigoIata;
  }

  public void setCodigoIata(String codigoIata) {
    this.codigoIata = codigoIata;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getCodigoPaisIso() {
    return codigoPaisIso;
  }

  public void setCodigoPaisIso(String codigoPaisIso) {
    this.codigoPaisIso = codigoPaisIso;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getAltitude() {
    return altitude;
  }

  public void setAltitude(Double altitude) {
    this.altitude = altitude;
  }
}

