package com.pruebatecnica.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pruebatecnica.openapi.model.AccountReport;
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
 * AccountStatementReport
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-05T23:38:06.552523547-05:00[America/Guayaquil]", comments = "Generator version: 7.6.0")
public class AccountStatementReport {

  private Long customerId;

  private String customerName;

  private String from;

  private String to;

  @Valid
  private List<@Valid AccountReport> accounts = new ArrayList<>();

  private Double totalCredits;

  private Double totalDebits;

  private String pdfBase64;

  public AccountStatementReport customerId(Long customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  */
  
  @Schema(name = "customerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customerId")
  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public AccountStatementReport customerName(String customerName) {
    this.customerName = customerName;
    return this;
  }

  /**
   * Get customerName
   * @return customerName
  */
  
  @Schema(name = "customerName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customerName")
  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public AccountStatementReport from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
  */
  
  @Schema(name = "from", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("from")
  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public AccountStatementReport to(String to) {
    this.to = to;
    return this;
  }

  /**
   * Get to
   * @return to
  */
  
  @Schema(name = "to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("to")
  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public AccountStatementReport accounts(List<@Valid AccountReport> accounts) {
    this.accounts = accounts;
    return this;
  }

  public AccountStatementReport addAccountsItem(AccountReport accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

  /**
   * Get accounts
   * @return accounts
  */
  @Valid 
  @Schema(name = "accounts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accounts")
  public List<@Valid AccountReport> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<@Valid AccountReport> accounts) {
    this.accounts = accounts;
  }

  public AccountStatementReport totalCredits(Double totalCredits) {
    this.totalCredits = totalCredits;
    return this;
  }

  /**
   * Get totalCredits
   * @return totalCredits
  */
  
  @Schema(name = "totalCredits", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalCredits")
  public Double getTotalCredits() {
    return totalCredits;
  }

  public void setTotalCredits(Double totalCredits) {
    this.totalCredits = totalCredits;
  }

  public AccountStatementReport totalDebits(Double totalDebits) {
    this.totalDebits = totalDebits;
    return this;
  }

  /**
   * Get totalDebits
   * @return totalDebits
  */
  
  @Schema(name = "totalDebits", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalDebits")
  public Double getTotalDebits() {
    return totalDebits;
  }

  public void setTotalDebits(Double totalDebits) {
    this.totalDebits = totalDebits;
  }

  public AccountStatementReport pdfBase64(String pdfBase64) {
    this.pdfBase64 = pdfBase64;
    return this;
  }

  /**
   * Get pdfBase64
   * @return pdfBase64
  */
  
  @Schema(name = "pdfBase64", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pdfBase64")
  public String getPdfBase64() {
    return pdfBase64;
  }

  public void setPdfBase64(String pdfBase64) {
    this.pdfBase64 = pdfBase64;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountStatementReport accountStatementReport = (AccountStatementReport) o;
    return Objects.equals(this.customerId, accountStatementReport.customerId) &&
        Objects.equals(this.customerName, accountStatementReport.customerName) &&
        Objects.equals(this.from, accountStatementReport.from) &&
        Objects.equals(this.to, accountStatementReport.to) &&
        Objects.equals(this.accounts, accountStatementReport.accounts) &&
        Objects.equals(this.totalCredits, accountStatementReport.totalCredits) &&
        Objects.equals(this.totalDebits, accountStatementReport.totalDebits) &&
        Objects.equals(this.pdfBase64, accountStatementReport.pdfBase64);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, customerName, from, to, accounts, totalCredits, totalDebits, pdfBase64);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountStatementReport {\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    customerName: ").append(toIndentedString(customerName)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    totalCredits: ").append(toIndentedString(totalCredits)).append("\n");
    sb.append("    totalDebits: ").append(toIndentedString(totalDebits)).append("\n");
    sb.append("    pdfBase64: ").append(toIndentedString(pdfBase64)).append("\n");
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

