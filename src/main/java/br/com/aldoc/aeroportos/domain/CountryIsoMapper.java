package br.com.aldoc.aeroportos.domain;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class CountryIsoMapper {
  private static final Map<String, String> cache = new HashMap<>();
  private static final Map<String, String> synonyms = new HashMap<>();

  static {
    for (String code : Locale.getISOCountries()) {
      String name = new Locale("", code).getDisplayCountry(Locale.ENGLISH).toLowerCase(Locale.ENGLISH);
      cache.put(name, code);
    }
    synonyms.put("uk", "GB");
    synonyms.put("russia", "RU");
    synonyms.put("south korea", "KR");
    synonyms.put("north korea", "KP");
    synonyms.put("united states", "US");
    synonyms.put("ivory coast", "CI");
  }

  private CountryIsoMapper() {}

  public static String obterIsoPais(String nome) {
    if (nome == null) return null;
    String key = nome.trim().toLowerCase(Locale.ENGLISH);
    String syn = synonyms.get(key);
    if (syn != null) return syn;
    String code = cache.get(key);
    return code;
  }
}

