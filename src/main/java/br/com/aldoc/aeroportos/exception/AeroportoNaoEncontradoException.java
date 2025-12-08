package br.com.aldoc.aeroportos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AeroportoNaoEncontradoException extends RuntimeException {
  public AeroportoNaoEncontradoException(String iata) {
    super("Aeroporto não encontrado: " + iata);
  }
}

