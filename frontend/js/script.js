function ocultarTodosLosFormularios() {
    document.getElementById("drinkFormContainer").style.display = "none";
    document.getElementById("truckFormContainer").style.display = "none";
    document.getElementById("adminFormContainer").style.display = "none";
    document.getElementById("storeFormContainer").style.display = "none";
    document.getElementById("storeUserFormContainer").style.display = "none";
    document.getElementById("driverFormContainer").style.display = "none";
    document.getElementById("orderFormContainer").style.display = "none";
    document.getElementById("deliveryFormContainer").style.display = "none";
    document.getElementById("inventoryFormContainer").style.display = "none";
    document.getElementById("orderDrinkFormContainer").style.display = "none";

}

function removerClaseActive() {
  document.querySelectorAll('.admin-menu button').forEach(btn => {
      btn.classList.remove('active');
  });
}

function mostrarFormulario(idFormulario, titulo, descripcion) {
  ocultarTodosLosFormularios();
  removerClaseActive();
  
  document.getElementById(idFormulario).style.display = "block";
  document.getElementById('currentSectionTitle').textContent = titulo;
  document.getElementById('currentSectionDescription').textContent = descripcion;
  
  // Marcar el botón como activo
  document.querySelector(`button[onclick*="${idFormulario}"]`).classList.add('active');
}

// Configuración de event listeners para cada botón
document.getElementById("toggleDrinkForm").addEventListener("click", () => {
  mostrarFormulario("drinkFormContainer", "Gestión de Bebidas", "Administra el catálogo de bebidas disponibles");
});

document.getElementById("toggleTruckForm").addEventListener("click", () => {
  mostrarFormulario("truckFormContainer", "Gestión de Camiones", "Administra la flota de camiones");
});

document.getElementById("toggleAdminForm").addEventListener("click", () => {
  mostrarFormulario("adminFormContainer", "Gestión de Administradores", "Administra los usuarios administradores");
});

document.getElementById("toggleStoreForm").addEventListener("click", () => {
  mostrarFormulario("storeFormContainer", "Gestión de Tiendas", "Administra las tiendas registradas");
});

document.getElementById("toggleStoreUserForm").addEventListener("click", () => {
  mostrarFormulario("storeUserFormContainer", "Gestión de Usuarios de Tienda", "Administra los usuarios asociados a tiendas");
});

document.getElementById("toggleDriverForm").addEventListener("click", () => {
  mostrarFormulario("driverFormContainer", "Gestión de Conductores", "Administra los conductores y sus asignaciones");
});

document.getElementById("toggleOrderForm").addEventListener("click", () => {
  mostrarFormulario("orderFormContainer", "Gestión de Órdenes", "Administra las órdenes de pedido");
});

document.getElementById("toggleDeliveryForm").addEventListener("click", () => {
  mostrarFormulario("deliveryFormContainer", "Gestión de Entregas", "Administra los registros de entregas");
});

document.getElementById("toggleInventoryForm").addEventListener("click", () => {
  mostrarFormulario("inventoryFormContainer", "Gestión de Inventario", "Administra el inventario de productos");
});

document.getElementById("toggleOrderDrinkForm").addEventListener("click", () => {
  mostrarFormulario("orderDrinkFormContainer", "Gestión de Detalles de Pedido", "Administra los detalles de cada pedido");
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

document.addEventListener('click', function(e) {
  if (e.target.classList.contains('update-inventory-btn')) {
      const btn = e.target;
      const inventoryId = btn.getAttribute('data-id');
      
      document.getElementById('updateInventoryId').value = inventoryId;
      document.getElementById('updateInventoryModal').dataset.currentId = inventoryId;

      document.getElementById("updateInventoryStock").value = btn.dataset.stock;
      
      // Seleccionar la bebida correcta en el select
      const drinkId = btn.dataset.drinkId;
      const drinkSelect = document.getElementById("updateInventoryDrinkId");
      drinkSelect.value = drinkId;

      document.getElementById("updateInventoryModal").style.display = "block";
  }
});

document.addEventListener('click', function(e) {
  if (e.target.classList.contains('update-order-drink-btn')) {
      const btn = e.target;
      const orderDrinkId = btn.getAttribute('data-id');
      
      document.getElementById('updateOrderDrinkId').value = orderDrinkId;
      document.getElementById('updateOrderDrinkModal').dataset.currentId = orderDrinkId;

      document.getElementById("updateOrderDrinkQuantity").value = btn.dataset.quantity;
      document.getElementById("updateOrderDrinkModal").style.display = "block";
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
  });
  
    // Cerrar modal de camión
    document.addEventListener("DOMContentLoaded", () => {
    const closeTruckBtn = document.getElementById("closeTruckModal");
    const truckModal = document.getElementById("updateModal1");
  
    if (closeTruckBtn && truckModal) {
      closeTruckBtn.addEventListener("click", () => {
        truckModal.style.display = "none";
      });
    }
  });

     // Cerrar modal de admin
     document.addEventListener("DOMContentLoaded", () => {
     const closeAdminBtn = document.getElementById("closeAdminModal");
     const adminModal = document.getElementById("updateAdminModal");
   
     if (closeAdminBtn && adminModal) {
       closeAdminBtn.addEventListener("click", () => {
         adminModal.style.display = "none";
       });
     }
    });

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

document.addEventListener("DOMContentLoaded", () => {
  const closeInventoryBtn = document.getElementById("closeInventoryModal");
  const inventoryModal = document.getElementById("updateInventoryModal");

  if (closeInventoryBtn && inventoryModal) {
      closeInventoryBtn.addEventListener("click", () => {
          inventoryModal.style.display = "none";
      });
  }
});

document.addEventListener("DOMContentLoaded", () => {
  const closeOrderDrinkBtn = document.getElementById("closeOrderDrinkModal");
  const orderDrinkModal = document.getElementById("updateOrderDrinkModal");

  if (closeOrderDrinkBtn && orderDrinkModal) {
      closeOrderDrinkBtn.addEventListener("click", () => {
          orderDrinkModal.style.display = "none";
      });
  }
});

  

// Menú hamburguesa del panel de administración
document.querySelector('.admin-menu-toggle').addEventListener('click', function() {
  this.classList.toggle('active');
  document.querySelector('.admin-menu').classList.toggle('active');
});
