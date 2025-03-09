package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.TruckDrinkDTO;
import com.sena.crud_basic.model.TruckDrink;
import com.sena.crud_basic.repository.ITruckDrink;
import java.util.List;
import java.util.Optional;

@Service
public class TruckDrinkService {

    @Autowired
    private ITruckDrink data;

    public void save(TruckDrinkDTO truckDrinkDTO) {
        TruckDrink truckDrink = convertToModel(truckDrinkDTO);
        data.save(truckDrink);
    }

    public List<TruckDrink> findAll() {
        return data.findAll();
    }

    public Optional<TruckDrink> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public TruckDrinkDTO convertToDTO(TruckDrink truckDrink) {
        return new TruckDrinkDTO(
                truckDrink.getTruck_id(),
                truckDrink.getDrink_id(),
                truckDrink.getStock());
    }

    public TruckDrink convertToModel(TruckDrinkDTO truckDrinkDTO) {
        return new TruckDrink(
                0,
                truckDrinkDTO.getTruck_id(),
                truckDrinkDTO.getDrink_id(),
                truckDrinkDTO.getStock());
    }
}
