package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.DrinkDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Drink;
import com.sena.crud_basic.repository.IDrink;

@Service
public class DrinkService {

    @Autowired
    private IDrink data;

    // Método para guardar una bebida con validaciones
    public responseDTO save(DrinkDTO drinkDTO) {
        // Validación del nombre
        if (drinkDTO.getName() == null || drinkDTO.getName().trim().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre no puede estar vacío");
        }
        if (drinkDTO.getName().length() < 1 || drinkDTO.getName().length() > 100) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 1 y 100 caracteres");
        }

        // Validación del precio
        if (drinkDTO.getPrice() <=0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El precio debe ser mayor a 0");
        }

        // Validación del volumen
        if (drinkDTO.getVolume() <=0 || drinkDTO.getVolume() > 10000) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El volumen debe estar entre 0.1 ml y 10,000 ml");
        }

        // Validación del stock
        if (drinkDTO.getStock() <=0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El stock no puede ser negativo");
        }

        // Guardar la bebida si todas las validaciones pasan
        Drink drink = convertToModel(drinkDTO);
        data.save(drink);
        return new responseDTO(HttpStatus.OK.toString(), "Bebida guardada exitosamente");
    }

    // Método para obtener todas las bebidas
    public List<Drink> findAll() {
        return data.findAll();
    }

    // Método para buscar una bebida por ID
    public Optional<Drink> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar una bebida por ID
    public responseDTO deleteUser(int id) {
        Optional<Drink> drink = findById(id);
        if (!drink.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La bebida no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Bebida eliminada correctamente");
    }

    // Método para convertir un modelo a un DTO
    public DrinkDTO convertToDTO(Drink drink) {
        return new DrinkDTO(drink.getName(), drink.getPrice(), drink.getVolume(), drink.getStock());
    }

    // Método para convertir un DTO a un modelo
    public Drink convertToModel(DrinkDTO drinkDTO) {
        return new Drink(0, drinkDTO.getName(), drinkDTO.getPrice(), drinkDTO.getVolume(), drinkDTO.getStock());
    }

    public responseDTO update(DrinkDTO drinkDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public responseDTO deleteDrink(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDrink'");
    }
}
