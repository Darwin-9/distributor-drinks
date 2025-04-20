import { urlBase } from './constante.js';

let urlOrders = `${urlBase}orders/`;
let urlStores = `${urlBase}stores/`;

// Función para cargar las tiendas en los select
async function cargarTiendas() {
    try {
        const response = await fetch(urlStores);
        const stores = await response.json();
        
        const selectCreacion = document.getElementById("orderStoreId");
        const selectActualizacion = document.getElementById("updateOrderStoreId");
        
        // Limpiar selects
        selectCreacion.innerHTML = '<option value="">Seleccione una tienda</option>';
        selectActualizacion.innerHTML = '<option value="">Seleccione una tienda</option>';
        
        // Llenar con las tiendas disponibles
        stores.forEach(store => {
            if(store.status) {
                const option = document.createElement("option");
                option.value = store.store_id;
                option.textContent = `${store.name} (${store.city})`;
                
                selectCreacion.appendChild(option.cloneNode(true));
                selectActualizacion.appendChild(option.cloneNode(true));
            }
        });
    } catch (error) {
        console.error("Error al cargar tiendas:", error);
        alert("Error al cargar la lista de tiendas");
    }
}

// Llamar a cargarTiendas al cargar la página
document.addEventListener("DOMContentLoaded", cargarTiendas);

// Registrar orden
document.getElementById("orderForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const storeId = document.getElementById("orderStoreId").value;
    const orderDate = document.getElementById("orderDate").value;

    if (!storeId) {
        alert("Por favor seleccione una tienda");
        return;
    }

    const bodyContent = JSON.stringify({
        "store_id": parseInt(storeId),
        "order_date": orderDate,
        "status": true
    });

    try {
        const response = await fetch(urlOrders, {
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        const data = await response.json();

        if (response.ok) {
            document.getElementById("orderForm").reset();
            alert(data.message || "Orden registrada exitosamente.");
            buscarOrders("", "");
        } else {
            alert(data.message || "Error al registrar orden");
        }
    } catch (error) {
        console.error("Error al registrar orden:", error);
        alert("Error en la solicitud: " + error.message);
    }
});

// Actualizar orden
// Actualizar orden - Versión corregida
document.getElementById("updateOrderForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateOrderId").value;
    const storeId = document.getElementById("updateOrderStoreId").value;
    const orderDate = document.getElementById("updateOrderDate").value;

    try {
        const response = await fetch(`${urlOrders}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({
                store_id: parseInt(storeId),
                order_date: orderDate
            })
        });

        const data = await response.json().catch(() => null);

        if (response.ok) {
            document.getElementById("updateOrderModal").style.display = "none";
            buscarOrders("", ""); // Refrescar lista
            console.log("Orden actualizada:", data?.message);
        } else {
            console.error("Error al actualizar:", response.status, data?.message);
        }
    } catch (error) {
        console.error("Error de conexión:", error);
    }
});

// Buscar órdenes
document.getElementById("searchOrderBtn").addEventListener("click", async (e) => {
    e.preventDefault();
    const searchTerm = document.getElementById("searchOrder").value.trim();
    
    if (searchTerm.match(/\d{4}-\d{2}-\d{2}/)) {
        await buscarOrders(searchTerm, "");
    } else {
        await buscarOrders("", searchTerm);
    }
});

async function buscarOrders(date, searchTerm) {
    try {
        const url = new URL(`${urlOrders}filter`);
        
        if (date) url.searchParams.append("order_date", date);
        
        if (searchTerm.toLowerCase() === "inactivos") {
            url.searchParams.append("status", false);
        } else {
            url.searchParams.append("status", true);
        }

        const response = await fetch(url);
        const orders = await response.json();

        const contenedor = document.getElementById("orderItems");
        contenedor.innerHTML = "";

        if (orders.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron órdenes.</li>";
            return;
        }

        orders.forEach(order => {
            const storeInfo = order.store_name ? 
                `${order.store_name} (${order.store_city})` : 
                "Sin tienda asignada";
            
            const formattedDate = new Date(order.order_date).toLocaleString('es-CO', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit'
            });
            
            const item = document.createElement("li");
            item.className = "order-item";
            item.innerHTML = `
                <span>
                    <strong>Orden #${order.order_id}</strong><br>
                    Fecha: ${formattedDate}<br>
                    Tienda: ${storeInfo}<br>
                    Estado: ${order.status ? 'Activo' : 'Inactivo'}
                </span>
                <div class="actions">
                    <button class="update-btn update-order-btn"
                            data-id="${order.order_id}"
                            data-date="${order.order_date}"
                            data-store-id="${order.store_id}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-order-btn" 
                            data-id="${order.order_id}">
                        Eliminar
                    </button>
                </div>
            `;
            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error en buscarOrders:", error);
        alert("Error al buscar órdenes: " + error.message);
    }
}

// Manejar clic en botón actualizar
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-order-btn')) {
        const btn = e.target;
        const orderId = btn.getAttribute('data-id');
        
        document.getElementById('updateOrderId').value = orderId;
        document.getElementById('updateOrderModal').dataset.currentId = orderId;

        document.getElementById("updateOrderDate").value = formatDateForInput(btn.dataset.date);
        
        const storeId = btn.dataset.storeId;
        const storeSelect = document.getElementById("updateOrderStoreId");
        storeSelect.value = storeId;

        document.getElementById("updateOrderModal").style.display = "block";
    }
});

// Función para formatear fecha al input datetime-local
function formatDateForInput(dateString) {
    const date = new Date(dateString);
    const isoString = date.toISOString();
    return isoString.substring(0, isoString.length - 1);
}

// Eliminar orden - Versión definitiva
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-order-btn")) {
        const orderId = e.target.dataset.id;

        if (!confirm("¿Estás seguro de que deseas desactivar esta orden?")) return;

        try {
            const response = await fetch(`${urlOrders}${orderId}`, {
                method: "DELETE",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                }
            });

            const data = await response.json().catch(() => null);

            if (response.ok) {
                buscarOrders("", ""); // Refrescar lista
                // Opcional: mostrar mensaje de éxito
                console.log("Orden desactivada:", data?.message);
            } else {
                console.error("Error en la respuesta:", response.status, data?.message);
            }
        } catch (error) {
            console.error("Error de conexión:", error);
        }
    }
});