package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.TruckDTO;
import com.sena.crud_basic.model.Truck;
import com.sena.crud_basic.repository.ITruck;
import java.util.List;
import java.util.Optional;

@Service
public class TruckService {

    @Autowired
    private ITruck data;

    public void save(TruckDTO truckDTO) {
        Truck truck = convertToModel(truckDTO);
        data.save(truck);
    }

    public List<Truck> findAll() {
        return data.findAll();
    }

    public Optional<Truck> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public TruckDTO convertToDTO(Truck truck) {
        return new TruckDTO(
                truck.getPlate_number(),
                truck.getModel(),
                truck.getCapacity());
    }

    public Truck convertToModel(TruckDTO truckDTO) {
        return new Truck(
                0,
                truckDTO.getPlate_number(),
                truckDTO.getModel(),
                truckDTO.getCapacity());
    }
}
