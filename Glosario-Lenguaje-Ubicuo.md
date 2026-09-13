# Glosario del Lenguaje Ubicuo - [DICEVA / Juegos de Mesa]

## Conceptos Centrales

### [Mecanica de juego]
**Definición:** [Es la forma en que funciona y se desarrolla una partida, incluyendo las acciones, reglas y dinámicas que determinan cómo se juega.]
**Precondiciones:** [a mecánica debe corresponder a las reglas reales del juego y estar definida antes de publicarlo en el catálogo.]
**Sinónimos aceptados:** [mecánica, mecánica de juego]
**No usar:** [tipo, característica]

**Ejemplo de uso en código:**
\`\`\`java
private Set<MecanicaDeJuego> mecanicasDeJuego;
\`\`\`

---

### [Rango de jugadores]
**Definición:** [Es la cantidad mínima y máxima de jugadores que pueden participar en una partida del juego de mesa.]
**Precondiciones:** [La cantidad mínima de jugadores debe ser mayor o igual a 1 y la cantidad máxima debe ser mayor o igual a la cantidad mínima.]
**Sinónimos aceptados:** [cantidad de jugadores, número de jugadores]
**No usar:** [jugadores, cantidad, número]

**Ejemplo de uso en código:**
\`\`\`java
private RangoDeJugadores rangoDeJugadores;
\`\`\`

---


### [Tematica de juego]
**Definición:** [Es el tema, ambientación o contexto que representa el juego y que permite identificar de qué trata su experiencia de juego.]
**Precondiciones:** [La temática debe corresponder al contenido y ambientación real del juego.]
**Sinónimos aceptados:** [temática, tema del juego]
**No usar:** [tema, tipo, categoria]

**Ejemplo de uso en código:**
\`\`\`java
public String getTematicaDeJuego() {
    return tematicaDeJuego;
}
\`\`\`

---

### [Edad recomdadada]
**Definición:** [Es la edad mínima recomendada para que una persona pueda participar adecuadamente en el juego, de acuerdo con sus características y reglas.]
**Precondiciones:** [Debe corresponder a la edad mínima recomendada indicada para el juego y representarse como un valor numérico de edad.]
**Sinónimos aceptados:** [Edad minima,Edad sugerida]
**No usar:** [Edad, Años, Rango, Rando edad ]

**Ejemplo de uso en código:**
\`\`\`java
public Integer getEdadRecomendada() 
\`\`\`

---


### [Complejidad de juego]
**Definición:** [Es el nivel de dificultad que presenta un juego de mesa para comprender sus reglas, aprender sus dinámicas y desarrollar una partida.]
**Precondiciones:** [El nivel de complejidad debe corresponder a las características y reglas reales del juego y clasificarse de acuerdo con los niveles definidos por DICEVA.]
**Sinónimos aceptados:** [Nivel de dificultad, Dificultad del juego]
**No usar:** [Nivel, Tipo]

**Ejemplo de uso en código:**
\`\`\`java
public String getComplejidadDeJuego() {
    return complejidadDeJuego;
}
\`\`\`

---
## Reglas de negocion 
## Reglas de Negocio Clave

1. **Un comprador solo puede calificar juegos que haya adquirido.**

2. **Un vendedor solo puede administrar sus propios juegos.**

3. **No se puede comprar una cantidad superior al stock disponible.**

4. **El stock se descuenta cuando el pedido es confirmado.**

5. **Un pedido solo puede ser confirmado cuando el pago haya sido aprobado.**

6. **Un juego debe tener un rango de jugadores válido, donde el mínimo sea mayor o igual a 1**

7. **El precio de un juego debe ser mayor a cero.**

8. **Una calificación debe estar entre 1 y 5 estrellas.**

9. **Un juego solo puede estar disponible para compra cuando se encuentre activo y tenga la información obligatoria completa.**

10. **La edad recomendada de un juego debe ser un valor mayor o igual a cero.**

---



## Anti-patrones (Términos a EVITAR en nuestro proyecto)

| No usar | Usar |
|---|---|
| [Mecanica] | [MecanicaDeJuego] |
| [Jugadores] | [RangoDeJugadores] |
| [Tematica] | [TematicaDeJuego] |
| [Edad] | [EdadRecomendada] |
| [Dificultad] | [ConplejidadDeJuego] |
