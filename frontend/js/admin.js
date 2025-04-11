// Configuración global
const API_URL = "http://localhost:8080/api/v1/drinks/";
let productoActual = null;

// Elementos del DOM
const elementos = {
    modalProducto: document.getElementById("modalProducto"),
    modalEliminar: document.getElementById("modalEliminar"),
    tablaProductos: document.getElementById("tablaProductos"),
    formProducto: document.getElementById("formProducto"),
    btnNuevo: document.getElementById("btnNuevoProducto"),
    btnGuardar: document.getElementById("btnGuardarProducto"),
    btnBuscar: document.getElementById("btnBuscar"),
    btnConfirmarEliminar: document.getElementById("btnConfirmarEliminar"),
    btnCancelarEliminar: document.getElementById("btnCancelarEliminar"),
    mensajeOperacion: document.getElementById("mensajeOperacion"),
    productoIdEliminar: document.getElementById("productoIdEliminar")
};

// Inicialización
document.addEventListener("DOMContentLoaded", function() {
    cargarProductos();
    configurarEventos();
});

// Configurar eventos
function configurarEventos() {
    // Botón Nuevo Producto
    elementos.btnNuevo.addEventListener("click", function() {
        productoActual = null;
        document.getElementById("tituloModal").textContent = "Nuevo Producto";
        limpiarFormulario();
        abrirModal(elementos.modalProducto);
    });

    // Botón Guardar
    elementos.btnGuardar.addEventListener("click", guardarProducto);

    // Botón Buscar
    elementos.btnBuscar.addEventListener("click", buscarProductos);

    // Botón Confirmar Eliminar
    elementos.btnConfirmarEliminar.addEventListener("click", eliminarProductoConfirmado);

    // Botón Cancelar Eliminar
    elementos.btnCancelarEliminar.addEventListener("click", function() {
        cerrarModal(elementos.modalEliminar);
    });

    // Cerrar modales al hacer clic en la X
    document.querySelectorAll(".close").forEach(span => {
        span.addEventListener("click", function() {
            const modal = this.closest(".modal");
            cerrarModal(modal);
        });
    });

    // Cerrar modales al hacer clic fuera
    window.addEventListener("click", function(event) {
        if (event.target.classList.contains("modal")) {
            cerrarModal(event.target);
        }
    });
}

// Funciones CRUD
async function cargarProductos() {
    try {
        mostrarLoading(true);
        const response = await fetch(API_URL);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        
        const productos = await response.json();
        mostrarProductos(productos);
    } catch (error) {
        console.error("Error al cargar productos:", error);
        mostrarError("Error al cargar los productos");
    } finally {
        mostrarLoading(false);
    }
}

async function buscarProductos() {
    const termino = document.getElementById("buscarProducto").value.trim();
    if (!termino) return cargarProductos();

    try {
        mostrarLoading(true);
        const response = await fetch(`${API_URL}search?q=${termino}`);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        
        const productos = await response.json();
        mostrarProductos(productos);
    } catch (error) {
        console.error("Error al buscar productos:", error);
        mostrarError("Error al buscar productos");
    } finally {
        mostrarLoading(false);
    }
}

async function guardarProducto() {
    if (!validarFormulario()) return;

    const producto = {
        name: document.getElementById("nombreProducto").value,
        price: parseFloat(document.getElementById("precioProducto").value),
        volume: parseInt(document.getElementById("volumenProducto").value),
        stock: parseInt(document.getElementById("stockProducto").value)
    };

    // Si estamos editando, agregamos el ID
    if (productoActual) {
        producto.id = productoActual.id;
    }

    try {
        mostrarLoading(true);
        const method = productoActual ? "PUT" : "POST";
        const url = productoActual ? `${API_URL}${productoActual.id}` : API_URL;
        
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        cerrarModal(elementos.modalProducto);
        await cargarProductos();
        mostrarExito(`Producto ${productoActual ? "actualizado" : "creado"} correctamente`);
    } catch (error) {
        console.error("Error al guardar producto:", error);
        mostrarError("Error al guardar el producto");
    } finally {
        mostrarLoading(false);
    }
}

function prepararEliminacion(productoId) {
    elementos.productoIdEliminar.value = productoId;
    abrirModal(elementos.modalEliminar);
}

async function eliminarProductoConfirmado() {
    const productoId = elementos.productoIdEliminar.value;
    if (!productoId) return;

    try {
        mostrarLoading(true);
        const response = await fetch(`${API_URL}${productoId}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        cerrarModal(elementos.modalEliminar);
        await cargarProductos();
        mostrarExito("Producto eliminado correctamente");
    } catch (error) {
        console.error("Error al eliminar producto:", error);
        mostrarError("Error al eliminar el producto");
    } finally {
        mostrarLoading(false);
    }
}

// Funciones de ayuda
function mostrarProductos(productos) {
    elementos.tablaProductos.innerHTML = "";

    if (productos.length === 0) {
        elementos.tablaProductos.innerHTML = `
            <tr>
                <td colspan="6" class="text-center">No hay productos registrados</td>
            </tr>
        `;
        return;
    }

    productos.forEach((producto, index) => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${index + 1}</td>
            <td>${producto.name || "Sin nombre"}</td>
            <td>$${(producto.price || 0).toFixed(2)}</td>
            <td>${producto.volume || 0} cm³</td>
            <td>${producto.stock || 0}</td>
            <td class="text-center action-buttons">
                <button class="btn btn-sm btn-primary btn-editar" data-id="${producto.id}">
                    <i class="fas fa-edit"></i>
                </button>
                <button class="btn btn-sm btn-danger btn-eliminar" data-id="${producto.id}">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        `;
        elementos.tablaProductos.appendChild(fila);
    });

    // Configurar eventos para los botones de acción
    document.querySelectorAll(".btn-editar").forEach(btn => {
        btn.addEventListener("click", function() {
            editarProducto(this.getAttribute("data-id"));
        });
    });

    document.querySelectorAll(".btn-eliminar").forEach(btn => {
        btn.addEventListener("click", function() {
            prepararEliminacion(this.getAttribute("data-id"));
        });
    });
}

async function editarProducto(productoId) {
    try {
        mostrarLoading(true);
        const response = await fetch(`${API_URL}${productoId}`);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        
        productoActual = await response.json();
        document.getElementById("tituloModal").textContent = "Editar Producto";
        llenarFormulario(productoActual);
        abrirModal(elementos.modalProducto);
    } catch (error) {
        console.error("Error al obtener producto:", error);
        mostrarError("Error al cargar el producto para editar");
    } finally {
        mostrarLoading(false);
    }
}

function llenarFormulario(producto) {
    document.getElementById("productoId").value = producto.id || "";
    document.getElementById("nombreProducto").value = producto.name || "";
    document.getElementById("precioProducto").value = producto.price || "";
    document.getElementById("volumenProducto").value = producto.volume || "";
    document.getElementById("stockProducto").value = producto.stock || "";
}

function limpiarFormulario() {
    document.getElementById("productoId").value = "";
    document.getElementById("nombreProducto").value = "";
    document.getElementById("precioProducto").value = "";
    document.getElementById("volumenProducto").value = "";
    document.getElementById("stockProducto").value = "";
}

function validarFormulario() {
    const nombre = document.getElementById("nombreProducto").value;
    const precio = document.getElementById("precioProducto").value;
    const volumen = document.getElementById("volumenProducto").value;
    const stock = document.getElementById("stockProducto").value;

    if (!nombre || !precio || !volumen || !stock) {
        mostrarError("Todos los campos son obligatorios");
        return false;
    }

    if (isNaN(precio) || parseFloat(precio) <= 0) {
        mostrarError("El precio debe ser un número mayor a 0");
        return false;
    }

    if (isNaN(volumen) || parseInt(volumen) <= 0) {
        mostrarError("El volumen debe ser un número mayor a 0");
        return false;
    }

    if (isNaN(stock) || parseInt(stock) < 0) {
        mostrarError("La cantidad en stock no puede ser negativa");
        return false;
    }

    return true;
}

// Funciones de UI
function abrirModal(modal) {
    modal.style.display = "block";
}

function cerrarModal(modal) {
    modal.style.display = "none";
}

function mostrarLoading(mostrar) {
    const spinner = document.querySelector(".spinner-border");
    if (spinner) {
        spinner.style.display = mostrar ? "block" : "none";
    }
}

function mostrarError(mensaje) {
    elementos.mensajeOperacion.textContent = mensaje;
    elementos.mensajeOperacion.className = "alert alert-danger";
    elementos.mensajeOperacion.style.display = "block";
    setTimeout(() => {
        elementos.mensajeOperacion.style.display = "none";
    }, 5000);
}

function mostrarExito(mensaje) {
    elementos.mensajeOperacion.textContent = mensaje;
    elementos.mensajeOperacion.className = "alert alert-success";
    elementos.mensajeOperacion.style.display = "block";
    setTimeout(() => {
        elementos.mensajeOperacion.style.display = "none";
    }, 5000);
}