import { urlBase } from './constante.js';

let urlStores = `${urlBase}stores/`;

console.log(urlStores);

// Registrar tienda
document.getElementById("storeForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const name = document.getElementById("storeName").value;
    const address = document.getElementById("storeAddress").value;
    const phone = document.getElementById("storePhone").value;
    const city = document.getElementById("storeCity").value;

    const bodyContent = JSON.stringify({
        "name": name,
        "address": address,
        "phone_number": phone,
        "city": city
    });

    try {
        const response = await fetch(urlStores, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("storeForm").reset();
            alert("Tienda registrada exitosamente.");
            buscarTiendas("", "", "", "", ""); // Refresca la lista
        } else {
            alert("Error al registrar tienda: " + data);
        }

        console.log(data);
    } catch (error) {
        console.error("Error al registrar tienda:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Actualizar tienda
document.getElementById("updateStoreForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateStoreId").value;
    const name = document.getElementById("updateStoreName").value;
    const address = document.getElementById("updateStoreAddress").value;
    const phone = document.getElementById("updateStorePhone").value;
    const city = document.getElementById("updateStoreCity").value;

    try {
        const response = await fetch(`${urlStores}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name,
                address,
                phone_number: phone,
                city
            })
        });

        if (response.ok) {
            alert("Tienda actualizada exitosamente.");
            document.getElementById("updateStoreModal").style.display = "none";
            document.getElementById("searchStoreBtn").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar tienda: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar tienda:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Buscar tiendas
document.getElementById("searchStoreBtn").addEventListener("click", async () => {
    const searchTerm = document.getElementById("searchStore").value.trim();
    await buscarTiendas(searchTerm, "", "", "", "");
});

async function buscarTiendas(name, address, phone, city, id) {
    const url = new URL(`${urlStores}filter`);
    
    if (id) url.searchParams.append("id", id);
    if (name) url.searchParams.append("name", name);
    if (address) url.searchParams.append("address", address);
    if (phone) url.searchParams.append("phone_number", phone);
    if (city) url.searchParams.append("city", city);

    try {
        const response = await fetch(url);
        const stores = await response.json();

        const contenedor = document.getElementById("storeItems");
        contenedor.innerHTML = "";

        if (stores.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron tiendas.</li>";
            return;
        }

        stores.forEach(store => {
            const item = document.createElement("li");
            item.classList.add("store-item");

            item.innerHTML = `
                <span>
                    <strong>${store.name}</strong> - ${store.address} - ${store.phone_number} - ${store.city}
                </span>
                <div class="actions">
                    <button class="update-btn update-store-btn"
                            data-id="${store.store_id}"
                            data-name="${store.name}"
                            data-address="${store.address}"
                            data-phone="${store.phone_number}"
                            data-city="${store.city}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-store-btn" data-id="${store.store_id}">Eliminar</button>
                </div>
            `;

            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error al buscar tiendas:", error);
        alert("Hubo un error al buscar tiendas.");
    }
}

// Eliminar tienda
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-store-btn")) {
        const storeId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar esta tienda?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlStores}${storeId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Tienda eliminada correctamente.");
                document.getElementById("searchStoreBtn").click(); // Refrescar lista
            } else {
                const errorMsg = await response.text();
                alert("Error al eliminar tienda: " + errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar tienda:", error);
            alert("Hubo un error al eliminar la tienda.");
        }
    }
});