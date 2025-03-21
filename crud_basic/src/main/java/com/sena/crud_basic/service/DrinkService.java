package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.DrinkDTO;
import com.sena.crud_basic.model.Drink;
import com.sena.crud_basic.repository.IDrink;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    @Autowired
    private IDrink data;

    public void save(DrinkDTO drinkDTO) {
        Drink drink = convertToModel(drinkDTO);
        data.save(drink);
    }

    public List<Drink> findAll() {
        return data.findAll();
    }

    public Optional<Drink> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public DrinkDTO convertToDTO(Drink drink) {
        return new DrinkDTO(
                drink.getDrink_name(),
                drink.getVolume(),
                drink.getPrice(),
                drink.getTrademark());
    }

    public Drink convertToModel(DrinkDTO drinkDTO) {
        return new Drink(
                0,
                drinkDTO.getDrink_name(),
                drinkDTO.getVolume(),
                drinkDTO.getPrice(),
                drinkDTO.getTrademark());
    }
}
