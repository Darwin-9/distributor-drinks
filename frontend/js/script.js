function ocultarTodosLosFormularios() {
    document.getElementById("drinkFormContainer").style.display = "none";
    document.getElementById("truckFormContainer").style.display = "none";
    document.getElementById("adminFormContainer").style.display = "none";
    document.getElementById("storeFormContainer").style.display = "none";
    document.getElementById("storeUserFormContainer").style.display = "none";
    document.getElementById("driverFormContainer").style.display = "none";
    document.getElementById("orderFormContainer").style.display = "none";
    document.getElementById("deliveryFormContainer").style.display = "none";

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


document.querySelector("button:nth-child(2)").addEventListener("click", () => {
  const formContainer = document.getElementById("driverFormContainer");
  const isVisible = formContainer.style.display === "block";

  ocultarTodosLosFormularios();
  formContainer.style.display = isVisible ? "none" : "block";
});

document.querySelector("button:nth-child(7)").addEventListener("click", () => {
  const formContainer = document.getElementById("orderFormContainer");
  const isVisible = formContainer.style.display === "block";

  ocultarTodosLosFormularios();
  formContainer.style.display = isVisible ? "none" : "block";
});


document.querySelector("button:nth-child(8)").addEventListener("click", () => {
  const formContainer = document.getElementById("deliveryFormContainer");
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
      
      // Seleccionar el camión correcto en el select
      const truckId = btn.dataset.truckId;
      const truckSelect = document.getElementById("updateDriverTruckId");
      truckSelect.value = truckId;

      // Agregar control para el estado
      const statusCheckbox = document.getElementById("updateDriverStatus");
      if (statusCheckbox) {
          statusCheckbox.checked = btn.dataset.status === "true";
      }

      document.getElementById("updateDriverModal").style.display = "block";
  }
});

document.addEventListener('click', function(e) {
  if (e.target.classList.contains('update-order-btn')) {
      const btn = e.target;
      const orderId = btn.getAttribute('data-id');
      
      document.getElementById('updateOrderId').value = orderId;
      document.getElementById('updateOrderModal').dataset.currentId = orderId;

      document.getElementById("updateOrderDate").value = btn.dataset.date;
      document.getElementById("updateOrderStoreId").value = btn.dataset.storeId;

      document.getElementById("updateOrderModal").style.display = "block";
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

document.addEventListener("DOMContentLoaded", () => {
  const closeDriverBtn = document.getElementById("closeDriverModal");
  const driverModal = document.getElementById("updateDriverModal");

  if (closeDriverBtn && driverModal) {
    closeDriverBtn.addEventListener("click", () => {
      driverModal.style.display = "none";
    });
  }
});

document.addEventListener("DOMContentLoaded", () => {
  const closeOrderBtn = document.getElementById("closeOrderModal");
  const orderModal = document.getElementById("updateOrderModal");

  if (closeOrderBtn && orderModal) {
      closeOrderBtn.addEventListener("click", () => {
          orderModal.style.display = "none";
      });
  }
});


// Cerrar modal de entrega
document.addEventListener("DOMContentLoaded", () => {
  const closeDeliveryBtn = document.getElementById("closeDeliveryModal");
  const deliveryModal = document.getElementById("updateDeliveryModal");

  if (closeDeliveryBtn && deliveryModal) {
      closeDeliveryBtn.addEventListener("click", () => {
          deliveryModal.style.display = "none";
      });
  }
});


  });
  
