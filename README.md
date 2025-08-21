# 🚀 Spring Boot Graphql

<div style="text-align: center">

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-brightgreen)
![H2 Database](https://img.shields.io/badge/H2-Database-f4f0e0)

</div>

Implementación de Graphql en Spring Boot y H2 Database

## 📋 Tabla de Contenidos

1. [Estructura del Proyecto](#-estructura-del-proyecto)
2. [Cómo Probar la Aplicación](#-cómo-probar-la-aplicación)
3. [Acceso a la Consola H2](#-acceso-a-la-consola-h2)
5. [Tecnologías utilizadas](#-tecnologías-utilizadas)
6. [Consideraciones](#-consideraciones)

## 🏗 Estructura del Proyecto

```bash

src/
├── main/
│ ├── java/
│ │ └── com/example/movie
│ │   ├── controller/ # Controladores REST
│ │   ├── dto/ # Objetos de Transferencia
│ │   ├── exception/ # Excepciones
│ │   ├── model/ # Entidades
│ │   ├── repository/ # Repositorios
│ │   ├── service/ # Lógica de negocio
│ │   └── MovieApplication.java
│ └── resources/
│   ├─ db/migrations # SQL
│   ├─ graphql/  # Contrato querys
│   ├─ application.yml # Seleccionador perfil
│   ├─ application-example.yml
│   └─ static/ # Recursos estáticos
└─ test/ # Pruebas

```

## 🧪 Cómo Probar la Aplicación

### Requisitos

- JDK 17+
- Maven 3.8+
- Aplicación autenticadora (Google/Microsoft Authenticator)

### Pasos:

Copiar el archivo denominado `application-example.yml` que se encuentra en el directorio `src/main/resource`s y renombrarlo como el environment seleccionado a levantar, ejemplo `application-local.yml`. Modificar los valores de las propiedades por los del ambiente local.

1. **Iniciar la aplicación**:

```bash
   mvn spring-boot:run -P local
```

2. **Configuración en Postman**:

```http
    POST /graphql
    HEADERS Content-Type: application/json
    BODY (raw, JSON):
```

2.1. Obtener todas las películas con id y título

```json

{ "query": "query { allMovies { id title } }" }

```
2.2. Obtener todas las películas con título y géneros

```json

{ "query": "query { allMovies {id title genres { name } } }" }

```

2.3. Obtener una película por ID con todos sus detalles

```json

{ "query": "query { movieById(id: 1) { title directors actors genres { name description } } }" }

```

2.4. Obtener películas por género

```json

{ "query" : "query { moviesByGenre(genre: \"Sci-Fi\") { title directors } }" }

```

2.5. Crear nueva película

```json

{
  "query": "mutation CreateMovie($input: MovieInput!) { createMovie(movieInput: $input) { id title directors actors genres { name } } }",
  "variables": {
    "input": {
      "title": "Interstellar",
      "directors": [
        "Christopher Nolan"
      ],
      "actors": [
        "Matthew McConaughey",
        "Anne Hathaway",
        "Jessica Chastain"
      ],
      "genreIds": [
        4,
        3
      ]
    }
  }
}

```

3. **Configuración en GraphiQL**:

Accede a http://localhost:8080/graphiql para una interfaz interactiva

3.1. Obtener todas las películas con id y título

```graphql

query MyQuery {
  allMovies {
    id
    title
  }
}

```

3.2. Obtener todas las películas con título y géneros

```graphql

query MyQuery {
  allMovies {
    id
    title
    genres {
      name
    }
  }
}

```

3.3. Obtener una película por ID con todos sus detalles

```graphql

query MyQuery {
  movieById(id: "1") {
    title
    directors
    actors
    genres {
      name
      description
    }
  }
}

```

3.4. Obtener películas por género

```graphql

query MyQuery {
  moviesByGenre(genre: "Sci-Fi") {
    title
    directors
  }
}

```

3.5. Crear nueva película

```graphql
mutation {
  createMovie(
    movieInput: {
      title: "Interstellar", 
      directors: ["Christopher Nolan"], 
      actors: ["Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"], 
      genreIds: [4, 3]}
  ) {
    id
    title
    directors
    actors
    genres {
      name
    }
  }
}

```


## 🖥 Acceso a la Consola H2

Accede a la consola H2:

* URL: http://localhost:8080/h2-console
* Credenciales:
    - JDBC URL: jdbc:h2:mem:movieapp
    - User: sa
    - Password: (dejar vacío)

## 🛠 Tecnologías Utilizadas

* Spring Boot 3.1+
* H2 Database (en memoria)
* Lombok (reducción de código boilerplate)
* Flyway (gestión y control de versiones de base de datos)

## ⚡️ Consideraciones

Esta implementación proporciona una base sólida para trabajar con GraphQL en Spring Boot:

* **Controller/Endpoint**: En GraphQL se usa `@Controller` con métodos anotados con `@QueryMapping` y `@MutationMapping` en lugar de los típicos `@RestController`. 
* **DTOs**: Necesarios para definir las estructuras de entrada y salida. 
* **Flexibilidad**: GraphQL permite que el cliente solicite exactamente los campos que necesita
* **GraphiQL**: Accede a http://localhost:8080/graphiql para una interfaz interactiva.
