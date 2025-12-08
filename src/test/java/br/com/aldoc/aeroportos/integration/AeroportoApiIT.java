package br.com.aldoc.aeroportos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.aldoc.aeroportos.web.dto.AeroportoRequest;
import br.com.aldoc.aeroportos.web.dto.AeroportoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AeroportoApiIT {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate rest;

  @Test
  void fluxoCompletoCrud() {
    AeroportoRequest req = new AeroportoRequest();
    req.setNomeAeroporto("Aeroporto X");
    req.setCodigoIata("XYZ");
    req.setCidade("Cidade");
    req.setNomePais("Brazil");
    req.setLatitude(-10.0);
    req.setLongitude(-20.0);
    req.setAltitude(100.0);
    req.setAltitudeEmPes(false);

    ResponseEntity<AeroportoResponse> created = rest.postForEntity(url("/api/v1/aeroportos"), req, AeroportoResponse.class);
    assertEquals(HttpStatus.CREATED, created.getStatusCode());
    AeroportoResponse body = created.getBody();
    assertEquals("XYZ", body.getCodigoIata());

    ResponseEntity<AeroportoResponse> got = rest.getForEntity(url("/api/v1/aeroportos/XYZ"), AeroportoResponse.class);
    assertEquals(HttpStatus.OK, got.getStatusCode());
    assertEquals("XYZ", got.getBody().getCodigoIata());

    req.setCidade("Nova Cidade");
    ResponseEntity<AeroportoResponse> updated = rest.exchange(url("/api/v1/aeroportos/XYZ"), HttpMethod.PUT, new HttpEntity<>(req), AeroportoResponse.class);
    assertEquals(HttpStatus.OK, updated.getStatusCode());
    assertEquals("Nova Cidade", updated.getBody().getCidade());

    ResponseEntity<Void> deleted = rest.exchange(url("/api/v1/aeroportos/XYZ"), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
    assertEquals(HttpStatus.NO_CONTENT, deleted.getStatusCode());

    ResponseEntity<AeroportoResponse> getAfterDelete = rest.getForEntity(url("/api/v1/aeroportos/XYZ"), AeroportoResponse.class);
    assertEquals(HttpStatus.NOT_FOUND, getAfterDelete.getStatusCode());
    assertNull(getAfterDelete.getBody());
  }

  private String url(String path) {
    return "http://localhost:" + port + path;
  }
}

