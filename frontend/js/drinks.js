import { urlBase } from './constante.js';

let urlDrinks = `${urlBase}drinks/`;

console.log(urlDrinks);

//registrar

document.getElementById("drinkForm").addEventListener("submit", async (event) => {
    event.preventDefault(); // evita que se recargue la pagina al enviar el formulario

    let nombre = document.getElementById("drinkName").value;
    let precio = document.getElementById("drinkPrice").value;
    let volumen = document.getElementById("drinkVolume").value;
    let stock = document.getElementById("drinkStock").value;

    let bodyContent = JSON.stringify({
        "name": nombre,
        "price": precio,
        "volume": volumen,
        "stock": stock
    });

    try {
        let response = await fetch(urlDrinks, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                'Content-Type': 'application/json'
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("drinkForm").reset(); // Limpiar el formulario
            alert("Registro exitoso");
            buscarBebidasPorNombre(""); // Mostrar todas las bebidas de nuevo
        }
        else {
            alert("Error al registrar bebida: " + data);
        }
        console.log(data);
    } catch (error) {
        console.error("Error:", error);
        alert("Error al registrar bebida: " + error);
    }

});


//Actualizar

document.getElementById("updateDrinkForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateDrinkId").value;
    const name = document.getElementById("updateDrinkName").value;
    const volume = document.getElementById("updateDrinkVolume").value;
    const price = document.getElementById("updateDrinkPrice").value;
    const stock = document.getElementById("updateDrinkStock").value;

    try {
        const response = await fetch(`${urlDrinks}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name,
                volume,
                price,
                stock
            })
        });

        if (response.ok) {
            alert("Bebida actualizada exitosamente.");
            document.getElementById("updateModal").style.display = "none";
            // Opcional: recargar la lista
            document.getElementById("searchBtn").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar bebida: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar bebida:", error);
        alert("Error en la solicitud: " + error);
    }
});




//filtrar
document.getElementById("searchBtn").addEventListener("click", async () => {
    const searchTerm = document.getElementById("searchDrink").value.trim();
    await buscarBebidasPorNombre(searchTerm);
});

async function buscarBebidasPorNombre(searchTerm) {
    const url = new URL(`${urlDrinks}filter`);
    if (searchTerm !== "") {
        url.searchParams.append("search", searchTerm);
    }

    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error(await response.text());
        
        const bebidas = await response.json();
        mostrarBebidas(bebidas);
    } catch (error) {
        console.error("Error al buscar bebidas:", error);
        alert("Hubo un error al buscar bebidas: " + error.message);
    }
}

function mostrarBebidas(bebidas) {
    const contenedor = document.getElementById("drinkItems");
    contenedor.innerHTML = "";

    if (bebidas.length === 0) {
        contenedor.innerHTML = "<li>No se encontraron bebidas.</li>";
        return;
    }

    bebidas.forEach(bebida => {
        const item = document.createElement("li");
        item.classList.add("drink-item");

        item.innerHTML = `
            <span>
                <strong>${bebida.name}</strong> - ${bebida.volume}ml - $${bebida.price} - Stock: ${bebida.stock}
            </span>
            <div class="actions">
                <button class="update-btn update-drink-btn" 
                        data-id="${bebida.drink_id}"
                        data-name="${bebida.name}"
                        data-volume="${bebida.volume}"
                        data-price="${bebida.price}"
                        data-stock="${bebida.stock}">
                    Actualizar
                </button>
                <button class="delete-btn delete-drink-btn" data-id="${bebida.drink_id}">Eliminar</button>
            </div>
        `;

        contenedor.appendChild(item);
    });
}

//eliminar
document.addEventListener("click", async (event) => {
    if (event.target.classList.contains("delete-drink-btn")) {
        const id = event.target.getAttribute("data-id");
        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar esta bebida?");

        if (confirmDelete) {
            try {
                const response = await fetch(`${urlDrinks}${id}`, {
                    method: "DELETE",
                    headers: {
                        "Accept": "*/*",
                        "Content-Type": "application/json"
                    }
                });

                if (response.ok) {
                    alert("Bebida eliminada exitosamente.");
                    // Opcional: recargar la lista
                    document.getElementById("searchBtn").click();
                } else {
                    const errorMsg = await response.text();
                    alert("Error al eliminar bebida: " + errorMsg);
                }
            } catch (error) {
                console.error("Error al eliminar bebida:", error);
                alert("Error en la solicitud: " + error);
            }
        }
    }
   

});





