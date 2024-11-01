# **PARCIAL-DETECTOR DE MUTANTES**

---
## Indice 📖
- [Introducción del reto](#️-introducción-del-reto)
  - [Descripción](#️-descripción)
- [Tecnologías utilizadas](#️-tecnologías-utilizadas)

- [Funcionamiento API](#️-funcionamiento-api)

- [Instalación en local](#️-instalación-en-local)
- [Ejemplo con el POST /stats](#️-ejemplo-con-el-post-stats)
- [Ejemplos de funcionamiento de /mutant/](#️-ejemplos-de-funcionamiento-de-/mutant/)
    - [ADN Humano](#️-adn-humano)
    - [ADN Mutante](#️-adn-mutante)
- [Pruebas de rendimiento JMeter](#️-pruebas-de-rendimiento-jmeter)
  - [Mutant](#️-mutant)
  - [Stats](#️-stats) 
## Introducción del reto

###   Descripción:
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

<pre><code>
{
  "dna": "",
  "nombre": "",
  "apellido": "",
  "poder": ""
}
</code></pre>

Si la API detecta que es efectivamente un mutante, devuelve un código 200ok.
Si la API detecta que es humano, devuelve un código 403 FORBIDDEN

En ambos casos la información será almacenada en la base de datos. 

El diagrama de secuencia simplificado es el siguiente.

![SecuenciaMutant.jpg](images%2FSecuenciaMutant.jpg)

**Luego la base de datos queda actualizada de la siguiente forma:**

![BaseDeDatos.jpg](images%2FBaseDeDatos.jpg)

El otro servicio que la API nos brinda es el de calcular y mostrar estadísticas de cuantos humanos y cuantos mutantes hay almacenados en la base de datos, asi como el ratio de mutantes.

El diagrama de secuencia simplificado es el siguiente.

![SecuenciaStats.jpg](images%2FSecuenciaStats.jpg)



## Instalación en local


1. Clonar o Descargar Proyecto desde la rama main:

[Descargar aquí](https://github.com/TomyFernandez/Parcial-MUTANT/archive/refs/heads/main.zip)

2. Abrir el proyecto en intelliJ IDEA:


3. Descargar e instalar H2:

<pre><code>LEVANTAR H2 con este comando: http://localhost:8080/h2-console/
Controlador: org.h2.Driver
URL JDBC: jdbc:h2:mem:testdb
</code></pre>

4. Enviar secuencia de ADN en formato JSON mediante postman:

    **Es requisito tener instalado POSTMAN**

### Ejemplo con el POST /stats

![statsPostman.jpg](images%2FstatsPostman.jpg)


## Ejemplos de funcionamiento de /mutant/

* ### ADN Humano

#### POST: http://localhost:8080/mutant/
Los siguientes son ejemplos de request en formato JSON para HUMANOS
<pre><code>
{
  "dna": ["TGAC", "ATCC", "TAAG", "GGTC"],
  "nombre": "Mickey",
  "apellido": "Mouse",
  "poder": "No es un raton"
}

{
  "dna": ["AAAT", "AACC", "AAAC", "CGGG"],
  "nombre": "Carlos",
  "apellido": "Javier",
  "poder": "Empatía"
}

</code></pre>

* ### ADN Mutante
#### POST: http://localhost:8080/mutant/
Los siguientes son ejemplos de request en formato JSON para MUTANTES.

<pre><code>
{
  "dna": ["AAAA", "CCCC", "TCAG", "GGTC"],
  "nombre": "Erik",
  "apellido": "Lehnsherr",
  "poder": "Magnetismo"
}

{
  "dna": ["TCGGGTGAT", "TGATCCTTT", "TACGAGTGA", "AAATGTACG", "ACGAGTGCT", "AGACACATG", "GAATTCCAA", "ACTACGACC", "TGAGTATCC"],
  "nombre": "Rodrigo",
  "apellido": "LeBeau",
  "poder": "Guita"
}

{
  "dna": ["TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "CCGACCAGT", "GGCACCTCA", "AGGACACTA", "CAAAGGCAT", "GCAGTCCCC"],
  "nombre": "Valentin"
  "apellido": "Correa",
  "poder": "A prueba de balas"
}
</code></pre>


## Pruebas de rendimiento JMeter
Se realizaron pruebas de rendimiento ej JMeter. Con hasta 5000 usuarios.
## **Estos fueron los porcentajes de rendimiento**
### Mutant

![mutantReport.jpg](images%2FmutantReport.jpg)

## Stats

![statsRequest.jpg](images%2FstatsRequest.jpg)

## DEPLOY EN RENDER


[lINK DE RENDER](https://parcial-mutant.onrender.com)
