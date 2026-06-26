Markdown
# Food Store - Sistema de Gestión de Pedidos en Consola 

Trabajo Práctico Integrador desarrollado para la materia **Programación II** de la **Tecnicatura Universitaria en Programación ** de la **Universidad Tecnológica Nacional (UTN)**.

---

## Descripción del Proyecto
Este proyecto consiste en un sistema de backend emulado en memoria que permite gestionar el flujo comercial completo de un local gastronómico (*Food Store*). 
Diseñado íntegramente bajo el paradigma de **Programación Orientada a Objetos (POO)** en Java, el software implementa un control estricto de negocio, validaciones transaccionales mediante excepciones personalizadas y consistencia referencial con bajas lógicas.

---

##  Tecnologías Aplicadas
* **Lenguaje:** Java Core (JDK 25)
* **Arquitectura:** Diseño modular en capas 
* **API del Sistema:** Java Time API (`LocalDate` y `LocalDateTime`)
* **Colecciones:** Estructuras estáticas en memoria (`ArrayList`)

---

## Arquitectura de Paquetes
La solución se organiza en seis capas bien definidas para asegurar la separación de responsabilidades:

* `Entities`: Modelado del dominio (`Usuario`, `Producto`, `Pedido`, `DetallePedido`, `Categoria`) heredando de una clase abstracta `Base`.
* `Gestores`: Capa de servicio encargada de la lógica de negocio y colecciones en memoria.
* `Ui`: Interfaz interactiva de consola para el operador.
* `Interfaces`: Contratos de comportamiento (`Calculable`).
* `Enums`: Dominios de datos cerrados (`Rol`, `Estado`, `FormaPago`).
* `Exceptions`: Excepciones personalizadas para el control defensivo de errores de negocio.

---

## Características principales e integridad del negocio
* **Validación de Unicidad:** Control cruzado mediante sobrecarga para edición y alta de datos únicos (como correos electrónicos).
* **Manejo Defensivo y Excepciones:** Los errores técnicos y de negocio (como la falta de stock) lanzan excepciones dedicadas capturadas limpiamente por la UI.
* **Mecanismo de Rollback:** En la creación de pedidos, si un artículo falla sus criterios de aceptación, toda la transacción en memoria se cancela para evitar estados inconsistentes.
* **Persistencia por Baja Lógica:** Los registros eliminados cambian de estado (`eliminado = true`) impidiendo nuevas transacciones pero salvaguardando la integridad histórica de pedidos previos.

---

## 🎮 Instrucciones de Ejecución
1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/TU_USUARIO/TU_REPOSITORIO.git](https://github.com/TU_U