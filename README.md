# Proyecto-Final-Programacion-avanzada


Taller pruebas avanzadas 


Parte 0: En la parte cero estamos probando cómo nuestros repositorios consultan los datos y verificando que lo hagan bien, aplicando las tres AAA (Arrange, Act, Assert) en el orden visto en clase, para determinar que nuestros repositorios cumplan con la funcionalidad requerida. También comprendimos que, al usar @SpringBootTest, se carga nuestro archivo data.sql, y así evitamos crear todo desde casi cero


Parte 1: En la parte uno estamos probando la implementación de JWT en un endpoint determinado. Con esto verificamos que determinadas funciones del backend estén restringidas para los diferentes tipos de usuarios, logrando así las respuestas 401, 403 y 200. Lo que más me costó entender fue que el código 401 significa que no saben quién eres, el 403 significa que saben quién eres, pero no tienes permiso, y el 200 indica que la petición fue exitosa y que el endpoint realizó la función correctamente.


Parte 2: En esta parte ya estamos usando @DataJpaTest en lugar de @SpringBootTest, y su principal diferencia es que @DataJpaTest solo carga una parte de la persistencia, mientras que @SpringBootTest levanta los controladores, repositorios, servicios, etc. Lo que más me costó entender fue que, como @DataJpaTest solo carga una parte de la persistencia, me tocó importar a mano un mapper que me faltaba para que funcionara.

Parte 3: En esta parte vamos a realizar una prueba de integración, la cual, por lo que entiendo, recorre una gran parte del proyecto en una sola prueba. Es decir, una prueba unitaria sí prueba una parte del código, pero de forma aislada, mientras que una prueba de integración prueba desde el controller, el caso de uso, el repositorio y la base de datos, lo que hace que podamos estar seguros de que nuestros endpoints funcionen bien.
