
---

# LITER ALURA

## Descripción del Proyecto
"LITER ALURA" es una aplicación que permite la búsqueda, almacenamiento y consulta de información sobre libros y autores. Utiliza una API externa para obtener datos de libros, los deserializa, y los almacena en una base de datos PostgreSQL. Emplea el framework Spring para la gestión de dependencias, la configuración y la integración de componentes.

## Funcionalidades Principales
- **Búsqueda de Libros por Título:** Permite buscar un libro específico por su título. Si el libro no está en la base de datos, lo guarda junto con su autor.
- **Listado de Libros Registrados:** Muestra todos los libros guardados en la base de datos.
- **Listado de Autores Registrados:** Muestra todos los autores que tienen libros registrados, junto con la lista de sus libros.
- **Búsqueda de Autores Vivos en un Año Específico:** Permite buscar autores que estuvieron vivos en un año determinado.
- **Listado de Libros por Idioma:** Muestra todos los libros disponibles en un idioma específico.

## Tecnologías Utilizadas
- **Java 8+:** Utiliza lambdas y streams para el procesamiento eficiente de datos.
- **Spring Framework:** Gestiona la configuración, inyección de dependencias y el ciclo de vida de los componentes de la aplicación.
- **Spring Data JPA:** Simplifica la implementación de la capa de persistencia, permitiendo realizar operaciones CRUD y consultas utilizando JPQL.
- **PostgreSQL:** Base de datos relacional utilizada para almacenar los datos de libros y autores.

## Estructura del Proyecto
El proyecto está organizado en las siguientes partes principales:
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
---
