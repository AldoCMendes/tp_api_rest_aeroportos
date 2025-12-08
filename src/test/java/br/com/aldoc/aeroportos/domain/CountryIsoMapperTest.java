package br.com.aldoc.aeroportos.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountryIsoMapperTest {
  @Test
  void retornaIsoParaBrazil() {
    assertEquals("BR", CountryIsoMapper.obterIsoPais("Brazil"));
  }
}

