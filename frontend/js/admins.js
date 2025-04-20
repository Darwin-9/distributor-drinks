import { urlBase } from './constante.js';

let urlAdmins = `${urlBase}admins/`;

console.log(urlAdmins);

// Registrar
document.getElementById("adminForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const ususario = document.getElementById("adminName").value;
    const gmail = document.getElementById("adminEmail").value;
    const contrasena = document.getElementById("adminPassword").value;

    const bodyContent = JSON.stringify({
        "username": usuario,
        "email": gmail,
        "password": contrasena
    });

    try {
        const response = await fetch(urlAdmins, {
            method: 'POST',
            headers: {
                "Accept": "*/*",
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        let data = await response.text();

        if (response.ok) {
            document.getElementById("adminForm").reset();
            alert("Administrador registrado exitosamente.");
            buscarAdmins(""); // Refresca la lista
        } else {
            alert("Error al registrar admin: " + data);
        }

        console.log(data);
    } catch (error) {
        console.error("Error al registrar admin:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Actualizar
document.getElementById("updateAdminForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const id = document.getElementById("updateAdminId").value;
    const username = document.getElementById("updateAdminName").value;
    const email = document.getElementById("updateAdminEmail").value;
    const password = document.getElementById("updateAdminPassword").value;

    try {
        const response = await fetch(`${urlAdmins}${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                email,
                password
            })
        });

        if (response.ok) {
            alert("Administrador actualizado exitosamente.");
            document.getElementById("updateAdminModal").style.display = "none";
            document.getElementById("searchBtn2").click();
        } else {
            const errorMsg = await response.text();
            alert("Error al actualizar admin: " + errorMsg);
        }
    } catch (error) {
        console.error("Error al actualizar admin:", error);
        alert("Error en la solicitud: " + error);
    }
});

// Buscar
document.getElementById("searchBtn2").addEventListener("click", async () => {
    const usuario = document.getElementById("searchAdmin").value.trim();
    await buscarAdmins(usuario);
});

async function buscarAdmins(usuario) {
    const url = new URL(`${urlAdmins}filter`);
    if (usuario !== "") {
        url.searchParams.append("username", usuario);
    }

    try {
        const response = await fetch(url);
        const admins = await response.json();

        const contenedor = document.getElementById("adminItems");
        contenedor.innerHTML = "";

        if (admins.length === 0) {
            contenedor.innerHTML = "<li>No se encontraron administradores.</li>";
            return;
        }

        admins.forEach(admin => {
            const item = document.createElement("li");
            item.classList.add("admin-item");

            item.innerHTML = `
                <span>
                    <strong>${admin.username}</strong> - ${admin.email}
                </span>
                <div class="actions">
                    <button class="update-btn update-admin-btn"
                            data-id="${admin.admin_id}"
                            data-name="${admin.username}"
                            data-email="${admin.email}"
                            data-password="${admin.password}">
                        Actualizar
                    </button>
                    <button class="delete-btn delete-admin-btn" data-id="${admin.admin_id}">Eliminar</button>
                </div>
            `;

            contenedor.appendChild(item);
        });
    } catch (error) {
        console.error("Error al buscar admins:", error);
        alert("Hubo un error al buscar administradores.");
    }
}

// Eliminar
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("delete-admin-btn")) {
        const adminId = e.target.dataset.id;

        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este administrador?");
        if (!confirmDelete) return;

        try {
            const response = await fetch(`${urlAdmins}${adminId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Administrador eliminado correctamente.");
                document.getElementById("searchBtn2").click();
            } else {
                const errorMsg = await response.text();
                alert("Error al eliminar administrador: " + errorMsg);
            }
        } catch (error) {
            console.error("Error al eliminar admin:", error);
            alert("Hubo un error al eliminar el administrador.");
        }
    }
});
