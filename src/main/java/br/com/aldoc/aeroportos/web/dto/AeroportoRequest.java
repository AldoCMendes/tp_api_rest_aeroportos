package br.com.aldoc.aeroportos.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AeroportoRequest {
  @NotBlank
  private String nomeAeroporto;

  @NotBlank
  @Size(min = 3, max = 3)
  private String codigoIata;

  @NotBlank
  private String cidade;

  @NotBlank
  private String nomePais;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  @DecimalMin("0.0")
  private Double altitude;

  private boolean altitudeEmPes;

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

  public String getNomePais() {
    return nomePais;
  }

  public void setNomePais(String nomePais) {
    this.nomePais = nomePais;
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

  public boolean isAltitudeEmPes() {
    return altitudeEmPes;
  }

  public void setAltitudeEmPes(boolean altitudeEmPes) {
    this.altitudeEmPes = altitudeEmPes;
  }
}

