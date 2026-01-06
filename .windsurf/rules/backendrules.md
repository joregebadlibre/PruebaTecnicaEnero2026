---
trigger: always_on
---

---
trigger: manual
---
Back End
Aplique todas las buenas prácticas, patrones Repository, etc que considere
necesario (se tomará en cuenta este punto para la calificación).
El manejo de entidades se debe manejar con: JPA / Entity Framework Core
Se debe manejar mensajes de excepciones.
Se debe realizar como mínimo dos pruebas unitarias de los endpoints.
La solución se debe desplegar en Docker.

Herramientas y tecnologías utilizadas
Java spring boot
Gradle
IDE de su preferencia
Base de Datos Relacional
Postman v9.13.2 (validador de API)

Back End
Creación de Api Rest “Aplication Programming Interface”
Manejar los verbos: Get, Post, Put, Push, Delete

#Persona
*Implementar la clase persona con los siguientes datos: nombre, genero, edad,
identificación, dirección, teléfono
*Debe manera su clave primaria (PK)

#Cliente
*Cliente debe manejar una entidad, que herede de la clase persona.
*Un cliente tiene: clienteid, contraseña, estado.
*El cliente debe tener una clave única. (PK)

#Cuenta
*Cuenta debe manejar una entidad
*Una cuenta tiene: número cuenta, tipo cuenta, saldo Inicial, estado.
*Debe manejar su Clave única

#Movimientos
*Movimientos debe manejar una entidad
*Un movimiento tiene: Fecha, tipo movimiento, valor, saldo
*Debe manejar su Clave única



Los API’s debe tener las siguientes operaciones:
Podrá Crear, editar, actualizar y eliminar registros (Entidades: Cliente, Cuenta y Movimiento).
Los endpoints a crear son:
• /cuentas
• /clientes
• /movimientos

Los valores cuando son crédito son positivos, y los débitos son negativos. Debe
almacenarse el saldo disponible en cada transacción dependiendo del tipo de
movimiento. (suma o resta).
Si el saldo es cero, y va a realizar una transacción débito, debe desplegar mensaje

“Saldo no disponible”.
Generar reporte (Estado de cuenta) especificando un rango de fechas y un cliente,
visualice las cuentas asociadas con sus respectivos saldos y el total de débitos y
créditos realizados durante las fechas de ese cliente. Tomar en consideración que
también se debe obtener los resultados del reporte en formato base64 (PDF) y Json.

Por ejemplo:
(/reportes?fecha=rango fechas)