package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.InventoryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Drink;
import com.sena.crud_basic.model.Inventory;
import com.sena.crud_basic.repository.IDrink;
import com.sena.crud_basic.repository.IInventory;

@Service
public class InventoryService {

    @Autowired
    private IDrink drinkRepository;

    @Autowired
    private IInventory data;

    // Método para guardar un registro de inventario con validaciones
      public responseDTO save(InventoryDTO inventoryDTO) {
        // Validaciones como en DriverService
        if (inventoryDTO.getCurrent_stock() < 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El stock actual no puede ser negativo");
        }

        // Verificar que el drink exista
        Drink drink = drinkRepository.findById(inventoryDTO.getDrink_id())
            .orElseThrow(() -> new RuntimeException("Bebida no encontrada"));

        Inventory inventory = convertToModel(inventoryDTO);
        inventory.setDrink(drink); // Establecer la relación
        data.save(inventory);

        return new responseDTO(HttpStatus.OK.toString(), "Inventario guardado exitosamente");
    }


    // Método para obtener todos los registros de inventario
    public List<Inventory> findAll() {
        return data.findAll();
    }

    // Método para buscar un registro de inventario por ID
    public Optional<Inventory> findById(int id) {
        return data.findById(id);
    }
    

    // Método para eliminar un registro de inventario por ID
   public responseDTO deleteUser(int id) {
        Optional<Inventory> inventory = findById(id);
        if (!inventory.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El registro de inventario no existe");
        }
        
        // Eliminación lógica
        inventory.get().setStatus(false);
        data.save(inventory.get());
        
        return new responseDTO(HttpStatus.OK.toString(), "Registro de inventario desactivado correctamente");
    }



    // Actualizar inventario
    public responseDTO update(int id, InventoryDTO dto) {
        Optional<Inventory> inventoryOpt = data.findById(id);
        if (!inventoryOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El inventario con ID " + id + " no existe");
        }

        if (dto.getCurrent_stock() < 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El stock no puede ser negativo");
        }

        Inventory inventory = inventoryOpt.get();
        inventory.setCurrent_stock(dto.getCurrent_stock());
        inventory.setLast_update(dto.getLast_update());

        data.save(inventory);
        return new responseDTO(HttpStatus.OK.toString(), "Inventario actualizado correctamente");
    }


     // Filtro por fecha y stock
     public List<Inventory> filterInventory(String last_update, Integer current_stock) {
        return data.filterInventory(last_update, current_stock);
    }


    // Método para convertir un modelo a un DTO
    public InventoryDTO convertToDTO(Inventory inventory) {
        return new InventoryDTO(
            inventory.getDrink().getDrink_id(), // Asumiendo que Drink tiene getId()
            inventory.getCurrent_stock(),
            inventory.getLast_update(),
            inventory.isStatus()
        );
    }

    // Método para convertir un DTO a un modelo
    public Inventory convertToModel(InventoryDTO inventoryDTO) {
        return new Inventory(
            0, 
            null, // drink se establece aparte
            inventoryDTO.getCurrent_stock(), 
            inventoryDTO.getLast_update(),
            inventoryDTO.getStatus()
        );
    }
}
