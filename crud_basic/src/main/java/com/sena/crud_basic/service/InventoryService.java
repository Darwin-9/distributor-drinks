package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.InventoryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Inventory;
import com.sena.crud_basic.repository.IInventory;

@Service
public class InventoryService {

    @Autowired
    private IInventory data;

    // Método para guardar un registro de inventario con validaciones
    public responseDTO save(InventoryDTO inventoryDTO) {
        if (inventoryDTO.getCurrent_stock() < 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El stock actual no puede ser negativo");
        }

        Inventory inventory = convertToModel(inventoryDTO);
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
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Registro de inventario eliminado correctamente");
    }

    // Método para convertir un modelo a un DTO
    public InventoryDTO convertToDTO(Inventory inventory) {
        return new InventoryDTO(inventory.getCurrent_stock(), inventory.getLast_update());
    }

    // Método para convertir un DTO a un modelo
    public Inventory convertToModel(InventoryDTO inventoryDTO) {
        return new Inventory(0, null, inventoryDTO.getCurrent_stock(), inventoryDTO.getLast_update());
    }
}
