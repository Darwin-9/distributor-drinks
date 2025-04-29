import { urlBase } from './constante.js';

let urlDeliveries = `${urlBase}deliveries/`;
let urlOrders = `${urlBase}orders/`;
let urlTrucks = `${urlBase}trucks/`;
let urlDrivers = `${urlBase}drivers/`;

// Función para cargar datos en los selects
// Función para cargar datos en los selects - Versión corregida
async function cargarDependencias() {
    try {
        // Cargar órdenes
        const ordersResponse = await fetch(urlOrders);
        const orders = await ordersResponse.json();
        
        // Cargar camiones
        const trucksResponse = await fetch(urlTrucks);
        const trucks = await trucksResponse.json();
        
        // Cargar conductores
        const driversResponse = await fetch(urlDrivers);
        const drivers = await driversResponse.json();
        
        // Llenar selects de creación
        llenarSelect('deliveryOrderId', orders.filter(o => o.status), 'order_id', (order) => `Orden #${order.order_id}`);
        llenarSelect('deliveryTruckId', trucks.filter(t => t.status), 'truck_id', (truck) => `${truck.model} (${truck.plate_number})`);
        llenarSelect('deliveryDriverId', drivers.filter(d => d.status), 'driver_id', (driver) => `${driver.first_name} ${driver.last_name}`);
        
        // Llenar selects de actualización
        llenarSelect('updateDeliveryOrderId', orders.filter(o => o.status), 'order_id', (order) => `Orden #${order.order_id}`);
        llenarSelect('updateDeliveryTruckId', trucks.filter(t => t.status), 'truck_id', (truck) => `${truck.model} (${truck.plate_number})`);
        llenarSelect('updateDeliveryDriverId', drivers.filter(d => d.status), 'driver_id', (driver) => `${driver.first_name} ${driver.last_name}`);
        
    } catch (error) {
        console.error("Error al cargar dependencias:", error);
    }
}

// Función llenarSelect corregida
function llenarSelect(selectId, items, valueField, textFunction) {
    const select = document.getElementById(selectId);
    select.innerHTML = `<option value="">Seleccione una opción</option>`;
    
    if (!items) return;
    
    items.forEach(item => {
        const option = document.createElement("option");
        option.value = item[valueField];
        option.textContent = textFunction(item); // Usamos la función para generar el texto
        select.appendChild(option);
    });
}

// Llamar al cargar la página
document.addEventListener("DOMContentLoaded", cargarDependencias);

// Registrar entrega - Versión corregida
document.getElementById("deliveryForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const orderId = document.getElementById("deliveryOrderId").value;
    const truckId = document.getElementById("deliveryTruckId").value;
    const driverId = document.getElementById("deliveryDriverId").value;
    const deliveryDate = document.getElementById("deliveryDate").value;

    if (!orderId || !truckId || !driverId) {
        alert("Por favor complete todos los campos");
        return;
    }

    const bodyContent = JSON.stringify({
        order: parseInt(orderId),
        truck: parseInt(truckId),  
        driver: parseInt(driverId), 
        delivery_date: deliveryDate
    });

    try {
        const response = await fetch(urlDeliveries, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: bodyContent
        });

        const data = await response.json();

        if (response.ok) {
            document.getElementById("deliveryForm").reset();
            buscarDeliveries("", "");
            alert(data.message || "Entrega registrada exitosamente");
        } else {
            alert(data.message || "Error al registrar entrega: " + (data.error || ""));
        }
    } catch (error) {
        console.error("Error al registrar entrega:", error);
        alert("Error en la solicitud: " + error.message);
    }
});



// Actualizar entrega - Versión corregida
document.getElementById("updateDeliveryForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateDeliveryId").value;
    const orderId = document.getElementById("updateDeliveryOrderId").value;
    const truckId = document.getElementById("updateDeliveryTruckId").value;
    const driverId = document.getElementById("updateDeliveryDriverId").value;
    const deliveryDate = document.getElementById("updateDeliveryDate").value;

    try {
        const response = await fetch(`${urlDeliveries}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({
                order: parseInt(orderId),  // Cambiado de order_id a order
                truck: parseInt(truckId),  // Cambiado de truck_id a truck
                driver: parseInt(driverId), // Cambiado de driver_id a driver
                delivery_date: deliveryDate
            })
        });

        const data = await response.json();

        if (response.ok) {
            document.getElementById("updateDeliveryModal").style.display = "none";
            buscarDeliveries("", "");
            console.log("Entrega actualizada:", data.message);
        } else {
            console.error("Error al actualizar:", response.status, data.message);
        }
    } catch (error) {
        console.error("Error de conexión:", error);
    }
});



// Buscar entregas
document.getElementById("searchDeliveryBtn").addEventListener("click", async (e) => {
    e.preventDefault();
    const searchTerm = document.getElementById("searchDelivery").value.trim();
    
    if (searchTerm.toLowerCase() === "inactivos") {
        await buscarDeliveries("", false);
    } else if (searchTerm.match(/\d{4}-\d{2}-\d{2}/)) {
        await buscarDeliveries(searchTerm, "");
    } else {
        await buscarDeliveries("", true);
    }
});

async function buscarDeliveries(date, status) {
    try {
        const url = new URL(`${urlDeliveries}filter`);
        if (date) url.searchParams.append("delivery_date", date);
        if (status !== undefined && status !== "") url.searchParams.append("status", status);

        const response = await fetch(url);
        const deliveries = await response.json();

        const contenedor = document.getElementById("deliveryItems");
        contenedor.innerHTML = "";

        if (deliveries.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron entregas.</li>";
            return;
        }

        deliveries.forEach(delivery => {
            const formattedDate = new Date(delivery.delivery_date).toLocaleString('es-CO', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit'
            });

            // Datos extra que queremos mostrar
            const storeName = delivery.order?.store?.name || 'Sin tienda asignada';
            const truckInfo = delivery.truck ? `${delivery.truck.model} (${delivery.truck.plate_number})` : 'Sin camión asignado';
            const driverName = delivery.driver ? `${delivery.driver.first_name} ${delivery.driver.last_name}` : 'Sin conductor asignado';
            
            const item = document.createElement("li");
            item.className = "delivery-item";
            item.innerHTML = `
                <span>
                    <strong>Entrega #${delivery.delivery_id}</strong><br>
                    Tienda: ${storeName}<br>
                    Camión: ${truckInfo}<br>
                    Conductor: ${driverName}<br>
                    Fecha: ${formattedDate}<br>
                    Estado: ${delivery.status ? 'Activo' : 'Inactivo'}
                </span>
                <div class="actions">
                    <button class="update-btn update-delivery-btn"
                            data-id="${delivery.delivery_id}"
                            data-order-id="${delivery.order?.order_id || ''}"
                            data-truck-id="${delivery.truck?.truck_id || ''}"
                            data-driver-id="${delivery.driver?.driver_id || ''}"
                            data-date="${delivery.delivery_date}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-delivery-btn" 
                            data-id="${delivery.delivery_id}">
                        Eliminar
                    </button>
                </div>
            `;
            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error al buscar entregas:", error);
        alert("Error al buscar entregas: " + error.message);
    }
}



// Manejar clic en botón actualizar
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-delivery-btn')) {
        const btn = e.target;
        const deliveryId = btn.getAttribute('data-id');
        
        document.getElementById('updateDeliveryId').value = deliveryId;
        document.getElementById('updateDeliveryOrderId').value = btn.dataset.orderId;
        document.getElementById('updateDeliveryTruckId').value = btn.dataset.truckId;
        document.getElementById('updateDeliveryDriverId').value = btn.dataset.driverId;
        document.getElementById('updateDeliveryDate').value = formatDateForInput(btn.dataset.date);

        document.getElementById("updateDeliveryModal").style.display = "block";
    }
});

// Eliminar entrega
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-delivery-btn")) {
        const deliveryId = e.target.dataset.id;

        if (!confirm("¿Estás seguro de que deseas eliminar esta entrega?")) return;

        try {
            const response = await fetch(`${urlDeliveries}${deliveryId}`, {
                method: "DELETE",
                headers: {
                    "Accept": "application/json"
                }
            });

            if (response.ok) {
                buscarDeliveries("", "");
                console.log("Entrega eliminada correctamente");
            } else {
                console.error("Error en la respuesta:", response.status);
            }
        } catch (error) {
            console.error("Error de conexión:", error);
        }
    }
});

// Función para formatear fecha al input datetime-local
function formatDateForInput(dateString) {
    const date = new Date(dateString);
    const isoString = date.toISOString();
    return isoString.substring(0, isoString.length - 1);
}