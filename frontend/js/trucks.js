import { urlBase } from './constante.js';

let urlTrucks = `${urlBase}trucks/`;

console.log(urlTrucks);

//registrar

document.getElementById("truckForm").addEventListener("submit", async (event) => {
    event.preventDefault(); // evita que se recargue la pagina al enviar el formulario

    let modelo = document.getElementById("truckModel").value;
    let placa = document.getElementById("truckPlate").value;
    let capacidad = document.getElementById("truckCapacity").value;

    let bodyContent = JSON.stringify({
        "model": modelo,
        "plate_number": placa,
        "capacity": capacidad,
    });

    try {
        let response = await fetch(urlTrucks, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                'Content-Type': 'application/json'
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("truckForm").reset(); // Limpiar el formulario
            alert("Registro exitoso");
            buscarCamionesPorModelo(""); // Mostrar todas las bebidas de nuevo
        }else {
            alert("Error al registrar camion: " + data);
        }
        console.log(data);
    } catch (error) {
        console.error("Error:", error);
        alert("Error al registrar camion: " + error);
    }

});


//Actualizar

document.getElementById("updateTruckForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateTruckId").value;
    const model = document.getElementById("updateTruckModel").value;
    const plate_number = document.getElementById("updateTruckPlate").value;
    const capacity = document.getElementById("updateTruckCapacity").value;

    try {
        const response = await fetch(`${urlTrucks}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                model,
                plate_number,
                capacity,
                status
            })
        });

        if (response.ok) {
            alert("Camion actualizada exitosamente.");
            document.getElementById("updateModal1").style.display = "none";
            // Opcional: recargar la lista
            document.getElementById("searchBtn1").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar camion: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar camion:", error);
        alert("Error en la solicitud: " + error);
    }
});




//filtrar
document.getElementById("searchBtn1").addEventListener("click", async () => {
    const modelo = document.getElementById("searchTruck").value.trim();
    await buscarCamionesPorModelo(modelo);
});

async function buscarCamionesPorModelo(modelo) {
    const url = new URL(`${urlTrucks}filter`);
    if (modelo !== "") {
        url.searchParams.append("model", modelo);
    }

    try {
        const response = await fetch(url);
        const camiones = await response.json();

        const contenedor = document.getElementById("truckItems");
        contenedor.innerHTML = "";

        if (camiones.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron camiones.</li>";
            return;
        }

        camiones.forEach(camion => {
            const item = document.createElement("li");
            item.classList.add("truck-item");

           item.innerHTML = `
    <span>
        <strong>${camion.model}</strong> - ${camion.plate_number} - $${camion.capacity}kg
    </span>
    <div class="actions">
        <button class="update-btn update-truck-btn" 
                data-id="${camion.truck_id}"
                data-model="${camion.model}"
                data-plate="${camion.plate_number}"
                data-capacity="${camion.capacity}">
            Actualizar
        </button>
        <button class="delete-btn delete-truck-btn" data-id="${camion.truck_id}">Eliminar</button>
    </div>
`;

            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error al buscar camiones:", error);
        alert("Hubo un error al buscar camiones.");
    }
}

// Eliminar camion
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-truck-btn")) {
        const truckId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este camión?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlTrucks}${truckId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Camión eliminado correctamente.");
                document.getElementById("searchBtn1").click(); // Refrescar lista
            } else {
                const errorMsg = await response.text();
                alert("Error al eliminar el camión: " + errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar camion:", error);
            alert("Hubo un error al eliminar el camión.");
        }
    }
});





