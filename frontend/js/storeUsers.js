import { urlBase } from './constante.js';

let urlStoreUsers = `${urlBase}store-users/`;
let urlStores = `${urlBase}stores/`; // Nueva URL para obtener las tiendas

// Función para cargar las tiendas en los select
async function cargarTiendas() {
    try {
        const response = await fetch(urlStores);
        const stores = await response.json();
        
        const selectCreacion = document.getElementById("storeUserStoreId");
        const selectActualizacion = document.getElementById("updateStoreUserStoreId");
        
        // Limpiar selects
        selectCreacion.innerHTML = '<option value="">Seleccione una tienda</option>';
        selectActualizacion.innerHTML = '';
        
        // Llenar con las tiendas disponibles
        stores.forEach(store => {
            if(store.status) { // Solo tiendas activas
                const option = document.createElement("option");
                option.value = store.store_id;
                option.textContent = `${store.name} (ID: ${store.store_id})`;
                
                selectCreacion.appendChild(option.cloneNode(true));
                selectActualizacion.appendChild(option);
            }
        });
    } catch (error) {
        console.error("Error al cargar tiendas:", error);
        alert("Error al cargar la lista de tiendas");
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

    if (!storeId) {
        alert("Por favor seleccione una tienda");
        return;
    }

    const bodyContent = JSON.stringify({
        "username": username,
        "email": email,
        "password": password,
        "store_id": parseInt(storeId)
    });

    try {
        const response = await fetch(urlStoreUsers, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("storeUserForm").reset();
            alert("Usuario de tienda registrado exitosamente.");
            buscarStoreUsers("", ""); // Refresca la lista
        } else {
            alert("Error al registrar usuario: " + data);
        }

        console.log(data);
    } catch (error) {
        console.error("Error al registrar usuario:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Actualizar usuario de tienda
document.getElementById("updateStoreUserForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateStoreUserId").value;
    const username = document.getElementById("updateStoreUserName").value;
    const email = document.getElementById("updateStoreUserEmail").value;
    const storeId = document.getElementById("updateStoreUserStoreId").value;

    try {
        const response = await fetch(`${urlStoreUsers}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                email,
                store_id: storeId
            })
        });

        if (response.ok) {
            alert("Usuario de tienda actualizado exitosamente.");
            document.getElementById("updateStoreUserModal").style.display = "none";
            document.getElementById("searchStoreUserBtn").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar usuario: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar usuario:", error);
        alert("Error en la solicitud: " + error);
    }
});



// Buscar usuarios de tienda - Versión corregida
document.getElementById("searchStoreUserBtn").addEventListener("click", async (e) => {
    e.preventDefault(); // Prevenir comportamiento por defecto
    const searchTerm = document.getElementById("searchStoreUser").value.trim();
    await buscarStoreUsers(searchTerm, "");
});

async function buscarStoreUsers(username, email) {
    try {
        const url = new URL(`${urlBase}store-users/filter`);
        if (username) url.searchParams.append("username", username);
        if (email) url.searchParams.append("email", email);
        url.searchParams.append("status", true); // Solo usuarios activos

        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const storeUsers = await response.json();
        const contenedor = document.getElementById("storeUserItems");
        contenedor.innerHTML = "";

        if (storeUsers.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron usuarios.</li>";
            return;
        }

        storeUsers.forEach(user => {
            const storeInfo = user.store_id ? 
                `${user.store_name} (ID: ${user.store_id})` : 
                "Sin tienda asignada";
            
            const item = document.createElement("li");
            item.className = "store-user-item";
            item.innerHTML = `
                <span>
                    <strong>${user.username}</strong> - ${user.email}<br>
                    Tienda: ${storeInfo} - Estado: ${user.status ? 'Activo' : 'Inactivo'}
                </span>
                <div class="actions">
                    <button class="update-btn update-store-user-btn"
                            data-id="${user.store_user_id}"
                            data-username="${user.username}"
                            data-email="${user.email}"
                            data-store-id="${user.store_id}">
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
        console.error("Error en buscarStoreUsers:", error);
        alert("Error al buscar usuarios: " + error.message);
    }
}

// Modificar el evento click para el modal de actualización
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-store-user-btn')) {
        const btn = e.target;
        const userId = btn.getAttribute('data-id');
        
        document.getElementById('updateStoreUserId').value = userId;
        document.getElementById('updateStoreUserModal').dataset.currentId = userId;

        document.getElementById("updateStoreUserName").value = btn.dataset.username;
        document.getElementById("updateStoreUserEmail").value = btn.dataset.email;
        
        // Seleccionar la tienda correcta en el select
        const storeId = btn.dataset.storeId;
        const storeSelect = document.getElementById("updateStoreUserStoreId");
        storeSelect.value = storeId;

        document.getElementById("updateStoreUserModal").style.display = "block";
    }
});