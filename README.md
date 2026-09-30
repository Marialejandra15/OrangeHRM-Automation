# Automatización de OrangeHRM

Proyecto de pruebas UI para automatizar algunos flujos del sitio de demostración de OrangeHRM. Está escrito en Java y usa Selenium WebDriver para manejar Chrome, TestNG para organizar y ejecutar los casos, y Allure para crear el reporte.

## Requisitos

- JDK 21 (el `pom.xml` compila con Java 21).
- Maven instalado y disponible como `mvn` en la terminal.
- Google Chrome instalado.
- Conexión a Internet para acceder al sitio de demostración y descargar las dependencias Maven la primera vez.

Selenium Manager, incluido con Selenium, se encarga normalmente de obtener el controlador compatible de Chrome al iniciar la prueba.

## Estructura y explicación del código

```text
src/
├── main/
│   ├── java/
│   │   ├── pages/       # Acciones y localizadores de las pantallas
│   │   └── utils/       # Lectura de archivos CSV
│   └── resources/data/  # Datos para las pruebas e imagen de ejemplo
└── test/java/
    ├── base/            # Inicio y cierre de Chrome
    └── tests/           # Casos de prueba de TestNG
```

- `base/BaseTest.java`: inicia Chrome antes de la suite, maximiza la ventana y lo cierra al terminar.
- `pages/Login.java`: localiza los campos de usuario y contraseña y ejecuta el inicio de sesión.
- `pages/Employe.java`: contiene acciones de PIM para crear y buscar empleados, cargar una foto y comprobar que el empleado aparezca en la lista.
- `pages/Employe_admin.java`: contiene acciones para crear un usuario desde el módulo Admin, asignando rol, empleado asociado y estado.
- `utils/csvdatareader.java`: lee un CSV, omite su encabezado y devuelve las filas como datos para TestNG.
- `tests/logintest.java`: prueba el acceso y toma usuario/contraseña de `login.csv`.
- `tests/Employe_test.java`: crea un empleado usando `employe_data.csv`.
- `tests/Employe_validate_test.java`: busca y valida los datos del empleado.
- `tests/employe_admin_test.java`: crea un usuario del sistema con los datos de `employe_admin.csv`.
- `testng.xml`: define la suite y las clases de prueba que Maven ejecuta.
- `pom.xml`: declara las dependencias de Selenium, TestNG y Allure, además de configurar Maven Surefire para usar `testng.xml` y guardar los resultados Allure en `target/allure-results`.

Los archivos CSV tienen una fila de encabezados y luego una fila por conjunto de datos. Si agregas columnas o filas, conserva el orden que espera el método de prueba correspondiente. La ruta de foto de `employe_data.csv` se interpreta desde la raíz del proyecto. El flujo de creación de empleado también actualiza `employe_admin.csv` con el empleado creado y un nuevo nombre de usuario para el caso Admin.

## Ejecutar las pruebas

Abre PowerShell o una terminal en la carpeta raíz del proyecto y ejecuta:

```powershell
mvn clean test
```

Maven compila el proyecto, ejecuta la suite de `testng.xml` y abre Chrome para interactuar con OrangeHRM. Al finalizar, los resultados de Allure quedan en:

```text
target/allure-results/
```

## Generar y abrir el reporte Allure

Primero ejecuta las pruebas para producir resultados. Luego, desde la misma carpeta raíz, ejecuta:

```powershell
mvn allure:serve
```

El plugin genera el reporte y levanta un servidor local para abrirlo en el navegador. Al cerrar ese servidor, los resultados crudos siguen en `target/allure-results`; vuelve a ejecutar `mvn allure:serve` para consultarlos otra vez. Para regenerar resultados desde cero, usa de nuevo `mvn clean test` antes del comando de reporte.

También puedes crear los archivos estáticos del reporte con:

```powershell
mvn allure:report
```

El reporte estático se guarda normalmente en `target/site/allure-maven-plugin/`; abre `index.html` dentro de esa carpeta.

## Archivos generados

- `target/surefire-reports/`: reportes de ejecución de TestNG/Surefire.
- `target/allure-results/`: datos de resultados utilizados por Allure.
- `target/site/allure-maven-plugin/`: reporte HTML estático generado por `mvn allure:report`.
