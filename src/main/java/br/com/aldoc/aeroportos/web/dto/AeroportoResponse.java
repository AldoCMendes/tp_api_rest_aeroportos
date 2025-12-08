package br.com.aldoc.aeroportos.web.dto;

public class AeroportoResponse {
  private Long idAeroporto;
  private String nomeAeroporto;
  private String codigoIata;
  private String cidade;
  private String codigoPaisIso;
  private Double latitude;
  private Double longitude;
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

