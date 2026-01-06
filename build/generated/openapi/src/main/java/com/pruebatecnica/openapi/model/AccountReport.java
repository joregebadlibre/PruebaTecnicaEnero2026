package com.pruebatecnica.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pruebatecnica.openapi.model.TransactionReport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AccountReport
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-05T23:38:06.552523547-05:00[America/Guayaquil]", comments = "Generator version: 7.6.0")
public class AccountReport {

  private Long accountId;

  private Long accountNumber;

  private String accountType;

  private Double initialBalance;

  private Double availableBalance;

  @Valid
  private List<@Valid TransactionReport> transactions = new ArrayList<>();

  public AccountReport accountId(Long accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   * @return accountId
  */
  
  @Schema(name = "accountId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountId")
  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public AccountReport accountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
  */
  
  @Schema(name = "accountNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountNumber")
  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public AccountReport accountType(String accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * @return accountType
  */
  
  @Schema(name = "accountType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountType")
  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public AccountReport initialBalance(Double initialBalance) {
    this.initialBalance = initialBalance;
    return this;
  }

  /**
   * Get initialBalance
   * @return initialBalance
  */
  
  @Schema(name = "initialBalance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("initialBalance")
  public Double getInitialBalance() {
    return initialBalance;
  }

  public void setInitialBalance(Double initialBalance) {
    this.initialBalance = initialBalance;
  }

  public AccountReport availableBalance(Double availableBalance) {
    this.availableBalance = availableBalance;
    return this;
  }

  /**
   * Get availableBalance
   * @return availableBalance
  */
  
  @Schema(name = "availableBalance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("availableBalance")
  public Double getAvailableBalance() {
    return availableBalance;
  }

  public void setAvailableBalance(Double availableBalance) {
    this.availableBalance = availableBalance;
  }

  public AccountReport transactions(List<@Valid TransactionReport> transactions) {
    this.transactions = transactions;
    return this;
  }

  public AccountReport addTransactionsItem(TransactionReport transactionsItem) {
    if (this.transactions == null) {
      this.transactions = new ArrayList<>();
    }
    this.transactions.add(transactionsItem);
    return this;
  }

  /**
   * Get transactions
   * @return transactions
  */
  @Valid 
  @Schema(name = "transactions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transactions")
  public List<@Valid TransactionReport> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<@Valid TransactionReport> transactions) {
    this.transactions = transactions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountReport accountReport = (AccountReport) o;
    return Objects.equals(this.accountId, accountReport.accountId) &&
        Objects.equals(this.accountNumber, accountReport.accountNumber) &&
        Objects.equals(this.accountType, accountReport.accountType) &&
        Objects.equals(this.initialBalance, accountReport.initialBalance) &&
        Objects.equals(this.availableBalance, accountReport.availableBalance) &&
        Objects.equals(this.transactions, accountReport.transactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, accountNumber, accountType, initialBalance, availableBalance, transactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountReport {\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    initialBalance: ").append(toIndentedString(initialBalance)).append("\n");
    sb.append("    availableBalance: ").append(toIndentedString(availableBalance)).append("\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
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

