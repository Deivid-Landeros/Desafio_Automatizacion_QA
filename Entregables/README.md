**Automatización de Pruebas - README**

**Pre-requisitos**

- **Sistema Operativo:** La ejecución funciona tanto en Windows como en Linux.
- **Resolución Configurable:** 1920 x 1080 (Configuración disponible en /Desafio\_Automatizacion\_QA/src/test/java/ips/tests/BaseTest.java [línea 64])
- **Modo Headless (Linux):** Para deshabilitar el modo headless en Linux, comenta la línea 59 en el archivo mencionado anteriormente.
- **Herramientas Utilizadas:**
  - **Java**
  - **Selenium**
  - **TestNG**
  - **Maven**
  - **Eclipse**
    Todas estas herramientas vienen incluidas en el archivo pom.xml. En caso de necesitar ejecutar el código desde la línea de comandos, se recomienda instalarlas previamente.

**Instrucciones para ejecutar**

**Ejecución por IDE**

 **Importar el proyecto:** Importa la carpeta que contiene el código en tu IDE.
 **Ejecutar las pruebas:** Ve a src/test/resources/testng.xml, haz clic derecho y selecciona "Run As" > "TestNG Suite".
 Se cargarán las tres pruebas consecutivamente.
 Las evidencias se generarán automáticamente en las siguientes carpetas:
 **Evidencias:** src/test/resources/evidencias (las evidencias llevan el número del caso de ejecución).
 **Informes:** test-output
 **Informes (Extent Reports):** extent-reports
 **Logs:** log4j2
 *Se recomienda eliminar estas carpetas después de cada ejecución. Se entrega la ejecución realizada en la carpeta "Entregables" segun requermiento*
 **Archivo de entrada con data:** Se utiliza el archivo src/test/resources/utils.json para cargar datos.

**Ejecución por Línea de Comandos**

- Ejecuta el siguiente comando:
mvn -f pom.xml -Xmx3072m
- Aunque este comando es recomendado, cualquier otro comando basado en Maven funcionará adecuadamente. Lo importante es ejecutar el archivo pom.xml, ya que contiene la ruta para el archivo testng.xml.

**Ejecución Individual de Pruebas**

- Las pruebas pueden ejecutarse de manera individual, pero esto sobrescribirá los informes y las evidencias se guardarán en una carpeta llamada "Null". Puedes modificar el archivo testng.xml para conservar las evidencias y los informes de forma separada.

**Detalle del Flujo**

El flujo de la automatización está dividido en tres casos de prueba:

**CP001 - Flujo del Desafío Automatización QA**

Este caso cubre el flujo completo del desafío:

1. Añadir al carro de compras un iPod Classic.
2. Añadir al carro de compras un iMac.
3. Proceder a realizar la compra.
4. Realizar login con credenciales obtenidas de un archivo externo (email válido y contraseña).
5. Crear una cuenta.
6. Continuar con la compra y llegar a la orden completa.
7. Visitar el historial de órdenes y validar el resumen.
8. Cerrar sesión.

*Nota: Para volver a ejecutar este flujo debido a la validación en la página, se debe cambiar la información de usuario.*

**CP002 - Primer Ítem de Puntaje Extra**

Este caso utiliza el usuario y la contraseña creados en el caso anterior:

1. Seleccionar fecha de entrega para mañana.
2. Validar que la memoria del equipo es de 16GB.
3. Escribir un review con texto de menos de 25 caracteres y obtener un mensaje de advertencia.
4. Escribir un review válido con rating neutral y obtener un mensaje de éxito.

**CP003 - Segundo Ítem de Puntaje Extra**

Este caso compara dos productos:

1. Comparar Apple Cinema 30" con Samsung SyncMaster 941BW.
2. Generar evidencia del cuadro comparativo de ambos productos.

**Flujo de Código**

**CP001**

- homePage.goToIndex() - Abre la página Index.
- agregarProducto(busquedaImac) - Busca y agrega el producto iMac; valida si existe.
- agregarProducto(busquedaIpod) - Busca y agrega el producto iPod; valida si existe.
- irCarritoDeCompras() - Muestra el carrito de compras y valida que ambos productos estén agregados.
- homePage.goToCheckOut() - Abre la página CheckOut.
- crearUsuarioCheckoutOptions() - Ingresa a la página de creación de usuario.
- crearUsuarioAcountDetails("CP001") - Rellena el formulario con la información del JSON y valida si el correo ya está registrado.
- crearUsuarioUsaDireccionActualDelivery() - Usa la información actual de delivery.
- validaCostoDespacho() - Valida el costo del despacho según el criterio.
- elegirTipoPago(tipoPago) - Elige el tipo de pago configurado en el JSON.
- confirmarOrden() - Confirma la orden.
- homePage.goToHistorial() - Abre la página Historial.
- ingresoHistorial() - Ingresa al historial de órdenes.
- validarPending() - Valida el estado "Pending".
- ingresarOtraInformacion() - Ingresa al menú de "Otra Información".
- validarDatosPago("CP001") - Valida los datos de la cuenta creados versus los mostrados en el menú.
- logOut() - Realiza el logout.

**CP002**

- homePage.goToIndex() - Abre la página Index.
- loginUsuario(usuario, pass) - Inicia sesión con la cuenta creada en el caso CP001.
- mostrarProducto(productoHP) - Busca y muestra la información del producto HP; valida si existe.
- homePage.goToCompra() - Abre la página Compra.
- completarDatosProductoHP("CP002") - Completa los datos para la compra del producto HP y realiza las validaciones necesarias.
- reviewTresEstrellas("CP002") - Completa el review realizando las verificaciones indicadas y lo envía validando su éxito.
- agregarACarro() - Agrega el producto al carro de compras.

**CP003**

- homePage.goToIndex() - Abre la página Index.
- ComparaProducto(apple) - Busca y agrega el producto Apple Cinema; valida si existe.
- ComparaProducto(samsung) - Busca y agrega el producto Samsung SyncMaster; valida si existe.
- IrComparador() - Se dirige a la comparación de productos seleccionados y muestra su contenido.

**Consideraciones Extra**

Todas las páginas incluyen validaciones mediante asserts, if, try-catch, entre otros. Por ejemplo, si se ingresa un correo antiguo y su clave es correcta, el flujo de CP001 levantará el Assert y no continuará, pero CP002 se ejecutará sin problemas.

