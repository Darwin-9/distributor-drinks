import { urlBase } from './constante.js';

let urlDrivers = `${urlBase}drivers/`;
let urlTrucks = `${urlBase}trucks/`;

// Función para cargar los camiones en los select
async function cargarCamiones() {
    try {
        const response = await fetch(urlTrucks);
        const trucks = await response.json();
        
        const selectCreacion = document.getElementById("driverTruckId");
        const selectActualizacion = document.getElementById("updateDriverTruckId");
        
        // Limpiar selects
        selectCreacion.innerHTML = '<option value="">Seleccione un camión</option>';
        selectActualizacion.innerHTML = '<option value="">Seleccione un camión</option>';
        
        // Llenar con los camiones disponibles
        trucks.forEach(truck => {
            if(truck.status) { // Solo camiones activos
                const option = document.createElement("option");
                option.value = truck.truck_id;
                option.textContent = `${truck.model} (${truck.plate_number})`;
                
                selectCreacion.appendChild(option.cloneNode(true));
                selectActualizacion.appendChild(option.cloneNode(true));
            }
        });
    } catch (error) {
        console.error("Error al cargar camiones:", error);
    }
}


// Llamar a cargarCamiones al cargar la página
document.addEventListener("DOMContentLoaded", cargarCamiones);

// Registrar conductor
document.getElementById("driverForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const firstName = document.getElementById("driverFirstName").value;
    const lastName = document.getElementById("driverLastName").value;
    const license = document.getElementById("driverLicense").value;
    const truckId = document.getElementById("driverTruckId").value;

    if (!truckId) {
        alert("Por favor seleccione un camión");
        return;
    }

    const bodyContent = JSON.stringify({
        "first_name": firstName,
        "last_name": lastName,
        "license_number": license,
        "truck_id": parseInt(truckId)
    });

    try {
        const response = await fetch(urlDrivers, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("driverForm").reset();
            alert("Conductor registrado exitosamente.");
            buscarDrivers("", "", ""); // Refresca la lista
        } else {
            alert("Error al registrar conductor: " + data);
        }

        console.log(data);
    } catch (error) {
        console.error("Error al registrar conductor:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Actualizar conductor
document.getElementById("updateDriverForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateDriverId").value;
    const firstName = document.getElementById("updateDriverFirstName").value;
    const lastName = document.getElementById("updateDriverLastName").value;
    const license = document.getElementById("updateDriverLicense").value;
    const truckId = document.getElementById("updateDriverTruckId").value;

    // Validación básica
    if (!truckId) {
        alert("Por favor seleccione un camión");
        return;
    }

    try {
        const response = await fetch(`${urlDrivers}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                first_name: firstName,
                last_name: lastName,
                license_number: license,
                truck_id: parseInt(truckId) // Enviar el nuevo truck_id
            })
        });

        if (response.ok) {
            alert("Conductor actualizado exitosamente.");
            document.getElementById("updateDriverModal").style.display = "none";
            buscarDrivers("", "", ""); // Refrescar lista
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar conductor: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar conductor:", error);
        alert("Error en la solicitud: " + error);
    }
});


// Buscar conductores
document.getElementById("searchDriverBtn").addEventListener("click", async (e) => {
    e.preventDefault();
    const searchTerm = document.getElementById("searchDriver").value.trim();
    await buscarDrivers(searchTerm, "", "");
});


async function buscarDrivers(firstName, lastName, license) {
    try {
        const url = new URL(`${urlDrivers}filter`);
        if (firstName) url.searchParams.append("first_name", firstName);
        if (lastName) url.searchParams.append("last_name", lastName);
        if (license) url.searchParams.append("license_number", license);
        
        // Modificación para buscar inactivos cuando se especifique
        const searchTerm = document.getElementById("searchDriver").value.trim().toLowerCase();
        if (searchTerm === "inactivos") {
            url.searchParams.append("status", false); // Solo inactivos
        } else {
            url.searchParams.append("status", true); // Por defecto solo activos
        }

        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const drivers = await response.json();
        const contenedor = document.getElementById("driverItems");
        contenedor.innerHTML = "";

        if (drivers.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron conductores.</li>";
            return;
        }

        drivers.forEach(driver => {
            const truckInfo = driver.truck ? 
                `${driver.truck.model} (${driver.truck.plate_number})` : 
                "Sin camión asignado";
            
            const item = document.createElement("li");
            item.className = "driver-item";
            item.innerHTML = `
                <span>
                    <strong>${driver.first_name} ${driver.last_name}</strong><br>
                    Licencia: ${driver.license_number}<br>
                    Camión: ${truckInfo}<br>
                    Estado: ${driver.status ? 'Activo' : 'Inactivo'}
                </span>
                <div class="actions">
                    <button class="update-btn update-driver-btn"
                            data-id="${driver.driver_id}"
                            data-first-name="${driver.first_name}"
                            data-last-name="${driver.last_name}"
                            data-license="${driver.license_number}"
                            data-truck-id="${driver.truck ? driver.truck.truck_id : ''}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-driver-btn" 
                            data-id="${driver.driver_id}">
                        Eliminar
                    </button>
                </div>
            `;
            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error en buscarDrivers:", error);
        alert("Error al buscar conductores: " + error.message);
    }
}

// Manejar clic en botón actualizar
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-driver-btn')) {
        const btn = e.target;
        const driverId = btn.getAttribute('data-id');
        
        document.getElementById('updateDriverId').value = driverId;
        document.getElementById('updateDriverModal').dataset.currentId = driverId;

        document.getElementById("updateDriverFirstName").value = btn.dataset.firstName;
        document.getElementById("updateDriverLastName").value = btn.dataset.lastName;
        document.getElementById("updateDriverLicense").value = btn.dataset.license;
        
        // Seleccionar el camión actual en el select
        const truckId = btn.dataset.truckId;
        const truckSelect = document.getElementById("updateDriverTruckId");
        truckSelect.value = truckId;

        document.getElementById("updateDriverModal").style.display = "block";
    }
});



// Eliminar conductor
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-driver-btn")) {
        const driverId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este conductor?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlDrivers}${driverId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Conductor eliminado correctamente.");
                document.getElementById("searchDriverBtn").click(); // Refrescar lista
            } else {
                const errorMsg = await response.text();
                alert("Error al eliminar conductor: " + errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar conductor:", error);
            alert("Hubo un error al eliminar el conductor.");
        }
    }
});