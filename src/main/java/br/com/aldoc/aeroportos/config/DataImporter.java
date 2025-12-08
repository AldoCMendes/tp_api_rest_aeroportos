package br.com.aldoc.aeroportos.config;

import br.com.aldoc.aeroportos.service.AeroportoService;
import br.com.aldoc.aeroportos.web.dto.AeroportoRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataImporter implements CommandLineRunner {
  private final AeroportoService service;

  @Value("${app.import.csv.enabled:true}")
  private boolean enabled;

  @Value("${app.import.csv.url:https://raw.githubusercontent.com/profdiegoaugusto/banco-dados/master/mysql/linguagem-consulta-dados/data/airports.csv}")
  private String url;

  public DataImporter(AeroportoService service) {
    this.service = service;
  }

  @Override
  public void run(String... args) throws Exception {
    if (!enabled) return;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = splitCsv(line);
        if (parts.length < 9) continue;
        String iata = trimQuotes(parts[4]);
        if (iata == null || iata.length() != 3) continue;
        AeroportoRequest req = new AeroportoRequest();
        req.setNomeAeroporto(trimQuotes(parts[1]));
        req.setCidade(trimQuotes(parts[2]));
        req.setNomePais(trimQuotes(parts[3]));
        req.setCodigoIata(iata);
        req.setLatitude(parseDouble(parts[6]));
        req.setLongitude(parseDouble(parts[7]));
        req.setAltitude(parseDouble(parts[8]));
        req.setAltitudeEmPes(true);
        try {
          service.criar(req);
        } catch (RuntimeException ignore) {}
      }
    }
  }

  private String[] splitCsv(String line) {
    return line.split(",(?=(?:[^"]*"[^"]*")*[^"]*$)");
  }

  private String trimQuotes(String s) {
    if (s == null) return null;
    String t = s.trim();
    if (t.startsWith("\"") && t.endsWith("\"")) return t.substring(1, t.length() - 1);
    return t;
  }

  private Double parseDouble(String s) {
    try {
      return Double.parseDouble(trimQuotes(s));
    } catch (Exception e) {
      return null;
    }
  }
}

