---

# LITER ALURA

## Introducción

El proyecto **LITER ALURA** combina diversas tecnologías y prácticas clave en el desarrollo de aplicaciones Java, incluyendo el uso de lambda, streams, el framework Spring, persistencia de datos con Spring Data JPA y consultas avanzadas en una base de datos PostgreSQL. Este README detalla cómo se aplican estos conceptos en el proyecto y proporciona una visión general de sus características y funcionalidades.

## Características principales del proyecto

### Consumo de API Externa

El proyecto inicia consumiendo datos de la API pública de Gutendex, la cual proporciona información sobre libros. Se utiliza la clase `ConsumoAPI` para realizar solicitudes HTTP y obtener datos en formato JSON desde la URL base `https://gutendex.com/books/?search=`.

### Deserialización de Datos

Los datos JSON obtenidos se deserializan utilizando la clase `ConvierteDatos`, la cual utiliza la biblioteca Jackson ObjectMapper para convertir el JSON en objetos Java correspondientes, tales como `Datos`, `DatosLibro` y `DatosAutor`.

### Persistencia en Base de Datos

Los objetos deserializados (`DatosLibro` y `DatosAutor`) se persisten en una base de datos PostgreSQL utilizando Spring Data JPA. Se definen las entidades `Libro` y `Autor`, junto con sus respectivos repositorios (`LibroRepository` y `AutorRepository`), para facilitar el almacenamiento y recuperación de datos.

### Consultas Avanzadas con Spring Data JPA

Se implementan consultas personalizadas en los repositorios (`AutorRepository` y `LibroRepository`) utilizando anotaciones `@Query` para realizar operaciones como búsqueda por título de libro, listado de autores vivos en un año específico, listado de libros por idioma, entre otras.

### Interfaz de Usuario Mediante Línea de Comandos

Se ha desarrollado una interfaz de usuario básica utilizando la clase `Principal`, la cual ofrece un menú interactivo al usuario a través de la consola. Las opciones incluyen búsqueda de libros por título, listado de libros y autores registrados, búsqueda de autores vivos en una fecha específica y búsqueda de libros por idioma.

### Spring Boot Application

La clase `LiteraluraApplication` actúa como punto de entrada principal de la aplicación, inicializando Spring Boot y configurando los repositorios y servicios necesarios para la ejecución del programa. Implementa `CommandLineRunner` para iniciar la clase `Principal` y ejecutar el menú de interacción con el usuario.

## Tecnologías Utilizadas

- **Java 8+:** Utilizado para aprovechar lambdas y streams para el procesamiento eficiente de datos.
- **Spring Framework:** Utilizado para la configuración de la aplicación, gestión de dependencias y creación de servicios.
- **Spring Data JPA:** Implementado para la simplificación de operaciones de persistencia de datos y consultas utilizando JPQL.
- **PostgreSQL:** Base de datos relacional utilizada para almacenar los datos de libros y autores.

## Estructura del Proyecto

El proyecto está organizado en varias partes principales:

- **Modelo de Datos:**
  - **Libro:** Representa la entidad de un libro, incluyendo su título, idioma, número de descargas y su relación con un autor.
  - **Autor:** Representa la entidad de un autor, incluyendo su nombre, fecha de nacimiento, fecha de fallecimiento y la lista de libros que ha escrito.
  
- **Persistencia de Datos:**
  - **LibroRepository:** Interfaz que define métodos para realizar operaciones de base de datos relacionadas con libros.
  - **AutorRepository:** Interfaz que define métodos para realizar operaciones de base de datos relacionadas con autores.

- **Servicios y Controladores:**
  - **Principal:** Clase principal que contiene la lógica de negocio y los controladores para manejar las diferentes funcionalidades de la aplicación.

- **Consumo de API Externa:**
  - **ConsumoAPI:** Clase que se encarga de consumir datos de una API externa utilizando URLs construidas dinámicamente.

## Conclusión

El proyecto **LITER ALURA** no solo busca aplicar conocimientos en desarrollo de software, sino también afianzarlos a través de la práctica. Desde consumir datos de una API hasta persistirlos en una base de datos relacional y consultarlos eficientemente, cada aspecto del proyecto está diseñado para proporcionar una experiencia completa en el uso de tecnologías modernas de desarrollo de aplicaciones Java. Este enfoque práctico no solo fortalece las habilidades técnicas, sino que también prepara para enfrentar desafíos del mundo real en el desarrollo de software.

---
