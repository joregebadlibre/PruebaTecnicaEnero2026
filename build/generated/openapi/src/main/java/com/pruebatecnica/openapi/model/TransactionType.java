package com.pruebatecnica.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TransactionType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-06T15:37:16.862324864-05:00[America/Guayaquil]", comments = "Generator version: 7.6.0")
public enum TransactionType {
  
  CREDITO("CREDITO"),
  
  DEBITO("DEBITO");

  private String value;

  TransactionType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TransactionType fromValue(String value) {
    for (TransactionType b : TransactionType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

