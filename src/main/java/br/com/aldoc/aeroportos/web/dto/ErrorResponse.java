package br.com.aldoc.aeroportos.web.dto;

import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
  private OffsetDateTime timestamp;
  private int status;
  private String error;
  private String message;
  private String path;

  public static ErrorResponse of(HttpStatus status, String message, String path) {
    ErrorResponse e = new ErrorResponse();
    e.timestamp = OffsetDateTime.now();
    e.status = status.value();
    e.error = status.getReasonPhrase();
    e.message = message;
    e.path = path;
    return e;
  }

  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }
}

