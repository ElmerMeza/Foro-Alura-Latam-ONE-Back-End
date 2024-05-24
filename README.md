# Foro-Alura-Latam-ONE-Back-End
Este proyecto es la resolución del Challenge - Foro Alura, parte del cuarto reto de la formación Oracle Next Education (ONE) de Oracle + Alura LATAM. Se implementó utilizando las siguientes tecnologías y herramientas:

- Spring Boot: Para la creación de la API Rest.
- MySQL: Para la gestión de bases de datos.
- Java 17: Para el desarrollo del backend.
- IntelliJ IDEA: Como entorno de desarrollo integrado (IDE).
- Insomnia: Para pruebas de la API.

Descripción del Reto

El desafío consiste en crear una API Rest siguiendo las mejores prácticas del modelo REST. Incluye la validación de datos y la implementación de una base de datos para asegurar la persistencia de la información.

Tecnologías Utilizadas
- Spring Boot: Framework que facilita la creación de aplicaciones Java empresariales, especialmente para construir APIs Restful.
- MySQL: Sistema de gestión de bases de datos relacional, utilizado para almacenar y gestionar los datos de la aplicación.
- Java 17: Versión de Java utilizada para el desarrollo del proyecto, aprovechando las últimas características del lenguaje.
- IntelliJ IDEA: IDE robusto que ofrece soporte completo para el desarrollo de aplicaciones Java, incluyendo integración con Spring Boot y otras herramientas.
- Insomnia: Herramienta de prueba de APIs que permite enviar solicitudes HTTP y analizar las respuestas para asegurar que la API funcione correctamente.
Objetivos del Proyecto
1. Crear una API Rest: Seguir el modelo de arquitectura REST para desarrollar una API que gestione las operaciones CRUD (Create, Read, Update, Delete) de un foro.
2. Validaciones: Implementar validaciones robustas para los datos recibidos en las solicitudes a la API, asegurando la integridad y validez de la información.
3. Persistencia de Datos: Utilizar MySQL para la persistencia de los datos, garantizando que la información se almacene de manera segura y eficiente.
5. Mejores Prácticas: Seguir las mejores prácticas en el desarrollo de la API, incluyendo el manejo adecuado de errores, el uso de DTOs (Data Transfer Objects) y el diseño modular del código.
Implementación
1. Configuración del Proyecto: Configuración de Spring Boot y MySQL en el archivo application.properties y la creación de las entidades JPA para el manejo de las tablas en la base de datos.
2. Controladores: Creación de controladores REST que manejan las solicitudes HTTP y las operaciones CRUD del foro.
3. Servicios: Implementación de servicios que contienen la lógica de negocio, separando la lógica de la capa de presentación.
4. Repositorios: Uso de Spring Data JPA para la interacción con la base de datos, proporcionando métodos para realizar operaciones CRUD sin necesidad de escribir consultas SQL manuales.
5. Validaciones: Uso de anotaciones de validación en los DTOs para asegurar que los datos recibidos en las solicitudes cumplan con los requisitos esperados.
