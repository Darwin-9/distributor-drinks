import { urlBase } from './constante.js';

let urlStoreUsers = `${urlBase}store-users/`;
let urlStores = `${urlBase}stores/`;

// Función para cargar las tiendas en los select
async function cargarTiendas() {
    try {
        const response = await fetch(urlStores);
        const stores = await response.json();
        
        const selectCreacion = document.getElementById("storeUserStoreId");
        const selectActualizacion = document.getElementById("updateStoreUserStoreId");
        
        // Limpiar selects
        selectCreacion.innerHTML = '<option value="">Seleccione una tienda</option>';
        selectActualizacion.innerHTML = '<option value="">Seleccione una tienda</option>';
        
        // Llenar con las tiendas disponibles
        stores.forEach(store => {
            if(store.status) { // Solo tiendas activas
                const option = document.createElement("option");
                option.value = store.store_id;
                option.textContent = `${store.name} (${store.city})`;
                
                selectCreacion.appendChild(option.cloneNode(true));
                selectActualizacion.appendChild(option.cloneNode(true));
            }
        });
    } catch (error) {
        console.error("Error al cargar tiendas:", error);
    }
}

// Llamar a cargarTiendas al cargar la página
document.addEventListener("DOMContentLoaded", cargarTiendas);

// Registrar usuario de tienda
document.getElementById("storeUserForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const username = document.getElementById("storeUserName").value;
    const email = document.getElementById("storeUserEmail").value;
    const password = document.getElementById("storeUserPassword").value;
    const storeId = document.getElementById("storeUserStoreId").value;

    // Validación básica
    if (!storeId) {
        alert("Por favor seleccione una tienda");
        return;
    }

    if (username.length < 3 || username.length > 50) {
        alert("El nombre de usuario debe tener entre 3 y 50 caracteres");
        return;
    }

    try {
        const response = await fetch(urlStoreUsers, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                email,
                password,
                store_id: parseInt(storeId)
            })
        });

        if (response.ok) {
            document.getElementById("storeUserForm").reset();
            alert("Usuario registrado exitosamente");
            buscarStoreUsers(""); // Refrescar lista
        } else {
            alert("Error al registrar usuario");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error en la solicitud");
    }
});


// Actualizar usuario de tienda
document.getElementById("updateStoreUserForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateStoreUserId").value;
    const username = document.getElementById("updateStoreUserName").value;
    const email = document.getElementById("updateStoreUserEmail").value;
    const storeId = document.getElementById("updateStoreUserStoreId").value;

    // Validación básica
    if (!storeId) {
        alert("Por favor seleccione una tienda");
        return;
    }

    try {
        const response = await fetch(`${urlStoreUsers}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                email: email,
                store_id: parseInt(storeId)
            })
        });

        if (response.ok) {
            alert("Usuario actualizado exitosamente.");
            document.getElementById("updateStoreUserModal").style.display = "none";
            buscarStoreUsers(""); // Refrescar lista
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar usuario: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar usuario:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Buscar usuarios de tienda
document.getElementById("searchStoreUserBtn").addEventListener("click", async (e) => {
    e.preventDefault();
    const searchTerm = document.getElementById("searchStoreUser").value.trim();
    await buscarStoreUsers(searchTerm);
});

async function buscarStoreUsers(searchTerm) {
    try {
        const url = new URL(`${urlStoreUsers}filter`);
        if (searchTerm) url.searchParams.append("search", searchTerm);

        const response = await fetch(url);
        const storeUsers = await response.json();
        const contenedor = document.getElementById("storeUserItems");
        contenedor.innerHTML = "";

        if (storeUsers.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron usuarios.</li>";
            return;
        }

        storeUsers.forEach(user => {
            const item = document.createElement("li");
            item.className = "store-user-item";
            item.innerHTML = `
                <span>
                    <strong>${user.username}</strong> - ${user.email}<br>
                    Tienda: ${user.store.name} - Estado: ${user.status ? 'Activo' : 'Inactivo'}
                </span>
                <div class="actions">
                    <button class="update-btn update-store-user-btn"
                            data-id="${user.store_user_id}"
                            data-username="${user.username}"
                            data-email="${user.email}"
                            data-store-id="${user.store.store_id}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-store-user-btn" 
                            data-id="${user.store_user_id}">
                        Eliminar
                    </button>
                </div>
            `;
            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error al buscar usuarios:", error);
        alert("Error al buscar usuarios");
    }
}


// Manejar clic en botón actualizar
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-store-user-btn')) {
        const btn = e.target;
        const userId = btn.getAttribute('data-id');
        
        // Llenar el formulario de actualización
        document.getElementById('updateStoreUserId').value = userId;
        document.getElementById('updateStoreUserName').value = btn.dataset.username;
        document.getElementById('updateStoreUserEmail').value = btn.dataset.email;
        
        // Seleccionar la tienda actual en el select
        const storeId = btn.dataset.storeId;
        const storeSelect = document.getElementById("updateStoreUserStoreId");
        storeSelect.value = storeId;

        // Mostrar el modal
        document.getElementById("updateStoreUserModal").style.display = "block";
    }
});

// Eliminar usuario de tienda
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-store-user-btn")) {
        const userId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este usuario de tienda?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlStoreUsers}${userId}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });

            if (response.ok) {
                const result = await response.json();
                alert(result.message || "Usuario eliminado correctamente.");
                document.getElementById("searchStoreUserBtn").click(); // Refrescar lista
            } else {
                const errorData = await response.json().catch(() => null);
                const errorMsg = errorData?.message || `Error ${response.status}`;
                alert(errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar usuario:", error);
            alert("Hubo un error al eliminar el usuario.");
        }
    }
});