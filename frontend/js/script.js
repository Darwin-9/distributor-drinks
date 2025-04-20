function ocultarTodosLosFormularios() {
    document.getElementById("drinkFormContainer").style.display = "none";
    document.getElementById("truckFormContainer").style.display = "none";
    document.getElementById("adminFormContainer").style.display = "none";
    document.getElementById("storeFormContainer").style.display = "none";
    document.getElementById("storeUserFormContainer").style.display = "none";

}

document.getElementById("toggleDrinkForm").addEventListener("click", () => {
    const formContainer = document.getElementById("drinkFormContainer");
    const isVisible = formContainer.style.display === "block";

    ocultarTodosLosFormularios();
    formContainer.style.display = isVisible ? "none" : "block";
});

document.getElementById("toggleTruckForm").addEventListener("click", () => {
    const formContainer = document.getElementById("truckFormContainer");
    const isVisible = formContainer.style.display === "block";

    ocultarTodosLosFormularios();
    formContainer.style.display = isVisible ? "none" : "block";
});

document.getElementById("toggleAdminForm").addEventListener("click", () => {
    const formContainer = document.getElementById("adminFormContainer");
    const isVisible = formContainer.style.display === "block";

    ocultarTodosLosFormularios();
    formContainer.style.display = isVisible ? "none" : "block";
});

document.querySelector("button:nth-child(4)").addEventListener("click", () => {
    const formContainer = document.getElementById("storeFormContainer");
    const isVisible = formContainer.style.display === "block";

    ocultarTodosLosFormularios();
    formContainer.style.display = isVisible ? "none" : "block";
});

document.querySelector("button:nth-child(5)").addEventListener("click", () => {
    const formContainer = document.getElementById("storeUserFormContainer");
    const isVisible = formContainer.style.display === "block";

    ocultarTodosLosFormularios();
    formContainer.style.display = isVisible ? "none" : "block";
});


// Abrir modal
// Abrir modal con los datos de la bebida
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-drink-btn')) {
        const btn = e.target;
        const drinkId = btn.getAttribute('data-id');
        
        document.getElementById('updateDrinkId').value = drinkId;
        document.getElementById('updateModal').dataset.currentId = drinkId;

        document.getElementById("updateDrinkName").value = btn.dataset.name;
        document.getElementById("updateDrinkVolume").value = btn.dataset.volume;
        document.getElementById("updateDrinkPrice").value = btn.dataset.price;
        document.getElementById("updateDrinkStock").value = btn.dataset.stock;

        document.getElementById("updateModal").style.display = "block";
    }
});


document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-truck-btn')) {
        const btn = e.target;
        const truckId = btn.getAttribute('data-id');
        
        document.getElementById('updateTruckId').value = truckId;
        document.getElementById('updateModal1').dataset.currentId = truckId;

        document.getElementById("updateTruckModel").value = btn.dataset.model;
        document.getElementById("updateTruckPlate").value = btn.dataset.plate;
        document.getElementById("updateTruckCapacity").value = btn.dataset.capacity;
      

        document.getElementById("updateModal1").style.display = "block";
    }
});

document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-admin-btn')) {
        const btn = e.target;
        const adminId = btn.getAttribute('data-id');

        document.getElementById('updateAdminId').value = adminId;
        document.getElementById('updateAdminModal').dataset.currentId = adminId;

        document.getElementById("updateAdminName").value = btn.dataset.name;
        document.getElementById("updateAdminEmail").value = btn.dataset.email;
        document.getElementById("updateAdminPassword").value = btn.dataset.password;

        document.getElementById("updateAdminModal").style.display = "block";
    }
});

document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-store-btn')) {
        const btn = e.target;
        const storeId = btn.getAttribute('data-id');
        
        document.getElementById('updateStoreId').value = storeId;
        document.getElementById('updateStoreModal').dataset.currentId = storeId;

        document.getElementById("updateStoreName").value = btn.dataset.name;
        document.getElementById("updateStoreAddress").value = btn.dataset.address;
        document.getElementById("updateStorePhone").value = btn.dataset.phone;
        document.getElementById("updateStoreCity").value = btn.dataset.city;

        document.getElementById("updateStoreModal").style.display = "block";
    }
});

document.addEventListener('click', function(e) {
    if (e.target.classList.contains('update-store-user-btn')) {
        const btn = e.target;
        const userId = btn.getAttribute('data-id');
        
        document.getElementById('updateStoreUserId').value = userId;
        document.getElementById('updateStoreUserModal').dataset.currentId = userId;

        document.getElementById("updateStoreUserName").value = btn.dataset.username;
        document.getElementById("updateStoreUserEmail").value = btn.dataset.email;

        document.getElementById("updateStoreUserModal").style.display = "block";
    }
});



document.addEventListener("DOMContentLoaded", () => {
    // Cerrar modal de bebida
    const closeDrinkBtn = document.getElementById("closeDrinkModal");
    const drinkModal = document.getElementById("updateModal");
  
    if (closeDrinkBtn && drinkModal) {
      closeDrinkBtn.addEventListener("click", () => {
        drinkModal.style.display = "none";
      });
    }
  
    // Cerrar modal de camión
    const closeTruckBtn = document.getElementById("closeTruckModal");
    const truckModal = document.getElementById("updateModal1");
  
    if (closeTruckBtn && truckModal) {
      closeTruckBtn.addEventListener("click", () => {
        truckModal.style.display = "none";
      });
    }

     // Cerrar modal de camión
     const closeAdminBtn = document.getElementById("closeAdminModal");
     const adminModal = document.getElementById("updateAdminModal");
   
     if (closeAdminBtn && adminModal) {
       closeTruckBtn.addEventListener("click", () => {
         truckModal.style.display = "none";
       });
     }

     // Agrega el cierre del modal de tiendas
document.addEventListener("DOMContentLoaded", () => {
    // Cerrar modal de tienda
    const closeStoreBtn = document.getElementById("closeStoreModal");
    const storeModal = document.getElementById("updateStoreModal");
  
    if (closeStoreBtn && storeModal) {
      closeStoreBtn.addEventListener("click", () => {
        storeModal.style.display = "none";
      });
    }
});

document.addEventListener("DOMContentLoaded", () => {
    // Cerrar modal de usuario de tienda
    const closeStoreUserBtn = document.getElementById("closeStoreUserModal");
    const storeUserModal = document.getElementById("updateStoreUserModal");
  
    if (closeStoreUserBtn && storeUserModal) {
      closeStoreUserBtn.addEventListener("click", () => {
        storeUserModal.style.display = "none";
      });
    }
});


  });
  
