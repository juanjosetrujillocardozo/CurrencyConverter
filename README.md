# Currency Converter App

Este es un conversor de monedas que permite a los usuarios convertir entre diferentes divisas utilizando una API de tasas de cambio actualizadas. El programa es interactivo y se ejecuta en consola, además de proporcionar un historial de conversiones.

## Características

- Conversión entre las siguientes monedas:
    - Dólar estadounidense (USD)
    - Peso argentino (ARS)
    - Real brasileño (BRL)
    - Peso colombiano (COP)
- Obtención de tasas de cambio en tiempo real desde la API [ExchangeRate-API](https://www.exchangerate-api.com).
- Manejo de errores de conexión y validación de entradas del usuario.
- Historial de conversiones con marcas de tiempo.

## Requisitos

Para ejecutar este proyecto, necesitas tener lo siguiente:

- **Java 17** o superior
- **Gson** (incluido en el archivo `pom.xml` si estás usando Maven)
- Clave API de [ExchangeRate-API](https://www.exchangerate-api.com) para obtener tasas de cambio.

## Instalación

1. Clona este repositorio en tu máquina local:
    ```bash
    git clone https://github.com/tu_usuario/CurrencyConverter.git
    ```

2. Abre el proyecto en **IntelliJ IDEA** o tu IDE favorito.

3. Asegúrate de que tienes configurado **Java 17** como versión del SDK.

4. Añade tu clave API en el archivo `CurrencyConverterApp.java`:
    ```java
    String apiKey = "TU_API_KEY";  // Reemplaza con tu clave de la API
    ```

5. Ejecuta el proyecto.

## Ejecución

Al ejecutar el programa, se te presentará un menú con las opciones de conversión entre diferentes monedas. Sigue las instrucciones para ingresar la cantidad y ver el resultado de la conversión. También puedes ver el historial de conversiones realizadas durante la sesión.

### Menú de opciones:

1. Dólar (USD) => Peso argentino (ARS)
2. Peso argentino (ARS) => Dólar (USD)
3. Dólar (USD) => Real brasileño (BRL)
4. Real brasileño (BRL) => Dólar (USD)
5. Dólar (USD) => Peso colombiano (COP)
6. Peso colombiano (COP) => Dólar (USD)
7. Ver historial de conversiones
8. Salir

### Ejemplo de uso:

1. Elige una opción del menú (ejemplo: 1 para convertir de USD a ARS).
2. Ingresa la cantidad a convertir.
3. Recibe el resultado de la conversión en tiempo real.

## Capturas de Pantalla

### Menú de Conversión

![Menú de Conversión](ruta_a_la_imagen.png)

### Resultado de Conversión

![Resultado de Conversión](ruta_a_la_imagen.png)

### Historial de Conversiones

![Historial de Conversiones](ruta_a_la_imagen.png)

## Errores comunes

1. **Error de conexión**: Si el programa no puede conectarse a la API, se mostrará el siguiente mensaje:
    ```
    Error de conexión: No se pudo establecer la conexión con la API. Verifique su conexión a internet.
    ```

2. **Entrada no válida**: Si el usuario ingresa un valor que no es un número, se pedirá que ingrese un valor válido.

## Futuras mejoras

- Soporte para más monedas internacionales.
- Interfaz gráfica (GUI) en lugar de consola.
- Guardar el historial de conversiones entre sesiones.

## Licencia

Este proyecto está bajo la [Licencia MIT](https://opensource.org/licenses/MIT).
