package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.DriverDTO;
import com.sena.crud_basic.model.Driver;
import com.sena.crud_basic.repository.IDriver;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private IDriver data;

    public void save(DriverDTO driverDTO) {
        Driver driver = convertToModel(driverDTO);
        data.save(driver);
    }

    public List<Driver> findAll() {
        return data.findAll();
    }

    public Optional<Driver> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public DriverDTO convertToDTO(Driver driver) {
        return new DriverDTO(
                driver.getDriver_first_name(),
                driver.getDriver_last_name(),
                driver.getLicense_number());
    }

    public Driver convertToModel(DriverDTO driverDTO) {
        return new Driver(
                0,
                driverDTO.getDriver_first_name(),
                driverDTO.getDriver_last_name(),
                driverDTO.getLicense_number());
    }
}
