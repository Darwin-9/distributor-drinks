document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita que el formulario se envíe directamente

    // Datos de usuario válidos (simulados)
    const validUsername = "admin";
    const validPassword = "1234";

    // Obtener valores del formulario
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Validar credenciales
    if (username === validUsername && password === validPassword) {
        alert("Inicio de sesión exitoso.");
        window.location.href = "index.html"; // Redirige a la página principal
    } else {
        document.getElementById("error-message").classList.remove("hidden");
    }
});
