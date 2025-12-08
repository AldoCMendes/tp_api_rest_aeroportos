package br.com.aldoc.aeroportos.domain;

public final class AltitudeConverter {
  private AltitudeConverter() {}

  public static double converterPesParaMetros(double pes) {
    return pes * 0.3048d;
  }
}

