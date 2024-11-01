# **Examen Parcial - API Mutante Mercadolibre**

---
## Indice 📖
- [Introducción del reto](#-introducción-del-reto)
    - [Alcance](#alcance)

- [Tecnologías utilizadas](#️-tecnologías-utilizadas)

- [Funcionamiento API](#-funcionamiento-api)

- [Instalación en local](#️-instalación-en-local)

- [Diagrama de Arquitectura](#️-diagrama-de-arquitectura)

- [Diagrama de secuencia](#️-diagrama-de-secuencia)

- [Cobertura de Código (>80%)](#-cobertura-de-código-80)

- [Ejemplos de funcionamiento (Postman)](#-ejemplos-de-funcionamiento-postman)
    - [ADN Humano](#-adn-humano)
    - [ADN Mutante](#-adn-mutante)
    - [Estadísticas](#-estadísticas)

- [Base de datos H2 para guardar los ADN´s verificados por la API](#️-base-de-datos-h2-para-guardar-los-adns-verificados-por-la-api)

- [Prueba de documentación con Swagger](#️-prueba-de-documentación-con-swagger)

- [Ejemplos de funcionamiento (Render + Postman)](#-ejemplos-de-funcionamiento-render--postman)
    - [Funcionamiento POST en Render](#funcionamiento-post-en-render)
    - [Funcionamiento GET en Render](#funcionamiento-get-en-render)
- [Pruebas de rendimiento JMeter](#-pruebas-de-rendimiento-jmeter)
## Introducción del reto

###   Alcance:
Un mutante es un ser humano con un ADN especial que contiene secuencias de cuatro letras iguales en forma horizontal, vertical o diagonal. 
Este sistema brinda un servicio que detecte si un humano es en verdad un mutante basado en su secuencia de ADN.



## Tecnologías utilizadas

- **Java 17** *(Desarrollo con IntelliJ IDEA)*
- **Gradle** *(Gestor de dependencias)*
- **Spring Boot** *(Framework backend)*
- **H2** *(Base de datos embebida)*
- **Postman** *(Cliente para pruebas de API)*
- **JUnit** *(Pruebas unitarias)*
- **JMeter** *(Pruebas de estrés y performance)*
- **Render** *(Despliegue en la nube de la API)*
- **Docker Desktop** *(Deploy contenedor)*
- **Swagger** *(Documentación interactiva de APIs)*


## **FUNCIONAMIENTO API**
El cliente envía una petición tipo POST http */mutant/* al servidor donde se encuentra alojada la api.
Esta petición se encuentra en formato JSON y tiene la siguiente forma.

{
"dna": "",
"nombre": "",
"apellido": "",
"poder": ""
}

Si la API detecta que es efectivamente un mutante, devuelve un código 200ok.
Si la API detecta que es humano, devuelve un código 403 FORBIDDEN

En ambos casos la información será almacenada en la base de datos. 

El diagrama de secuencia simplificado es el siguiente.

![SecuenciaMutant.jpg](images%2FSecuenciaMutant.jpg)


El otro servicio que la API nos brinda es el de calcular y mostrar estadísticas de cuantos humanos y cuantos mutantes hay almacenados en la base de datos, asi como el ratio de mutantes.

El diagrama de secuencia simplificado es el siguiente.

![SecuenciaStats.jpg](images%2FSecuenciaStats.jpg)

### HTTP POST (mediante Postman)


<pre><code>403 Forbidden</code></pre>

### HTTP GET (mediante Postman)

Devuelve un JSON con las estadisticas de las veridficaciones de ADN

Path: http://localhost:8080/stats

Response:

<pre><code>{
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
}</code></pre>

## 🖥️ Instalación en local


1. Clonar o Descargar Proyecto desde la rama main:

[Descargar aquí](https://github.com/TheBestDeveloper95/Examen-Parcial-Mercadolibre/archive/refs/heads/main.zip)

2. Abrir el proyecto en intelliJ IDEA:

<pre><code>Ejecutar MutantApplication</code></pre>

3. Descargar e instalar H2:

<pre><code>LEVANTAR H2 con este comando: http://localhost:8080/h2-console/
Controlador: org.h2.Driver
URL JDBC: jdbc:h2:mem:testdb
</code></pre>

4. Enviar secuencia de ADN en formato JSON mediante:
<pre><code>a)Postman: (Instalar postman)
b)Swagger: llamandolo mediante http://localhost:8080/swagger-ui/index.html
</code></pre>
Si solo quieren testear el deploy de la API de render con Postman a continuación dejo el servicio en vivo:
<pre><code>https://examen-parcial-mercadolibre.onrender.com

La lógica es la misma:
POST https://examen-parcial-mercadolibre.onrender.com/mutant
GET  https://examen-parcial-mercadolibre.onrender.com/stats
</code></pre>

## 🖥️ Diagrama de Arquitectura
![Arquitectura.png](imagenes%2FArquitectura.png)

## 🖥️ Diagrama de secuencia
![Secuencia1.png](imagenes%2FSecuencia1.png)

## 📊 Cobertura de Código (>80%)

La aplicación cuenta con test automatizados con cobertura por encima del 80%.

![img.png](imagenes%2Fimg.png)

## 🧪 Ejemplos de funcionamiento (Postman)

* ### 🔬 ADN Humano

#### POST: http://localhost:8080/mutant
<pre><code>{
    "dna": [
        "TTGCGCAGCT",
        "CAGTAAACCT",
        "TTAGAGAGGT",
        "ATTCGGGAAA",
        "CCCAAACTAG",
        "GGGTACTGAA",
        "TTAGAGAGGT",
        "ATTCGGGAAA",
        "TTGCGCAGCT",
        "CAGTAAACCT"
    ]
}
</code></pre>

![403 fORBIDEN.png](imagenes%2F403%20fORBIDEN.png)

* ### 🧬 ADN Mutante
#### POST: http://localhost:8080/mutant
<pre><code>{
    "dna": [
        "ATGCGA", 
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA", 
        "TCACTG"
    ]
}
</code></pre>

![200OK.png](imagenes%2F200OK.png)

* ### 📈 Estadísticas
#### GET: http://localhost:8080/stats

![STATS.png](imagenes%2FSTATS.png)

## 🗄️ Base de datos H2 para guardar los ADN´s verificados por la API.
Se utilizó H2 como base de datos para almacenar todas las secuencias de adn, sin que se repitan.

![H2.png](imagenes%2FH2.png)

## 🗄️ Prueba de documentación con Swagger

![Swagger.png](imagenes%2FSwagger.png)

## 🧪 Ejemplos de funcionamiento (Render + Postman)

#### Funcionamiento POST en Render
<pre><code>POST: https://examen-parcial-mercadolibre.onrender.com/mutant</code></pre>
![renderPost.png](imagenes%2FrenderPost.png)

#### Funcionamiento GET en Render
<pre><code>GET:  https://examen-parcial-mercadolibre.onrender.com/stats</code></pre>
![renderGet.png](imagenes%2FrenderGet.png)



## 🔨 Pruebas de rendimiento JMeter
Se realizaron pruebas de rendimiento de la aplicación, recibiendo tráfico de peticiones por segundo, se probó desde 100
usuarios por segundo hasta 3000, respondiendo correctamente la aplicación 2100 sin presentar error en la petición.

![jmeterGetT.png](imagenes%2FjmeterGetT.png)

![jmeterPostT.png](imagenes%2FjmeterPostT.png)

Para más información sobre el reto, la implementación del resto de tecnologias y temas como la eficiencia
y la complejidad cuadrática del algoritmo visitar mi pagina:

<a href="https://thebestdeveloper95.github.io/Documentacion-HTML-Examen-Mercadolibre/" target="_blank">Como afronté el Examen de Mercadolibre</a>

## 🏆 Desafíos cumplidos:
### Nivel 1: ✓
### Nivel 2: ✓
### Nivel 3: ✓

## 🧬 Posibles Pruebas unitarias
- **Mutante 1**

<pre><code>{
"dna": ["AAAA", "CCCC", "TCAG", "GGTC"]
}</code></pre>
- **Humano 1**

<pre><code>{
    "dna": ["AAAT", "AACC", "AAAC", "CGGG"]
}</code></pre>


- **Mutante 2**
<pre><code>{
    "dna": ["TGAC", "AGCC", "TGAC", "GGTC"]
}</code></pre>

- **Humano 2**
<pre><code>{
    "dna": ["TGAC", "ATCC", "TAAG", "GGTC"]
}</code></pre>

- **Mutante 3**
<pre><code>{
    "dna": ["TCGGGTGAT", "TGATCCTTT", "TACGAGTGA", "AAATGTACG", "ACGAGTGCT", "AGACACATG", "GAATTCCAA", "ACTACGACC", "TGAGTATCC"]
}</code></pre>

- **Mutante 4**
<pre><code>{
    "dna": ["TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "CCGACCAGT", "GGCACCTCA", "AGGACACTA", "CAAAGGCAT", "GCAGTCCCC"]
}</code></pre>