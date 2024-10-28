
### Instrucciones para Ejecutar el Sistema Distribuido

#### Paso 1: Compilar el Proyecto
Abre una terminal en la raíz del proyecto y ejecuta el siguiente comando para compilar el proyecto:

```bash
./gradlew build
```

Esto generará los archivos `.jar` necesarios en las carpetas `server/build/libs` y `helperServer/build/libs`.

#### Paso 2: Iniciar el Servidor Maestro
Navega a la carpeta del servidor y ejecuta el servidor maestro:

```bash
cd server/build/libs
java -jar server.jar
```

#### Paso 3: Iniciar los Trabajadores
Abre nuevas terminales para cada trabajador y ejecuta el comando correspondiente. Asegúrate de cambiar el puerto para cada trabajador:

**Para el primer trabajador:**

```bash
cd helperServer/build/libs
java -jar helperServer.jar 10001 Worker1
```

**Para el segundo trabajador:**

```bash
cd helperServer/build/libs
java -jar helperServer.jar 10002 Worker2
```

**Para el tercer trabajador:**

```bash
cd helperServer/build/libs
java -jar helperServer.jar 10003 Worker3
```

*Repite el proceso para iniciar más trabajadores con puertos diferentes.*

#### Paso 4: Iniciar el Cliente
Finalmente, en otra terminal, navega a la carpeta del cliente y ejecuta el cliente:

```bash
cd client/build/libs
java -jar client.jar
```

Cuando se te pida, ingresa el número total de puntos para la estimación de π.

### Ejemplo de Salida

```plaintext
Ingrese el número total de puntos para la estimación de π: 1000000
Estimación de π: 3.141592653589793
Tiempo de espera: 150 milisegundos
```
