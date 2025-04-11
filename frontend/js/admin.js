// Variables globales
let modal = document.getElementById("myModal");
let btn = document.getElementById("myBtn");
let span = document.getElementsByClassName("close")[0];
let btnGuardar = document.getElementById("btnGuardar");
let productoActual = null; // Para manejar edición

// Event Listeners
document.addEventListener("DOMContentLoaded", function() {
    getProductos();
});

btn.onclick = function() {
    productoActual = null; // Indica que es un nuevo producto
    document.getElementById("tituloModal").innerText = "Crear Producto";
    limpiarFormulario();
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

btnGuardar.onclick = function() {
    if (validarFormulario()) {
        if (productoActual) {
            updateProducto();
        } else {
            registerProducto();
        }
    }
}

// Validación del formulario
function validarFormulario() {
    const nombre = document.getElementById("nombre").value;
    const precio = document.getElementById("precio").value;
    const volumen = document.getElementById("volumen").value;
    const cantidad = document.getElementById("cantidad").value;

    if (!nombre || !precio || !volumen || !cantidad) {
        alert("Todos los campos son obligatorios");
        return false;
    }

    if (parseFloat(precio) <= 0) {
        alert("El precio debe ser mayor a 0");
        return false;
    }

    if (parseFloat(volumen) <= 0) {
        alert("El volumen debe ser mayor a 0");
        return false;
    }

    if (parseInt(cantidad) < 0) {
        alert("La cantidad no puede ser negativa");
        return false;
    }

    return true;
}

// Funciones CRUD
function registerProducto() {
    return new Promise(async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "name": document.getElementById("nombre").value,
            "price": parseFloat(document.getElementById("precio").value),
            "volume": parseFloat(document.getElementById("volumen").value),
            "stock": parseInt(document.getElementById("cantidad").value)
        });

        try {
            let response = await fetch("http://localhost:8080/api/v1/drinks/", {
                method: "POST",
                body: bodyContent,
                headers: headersList
            });

            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }

            let data = await response.json();
            console.log("Producto creado:", data);
            modal.style.display = "none";
            getProductos();
        } catch (error) {
            console.error("Error al crear producto:", error);
            alert("Error al crear el producto. Verifica la consola para más detalles.");
        }
    });
}

function getProductos() {
    return new Promise(async (resolve) => {
        var url = "http://localhost:8080/api/v1/drinks/";
        
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        try {
            let response = await fetch(url, {
                method: "GET",
                headers: headersList
            });

            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }

            let data = await response.json();
            renderizarProductos(data);
        } catch (error) {
            console.error("Error al obtener productos:", error);
            alert("Error al cargar los productos. Verifica la consola para más detalles.");
        }
    });
}

function renderizarProductos(productos) {
    var tabla = document.getElementById("tablaProductos");
    tabla.innerHTML = "";
    
    if (productos.length === 0) {
        tabla.innerHTML = `<tr><td colspan="6" class="text-center">No hay productos registrados</td></tr>`;
        return;
    }
    
    productos.forEach((producto, index) => {
        var row = document.createElement("tr");
        
        // Número
        var th = document.createElement("th");
        th.scope = "row";
        th.innerText = index + 1;
        row.appendChild(th);
        
        // Nombre
        var tdNombre = document.createElement("td");
        tdNombre.innerText = producto.name;
        row.appendChild(tdNombre);
        
        // Precio
        var tdPrecio = document.createElement("td");
        tdPrecio.innerText = `$${producto.price.toFixed(2)}`;
        row.appendChild(tdPrecio);
        
        // Volumen
        var tdVolumen = document.createElement("td");
        tdVolumen.innerText = `${producto.volume} cm³`;
        row.appendChild(tdVolumen);
        
        // Cantidad
        var tdCantidad = document.createElement("td");
        tdCantidad.innerText = producto.stock;
        row.appendChild(tdCantidad);
        
        // Acciones
        var tdAcciones = document.createElement("td");
        tdAcciones.className = "d-flex";
        
        var btnEdit = document.createElement("button");
        btnEdit.className = "btn btn-warning btn-sm mr-2";
        btnEdit.innerHTML = '<i class="fas fa-edit"></i> Editar';
        btnEdit.onclick = function() {
            editarProducto(producto);
        };
        
        var btnDelete = document.createElement("button");
        btnDelete.className = "btn btn-danger btn-sm";
        btnDelete.innerHTML = '<i class="fas fa-trash"></i> Eliminar';
        btnDelete.onclick = function() {
            eliminarProducto(producto.id);
        };
        
        tdAcciones.appendChild(btnEdit);
        tdAcciones.appendChild(btnDelete);
        row.appendChild(tdAcciones);
        
        tabla.appendChild(row);
    });
}

function editarProducto(producto) {
    productoActual = producto;
    document.getElementById("tituloModal").innerText = "Editar Producto";
    
    // Llenar formulario con datos del producto
    document.getElementById("nombre").value = producto.name;
    document.getElementById("precio").value = producto.price;
    document.getElementById("volumen").value = producto.volume;
    document.getElementById("cantidad").value = producto.stock;
    
    modal.style.display = "block";
}

function updateProducto() {
    return new Promise(async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": productoActual.id,
            "name": document.getElementById("nombre").value,
            "price": parseFloat(document.getElementById("precio").value),
            "volume": parseFloat(document.getElementById("volumen").value),
            "stock": parseInt(document.getElementById("cantidad").value)
        });

        try {
            let response = await fetch("http://localhost:8080/api/v1/drinks/", {
                method: "PUT",
                body: bodyContent,
                headers: headersList
            });

            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }

            let data = await response.json();
            console.log("Producto actualizado:", data);
            modal.style.display = "none";
            getProductos();
        } catch (error) {
            console.error("Error al actualizar producto:", error);
            alert("Error al actualizar el producto. Verifica la consola para más detalles.");
        }
    });
}

function eliminarProducto(id) {
    if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
        return new Promise(async (resolve) => {
            let headersList = {
                "Accept": "*/*",
                "User-Agent": "web",
                "Content-Type": "application/json"
            }

            try {
                let response = await fetch(`http://localhost:8080/api/v1/drinks/${id}`, {
                    method: "DELETE",
                    headers: headersList
                });

                if (!response.ok) {
                    throw new Error(`Error HTTP: ${response.status}`);
                }

                let data = await response.json();
                console.log("Producto eliminado:", data);
                getProductos();
            } catch (error) {
                console.error("Error al eliminar producto:", error);
                alert("Error al eliminar el producto. Verifica la consola para más detalles.");
            }
        });
    }
}

function limpiarFormulario() {
    document.getElementById("nombre").value = "";
    document.getElementById("precio").value = "";
    document.getElementById("volumen").value = "";
    document.getElementById("cantidad").value = "";
}