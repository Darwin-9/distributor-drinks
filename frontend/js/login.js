// login.js - Manejo de autenticación

// Verificar si el usuario está autenticado
function checkAuthentication() {
    // Si el usuario intenta acceder a admin.html y no está autenticado
    if (window.location.pathname.includes('admin.html')) {
        if (localStorage.getItem('isLoggedIn') !== 'true') {
            window.location.href = 'login.html';
        }
    }

    // Si el usuario intenta acceder al enlace de Admin desde cualquier otra página
    document.querySelectorAll('a[href="admin.html"]').forEach(link => {
        link.addEventListener('click', function (e) {
            if (localStorage.getItem('isLoggedIn') !== 'true') {
                e.preventDefault();
                window.location.href = 'login.html';
            }
        });
    });
}

// Manejar el inicio de sesión en login.html
function handleLogin() {
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        const validUsername = "admin";
        const validPassword = "1234";

        loginForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            if (username === validUsername && password === validPassword) {
                // Guardar sesión en localStorage
                localStorage.setItem('isLoggedIn', 'true');
                // Redirigir al panel de administración
                window.location.href = "admin.html";
            } else {
                document.getElementById('errorMessage').style.display = 'block';
            }
        });
    }
}

// Ejecutar las funciones al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    checkAuthentication();
    handleLogin();
});