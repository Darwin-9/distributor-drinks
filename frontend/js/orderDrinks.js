import { urlBase } from './constante.js';

let urlOrderDrinks = `${urlBase}order-drinks/`;
let urlOrders = `${urlBase}orders/`;
let urlDrinks = `${urlBase}drinks/`;

// Función para cargar órdenes y bebidas en los selects
async function cargarSelects() {
    try {
        // Cargar órdenes
        const ordersResponse = await fetch(urlOrders);
        const orders = await ordersResponse.json();
        
        const orderSelect = document.getElementById("orderDrinkOrderId");
        orderSelect.innerHTML = '<option value="">Seleccione una orden</option>';
        
        orders.forEach(order => {
            const option = document.createElement("option");
            option.value = order.order_id;
            option.textContent = `Orden #${order.order_id} - ${new Date(order.order_date).toLocaleString()}`;
            orderSelect.appendChild(option);
        });

        // Cargar bebidas
        const drinksResponse = await fetch(urlDrinks);
        const drinks = await drinksResponse.json();
        
        const drinkSelect = document.getElementById("orderDrinkDrinkId");
        drinkSelect.innerHTML = '<option value="">Seleccione una bebida</option>';
        
        drinks.forEach(drink => {
             // Solo bebidas activas
                const option = document.createElement("option");
                option.value = drink.drink_id;
                option.textContent = `${drink.name} (${drink.volume}ml) - $${drink.price}`;
                drinkSelect.appendChild(option);
            
        });
    } catch (error) {
        console.error("Error al cargar selects:", error);
    }
}

// Llamar a cargarSelects al cargar la página
document.addEventListener("DOMContentLoaded", cargarSelects);

// Registrar detalle de pedido
document.getElementById("orderDrinkForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const orderId = document.getElementById("orderDrinkOrderId").value;
    const drinkId = document.getElementById("orderDrinkDrinkId").value;
    const quantity = document.getElementById("orderDrinkQuantity").value;

    if (!orderId || !drinkId) {
        alert("Por favor seleccione una orden y una bebida");
        return;
    }

    const bodyContent = JSON.stringify({
        "order_id": parseInt(orderId),
        "drink_id": parseInt(drinkId),
        "quantity": parseInt(quantity)
    });

    try {
        const response = await fetch(urlOrderDrinks, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("orderDrinkForm").reset();
            alert("Detalle de pedido registrado exitosamente.");
            buscarOrderDrinks("", "", ""); // Refresca la lista
        } else {
            alert("Error al registrar detalle: " + data);
        }
    } catch (error) {
        console.error("Error al registrar detalle:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Buscar detalles de pedido
document.getElementById("searchOrderDrinkBtn").addEventListener("click", async (e) => {
    e.preventDefault();
    const searchTerm = document.getElementById("searchOrderDrink").value.trim();
    
    // Si el usuario busca por ID de orden o bebida
    const orderId = searchTerm.startsWith("orden:") ? searchTerm.split(":")[1] : "";
    const drinkId = searchTerm.startsWith("bebida:") ? searchTerm.split(":")[1] : "";
    
    await buscarOrderDrinks(orderId, drinkId, "");
});

async function buscarOrderDrinks(orderId, drinkId, quantity) {
    try {
        const url = new URL(`${urlOrderDrinks}filter`);
        if (orderId) url.searchParams.append("orderId", orderId);
        if (drinkId) url.searchParams.append("drinkId", drinkId);
        if (quantity) url.searchParams.append("quantity", quantity);

        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const orderDrinks = await response.json();
        const contenedor = document.getElementById("orderDrinkItems");
        contenedor.innerHTML = "";

        if (orderDrinks.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron detalles de pedido.</li>";
            return;
        }

        orderDrinks.forEach(item => {
            const orderNumber = item.order?.order_id || "Sin orden";
            const drinkName = item.drink?.name || "Bebida desconocida";
            const drinkVolume = item.drink?.volume || "0";
            const drinkPrice = item.drink?.price || 0;
            const quantityOrdered = item.quantity || 0;
            const subtotal = drinkPrice * quantityOrdered;

            const itemElement = document.createElement("li");
            itemElement.className = "order-drink-item";
            itemElement.innerHTML = `
                <span>
                    <strong>Orden #${orderNumber}</strong><br>
                    Bebida: ${drinkName} (${drinkVolume}ml)<br>
                    Precio Unitario: $${drinkPrice}<br>
                    Cantidad: ${quantityOrdered}<br>
                    Subtotal: $${subtotal}
                </span>
                <div class="actions">
                    <button class="update-btn update-order-drink-btn"
                            data-id="${item.order_drink_id}"
                            data-order-id="${item.order?.order_id || ''}"
                            data-drink-id="${item.drink?.drink_id || ''}"
                            data-quantity="${quantityOrdered}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-order-drink-btn" 
                            data-id="${item.order_drink_id}">
                        Eliminar
                    </button>
                </div>
            `;
            contenedor.appendChild(itemElement);
        });
    } catch (error) {
        console.error("Error en buscarOrderDrinks:", error);
        alert("Error al buscar detalles de pedido: " + error.message);
    }
}


// Actualizar detalle de pedido
document.getElementById("updateOrderDrinkForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateOrderDrinkId").value;
    const quantity = document.getElementById("updateOrderDrinkQuantity").value;

    try {
        const response = await fetch(`${urlOrderDrinks}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                quantity: parseInt(quantity)
            })
        });

        if (response.ok) {
            alert("Detalle de pedido actualizado exitosamente.");
            document.getElementById("updateOrderDrinkModal").style.display = "none";
            document.getElementById("searchOrderDrinkBtn").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar detalle: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar detalle:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Eliminar detalle de pedido
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-order-drink-btn")) {
        const orderDrinkId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este detalle de pedido?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlOrderDrinks}${orderDrinkId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Detalle de pedido eliminado correctamente.");
                document.getElementById("searchOrderDrinkBtn").click();
            } else {
                const errorMsg = await response.text();
                alert("Error al eliminar detalle: " + errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar detalle:", error);
            alert("Hubo un error al eliminar el detalle de pedido.");
        }
    }
});