
<!DOCTYPE html>
<html lang="es">
<body>
    <main>
        <h1>Proyecto: Tienda online de videojuegos con Java y Springboot</h1>
        <p>Este proyecto es una tienda online de videojuegos desarrollada con Java y Springboot. Cuenta con un servidor
            y un cliente, y utiliza una base de datos SQL.</p>
        <div>
            <h2>Administración del servidor</h2>
            <p>La administración del servidor permite la creación, modificación y alta/baja de las entidades
                videojuegos,
                usuarios, plataformas y géneros. También permite cambiar el estado de un pedido. Tiene integrado
                multiidioma
                en casi todos los textos de la administración.</p>
            <p>La parte visual de la administración está hecha con HTML, SCSS (traducido a CSS), un poco de JavaScript y
                utiliza el motor de plantillas Thymeleaf para recoger y enviar los datos del servidor.</p>
        </div>
        <div>
            <h2>Cliente de la tienda</h2>
            <p>Desde la parte cliente de la tienda se puede:</p>
            <ul>
                <li>Registrar usuarios</li>
                <li>Iniciar sesión</li>
                <li>Listar los videojuegos de la tienda que están de alta</li>
                <li>Ver los detalles de un videojuego</li>
                <li>Añadir al carrito videojuegos</li>
                <li>Borrar productos del carrito</li>
                <li>Cambiar la cantidad de un producto del carrito</li>
                <li>Realizar un pedido con los productos del carrito</li>
                <li>Ver los pedidos realizados</li>
                <li>Cambiar el idioma (solo la barra de navegación)</li>
                <li>Cerrar sesión</li>
            </ul>
        </div>
        <p>La parte visual de la tienda (como cliente) está hecha con HTML, SCSS (traducido a CSS), JavaScript y el
            motor de plantillas Mustache. Está construida para ser una página web dinámica.</p>
        <p class="enlace">Puedes descargar el proyecto aquí: <a href="http://cabodash.byethost22.com/tienda_springboot/springboot_vj.zip">Descargar proyecto</a></p>
        <p>Después de descargar el proyecto, sigue esta guía para importarlo a Eclipse:</p>
        <ol>
            <li>Se necesita Java 8 para el proyecto, si no lo tuvieses y el Eclipse no tuviese un Java 8 instalado lo puedes encontrar en:  <a rel="noopener" href="https://adoptium.net/es/temurin/releases/?package=jdk&version=8" target="_blank">Descargar Java 8</a></li>
            <li>Descomprime el proyecto donde prefieras</li>
            <li>Abre Eclipse, si no lo tienes instalado puedes descargarlo en: <a rel="noopener" href="https://www.eclipse.org/downloads/packages/release/2023-06/r/eclipse-ide-enterprise-java-and-web-developers" target="_blank">Descargar Eclipse EE</a></li>
            <li>Ve a File > Import</li>
            <li>Selecciona Maven > Existing Maven Projects</li>
            <li>Selecciona el directorio donde descargaste el proyecto y haz clic en Finish</li>
            <li>Abre el programa XAMPP ,si no lo tienes instalado puedes descargarlo en: <a rel="noopener" href="https://www.apachefriends.org/download.html" target="_blank">Descargar XAMPP</a></li>
            <li>Enciende el proceso de SQL</li>
            <li>Ve al archivo de Application.java del proyecto (src > main > java > springboot > tienda > Application.java)</li>
            <li>Ejecútalo, la ruta de la web será: http://localhost:8080/</li> 
        </ol>
    </main>
</body>

</html>
