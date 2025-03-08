let products = [];
let editIndex = null;

// Validación del formulario de inicio de sesión
document.getElementById("login-form").addEventListener("submit", (e) => {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const errorMessage = document.getElementById("error-message");

    // Credenciales válidas
    const validUsername = "admin";
    const validPassword = "1234";

    if (username === validUsername && password === validPassword) {
        // Redirigir al CRUD
        window.location.href = "crud.html";
    } else {
        // Mostrar mensaje de error
        errorMessage.textContent = "Usuario o contraseña incorrectos.";
    }
});


let nextId = 1; // Contador para generar IDs únicos

// Función para abrir el formulario modal
function openForm() {
    document.getElementById("form-modal").style.display = "block";
}

// Función para cerrar el formulario modal
function closeForm() {
    document.getElementById("form-modal").style.display = "none";
    document.getElementById("product-form").reset();
    editIndex = null;
    document.getElementById("form-title").textContent = "Agregar Producto";
    document.getElementById("submit-btn").textContent = "Guardar";
}

// Función para renderizar la tabla de productos
function renderProducts() {
    const tbody = document.querySelector("#product-table tbody");
    tbody.innerHTML = "";

    products.forEach((product, index) => {
        const row = `
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>$${product.price.toFixed(2)}</td>
                <td>
                    <button onclick="editProduct(${index})" class="btn-edit"><i class="fas fa-edit"></i> Editar</button>
                    <button onclick="deleteProduct(${index})" class="btn-delete"><i class="fas fa-trash"></i> Eliminar</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Función para agregar o actualizar un producto
document.getElementById("product-form").addEventListener("submit", (e) => {
    e.preventDefault();

    const id = document.getElementById("product-id").value;
    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;
    const price = parseFloat(document.getElementById("price").value);

    if (editIndex !== null) {
        // Actualizar producto
        products[editIndex] = { id: products[editIndex].id, name, description, price };
    } else {
        // Agregar nuevo producto
        products.push({ id: nextId++, name, description, price });
    }

    renderProducts();
    closeForm();
});

// Función para editar un producto
function editProduct(index) {
    const product = products[index];
    document.getElementById("product-id").value = index;
    document.getElementById("name").value = product.name;
    document.getElementById("description").value = product.description;
    document.getElementById("price").value = product.price;
    document.getElementById("form-title").textContent = "Editar Producto";
    document.getElementById("submit-btn").textContent = "Actualizar";
    openForm();
}

// Función para eliminar un producto
function deleteProduct(index) {
    if (confirm("¿Estás seguro de eliminar este producto?")) {
        products.splice(index, 1);
        renderProducts();
    }
}

// Renderizar productos al cargar la página
renderProducts();