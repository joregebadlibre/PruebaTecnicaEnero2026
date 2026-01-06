-- Datos iniciales

INSERT INTO personas (id, nombre, genero, edad, identificacion, direccion, telefono)
VALUES
  (1, 'Juan Perez', 'M', 30, '0102030405', 'Av. Principal 123', '099111222'),
  (2, 'Maria Lopez', 'F', 28, '1112131415', 'Calle Secundaria 456', '098333444')
ON CONFLICT (id) DO NOTHING;

INSERT INTO clientes (cliente_id, contrasena, estado)
VALUES
  (1, 'juan123', true),
  (2, 'maria123', true)
ON CONFLICT (cliente_id) DO NOTHING;

INSERT INTO cuentas (id, numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES
  (1, 47875899, 'AHORRO', 2000.00, true, 1),
  (2, 22548799, 'CORRIENTE', 100.00, true, 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO movimientos (id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES
  (1, '2026-01-01 10:00:00', 'CREDITO', 2000.00, 2000.00, 1),
  (2, '2026-01-02 09:30:00', 'DEBITO', 575.00, 1425.00, 1),
  (3, '2026-01-03 16:45:00', 'CREDITO', 100.00, 100.00, 2)
ON CONFLICT (id) DO NOTHING;

-- Ajustar secuencias para evitar colisiones cuando la app inserte nuevos registros
SELECT setval(pg_get_serial_sequence('personas', 'id'), COALESCE((SELECT MAX(id) FROM personas), 1));
SELECT setval(pg_get_serial_sequence('cuentas', 'id'), COALESCE((SELECT MAX(id) FROM cuentas), 1));
SELECT setval(pg_get_serial_sequence('movimientos', 'id'), COALESCE((SELECT MAX(id) FROM movimientos), 1));
