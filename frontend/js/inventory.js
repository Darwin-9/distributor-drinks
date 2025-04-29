import { urlBase } from './constante.js';

let urlInventory = `${urlBase}inventory/`;
let urlDrinks = `${urlBase}drinks/`;

// Función para cargar las bebidas en los select
async function cargarBebidas() {
    try {
        const response = await fetch(urlDrinks);
        const drinks = await response.json();
        
        const selectCreacion = document.getElementById("inventoryDrinkId");
        const selectActualizacion = document.getElementById("updateInventoryDrinkId");
        
        // Limpiar selects
        selectCreacion.innerHTML = '<option value="">Seleccione una bebida</option>';
        selectActualizacion.innerHTML = '';
        
        // Llenar con las bebidas disponibles
        drinks.forEach(drink => {
                const option = document.createElement("option");
                option.value = drink.drink_id;
                option.textContent = `${drink.name} (${drink.volume}ml)`;
                
                selectCreacion.appendChild(option.cloneNode(true));
                selectActualizacion.appendChild(option);
            
        });
    } catch (error) {
        console.error("Error al cargar bebidas:", error);
    }
}

// Llamar a cargarBebidas al cargar la página
document.addEventListener("DOMContentLoaded", cargarBebidas);

// Registrar inventario
document.getElementById("inventoryForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const drinkId = document.getElementById("inventoryDrinkId").value;
    const stock = document.getElementById("inventoryStock").value;

    if (!drinkId) {
        alert("Por favor seleccione una bebida");
        return;
    }

    const bodyContent = JSON.stringify({
        "drink_id": parseInt(drinkId),
        "current_stock": parseInt(stock),
        "last_update": new Date().toISOString()
    });

    try {
        const response = await fetch(urlInventory, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("inventoryForm").reset();
            alert("Registro de inventario creado exitosamente.");
            buscarInventory("", null); // Refresca la lista
        } else {
            alert("Error al registrar inventario: " + data);
        }

        console.log(data);
    } catch (error) {
        console.error("Error al registrar inventario:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Actualizar inventario
document.getElementById("updateInventoryForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateInventoryId").value;
    const drinkId = document.getElementById("updateInventoryDrinkId").value;
    const stock = document.getElementById("updateInventoryStock").value;

    try {
        const response = await fetch(`${urlInventory}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                drink_id: parseInt(drinkId),
                current_stock: parseInt(stock),
                last_update: new Date().toISOString()
            })
        });

        if (response.ok) {
            alert("Inventario actualizado exitosamente.");
            document.getElementById("updateInventoryModal").style.display = "none";
            document.getElementById("searchInventoryBtn").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar inventario: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar inventario:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Buscar inventario
document.getElementById("searchInventoryBtn").addEventListener("click", async (e) => {
    e.preventDefault();
    const searchTerm = document.getElementById("searchInventory").value.trim();
    
    // Si el usuario busca "stock bajo", mostrar solo registros con stock < 10
    const lowStock = searchTerm.toLowerCase() === "stock bajo" ? 10 : null;
    await buscarInventory(searchTerm, lowStock);
});

async function buscarInventory(searchTerm, lowStock) {
    try {
        const url = new URL(`${urlInventory}filter`);
        
        if (searchTerm && searchTerm.toLowerCase() !== "stock bajo") {
            url.searchParams.append("search", searchTerm);
        }
        if (lowStock !== null) {
            url.searchParams.append("current_stock", lowStock);
        }

        const response = await fetch(url);
        const inventory = await response.json();

        const contenedor = document.getElementById("inventoryItems");
        contenedor.innerHTML = "";

        if (inventory.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron registros de inventario.</li>";
            return;
        }

        inventory.forEach(item => {
            const drinkName = item.drink?.name || "Bebida desconocida";
            const drinkVolume = item.drink?.volume || "0";
            const stockActual = item.current_stock ?? "N/A";
            const lastUpdate = item.last_update 
                ? new Date(item.last_update).toLocaleString('es-CO', {
                    year: 'numeric', month: '2-digit', day: '2-digit',
                    hour: '2-digit', minute: '2-digit'
                })
                : "Sin actualización";

            const isLowStock = stockActual < 10;
            const stockClass = isLowStock ? "low-stock" : "";

            const itemElement = document.createElement("li");
            itemElement.className = `inventory-item ${isLowStock ? 'low-stock' : ''}`;
            itemElement.innerHTML = `
                <span>
                    <strong>${drinkName}</strong> (${drinkVolume}ml)<br>
                    Stock: <span class="${stockClass}">${stockActual}</span><br>
                    Última actualización: ${lastUpdate}
                </span>
                <div class="actions">
                    <button class="update-btn update-inventory-btn"
                            data-id="${item.inventory_id}"
                            data-drink-id="${item.drink?.drink_id || ''}"
                            data-stock="${stockActual}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-inventory-btn" 
                            data-id="${item.inventory_id}">
                        Eliminar
                    </button>
                </div>
            `;
            contenedor.appendChild(itemElement);
        });
    } catch (error) {
        console.error("Error en buscarInventory:", error);
        alert("Error al buscar inventario: " + error.message);
    }
}


// Eliminar registro de inventario
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-inventory-btn")) {
        const inventoryId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este registro de inventario?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlInventory}${inventoryId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Registro de inventario eliminado correctamente.");
                document.getElementById("searchInventoryBtn").click(); // Refrescar lista
            } else {
                const errorMsg = await response.text();
                alert("Error al eliminar registro: " + errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar registro de inventario:", error);
            alert("Hubo un error al eliminar el registro de inventario.");
        }
    }
});