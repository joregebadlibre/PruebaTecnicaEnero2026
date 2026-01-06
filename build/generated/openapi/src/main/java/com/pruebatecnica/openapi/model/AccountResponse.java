package com.pruebatecnica.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pruebatecnica.openapi.model.AccountType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AccountResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-05T23:38:06.552523547-05:00[America/Guayaquil]", comments = "Generator version: 7.6.0")
public class AccountResponse {

  private Long accountNumber;

  private AccountType accountType;

  private Double initialBalance;

  private Boolean active;

  private Long customerId;

  private Long id;

  public AccountResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AccountResponse(Long accountNumber, AccountType accountType, Double initialBalance, Boolean active, Long customerId) {
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.initialBalance = initialBalance;
    this.active = active;
    this.customerId = customerId;
  }

  public AccountResponse accountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
  */
  @NotNull 
  @Schema(name = "accountNumber", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountNumber")
  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public AccountResponse accountType(AccountType accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * @return accountType
  */
  @NotNull @Valid 
  @Schema(name = "accountType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountType")
  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public AccountResponse initialBalance(Double initialBalance) {
    this.initialBalance = initialBalance;
    return this;
  }

  /**
   * Get initialBalance
   * @return initialBalance
  */
  @NotNull 
  @Schema(name = "initialBalance", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("initialBalance")
  public Double getInitialBalance() {
    return initialBalance;
  }

  public void setInitialBalance(Double initialBalance) {
    this.initialBalance = initialBalance;
  }

  public AccountResponse active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  */
  @NotNull 
  @Schema(name = "active", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("active")
  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public AccountResponse customerId(Long customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  */
  @NotNull 
  @Schema(name = "customerId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("customerId")
  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public AccountResponse id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountResponse accountResponse = (AccountResponse) o;
    return Objects.equals(this.accountNumber, accountResponse.accountNumber) &&
        Objects.equals(this.accountType, accountResponse.accountType) &&
        Objects.equals(this.initialBalance, accountResponse.initialBalance) &&
        Objects.equals(this.active, accountResponse.active) &&
        Objects.equals(this.customerId, accountResponse.customerId) &&
        Objects.equals(this.id, accountResponse.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNumber, accountType, initialBalance, active, customerId, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountResponse {\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    initialBalance: ").append(toIndentedString(initialBalance)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

