package br.com.aldoc.aeroportos.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AltitudeConverterTest {
  @Test
  void converte1000PesPara304_8Metros() {
    assertEquals(304.8d, AltitudeConverter.converterPesParaMetros(1000d), 1e-9);
  }
}

