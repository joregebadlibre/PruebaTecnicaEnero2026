#!/usr/bin/env bash
set -euo pipefail

BASE_URL="http://localhost:8080"

# ---------- Customers ----------

echo "\n== Customers: POST /customers (create) =="
curl -i -X POST "${BASE_URL}/customers" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Juan",
    "gender": "M",
    "age": 30,
    "identification": "1234567890",
    "address": "Street 1",
    "phone": "0999999999",
    "password": "pass",
    "active": true
  }'

echo "\n== Customers: GET /customers (list) =="
curl -i -X GET "${BASE_URL}/customers"

echo "\n== Customers: GET /customers/{id} (get by id) =="
curl -i -X GET "${BASE_URL}/customers/1"

echo "\n== Customers: PUT /customers/{id} (update) =="
curl -i -X PUT "${BASE_URL}/customers/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Juan Updated",
    "gender": "M",
    "age": 31,
    "identification": "1234567890",
    "address": "Street 99",
    "phone": "0999999999",
    "password": "pass",
    "active": true
  }'

echo "\n== Customers: PATCH /customers/{id}/status?active=true (update status) =="
curl -i -X PATCH "${BASE_URL}/customers/1/status?active=true"

echo "\n== Customers: DELETE /customers/{id} (delete) =="
curl -i -X DELETE "${BASE_URL}/customers/1"

# ---------- Accounts ----------

echo "\n== Accounts: POST /accounts (create) =="
curl -i -X POST "${BASE_URL}/accounts" \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": 1002003004,
    "accountType": "AHORRO",
    "initialBalance": 1000.0,
    "active": true,
    "customerId": 1
  }'

echo "\n== Accounts: GET /accounts (list all) =="
curl -i -X GET "${BASE_URL}/accounts"

echo "\n== Accounts: GET /accounts?customerId=1 (list by customerId) =="
curl -i -X GET "${BASE_URL}/accounts?customerId=1"

echo "\n== Accounts: GET /accounts/{id} (get by id) =="
curl -i -X GET "${BASE_URL}/accounts/1"

echo "\n== Accounts: PUT /accounts/{id} (update) =="
curl -i -X PUT "${BASE_URL}/accounts/1" \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": 1002003004,
    "accountType": "AHORRO",
    "initialBalance": 2000.0,
    "active": true,
    "customerId": 1
  }'

echo "\n== Accounts: DELETE /accounts/{id} (delete) =="
curl -i -X DELETE "${BASE_URL}/accounts/1"

# ---------- Transactions ----------

echo "\n== Transactions: POST /transactions (credit) =="
curl -i -X POST "${BASE_URL}/transactions" \
  -H "Content-Type: application/json" \
  -d '{
    "accountId": 1,
    "transactionType": "CREDITO",
    "amount": 200.0
  }'

echo "\n== Transactions: POST /transactions (debit) =="
curl -i -X POST "${BASE_URL}/transactions" \
  -H "Content-Type: application/json" \
  -d '{
    "accountId": 1,
    "transactionType": "DEBITO",
    "amount": 50.0
  }'

echo "\n== Transactions: GET /transactions (list) =="
curl -i -X GET "${BASE_URL}/transactions"

echo "\n== Transactions: GET /transactions/{id} (get by id) =="
curl -i -X GET "${BASE_URL}/transactions/1"

echo "\n== Transactions: PUT /transactions/{id} (update - only last transaction per account) =="
curl -i -X PUT "${BASE_URL}/transactions/1" \
  -H "Content-Type: application/json" \
  -d '{
    "accountId": 1,
    "transactionType": "DEBITO",
    "amount": 10.0
  }'

echo "\n== Transactions: DELETE /transactions/{id} (delete) =="
curl -i -X DELETE "${BASE_URL}/transactions/1"

# ---------- Reports ----------

echo "\n== Reports: GET /reports?customerId=1&from=YYYY-MM-DD&to=YYYY-MM-DD =="
curl -i -X GET "${BASE_URL}/reports?customerId=1&from=2026-01-01&to=2026-01-31"
