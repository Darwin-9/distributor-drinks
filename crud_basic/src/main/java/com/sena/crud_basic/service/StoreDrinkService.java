package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.StoreDrinkDTO;
import com.sena.crud_basic.model.StoreDrink;
import com.sena.crud_basic.repository.IStoreDrink;
import java.util.List;
import java.util.Optional;

@Service
public class StoreDrinkService {

    @Autowired
    private IStoreDrink data;

    public void save(StoreDrinkDTO storeDrinkDTO) {
        StoreDrink storeDrink = convertToModel(storeDrinkDTO);
        data.save(storeDrink);
    }

    public List<StoreDrink> findAll() {
        return data.findAll();
    }

    public Optional<StoreDrink> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public StoreDrinkDTO convertToDTO(StoreDrink storeDrink) {
        return new StoreDrinkDTO(
                storeDrink.getStore_id(),
                storeDrink.getDrink_id(),
                storeDrink.getStock());
    }

    public StoreDrink convertToModel(StoreDrinkDTO storeDrinkDTO) {
        return new StoreDrink(
                0,
                storeDrinkDTO.getStore_id(),
                storeDrinkDTO.getDrink_id(),
                storeDrinkDTO.getStock());
    }
}
