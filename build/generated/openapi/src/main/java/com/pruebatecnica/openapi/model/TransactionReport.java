package com.pruebatecnica.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TransactionReport
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-05T23:38:06.552523547-05:00[America/Guayaquil]", comments = "Generator version: 7.6.0")
public class TransactionReport {

  private String date;

  private String transactionType;

  private Double amount;

  private Double balance;

  public TransactionReport date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public TransactionReport transactionType(String transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Get transactionType
   * @return transactionType
  */
  
  @Schema(name = "transactionType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transactionType")
  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public TransactionReport amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public TransactionReport balance(Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  
  @Schema(name = "balance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("balance")
  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionReport transactionReport = (TransactionReport) o;
    return Objects.equals(this.date, transactionReport.date) &&
        Objects.equals(this.transactionType, transactionReport.transactionType) &&
        Objects.equals(this.amount, transactionReport.amount) &&
        Objects.equals(this.balance, transactionReport.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, transactionType, amount, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionReport {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

