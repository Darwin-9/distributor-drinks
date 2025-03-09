package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.DistributorDTO;
import com.sena.crud_basic.model.Distributor;
import com.sena.crud_basic.repository.IDistributor;
import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {

    @Autowired
    private IDistributor data;

    public void save(DistributorDTO distributorDTO) {
        Distributor distributor = convertToModel(distributorDTO);
        data.save(distributor);
    }

    public List<Distributor> findAll() {
        return data.findAll();
    }

    public Optional<Distributor> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public DistributorDTO convertToDTO(Distributor distributor) {
        return new DistributorDTO(
                distributor.getDistributor_name(),
                distributor.getAddress(),
                distributor.getPhone_number());
    }

    public Distributor convertToModel(DistributorDTO distributorDTO) {
        return new Distributor(
                0,
                distributorDTO.getDistributor_name(),
                distributorDTO.getAddress(),
                distributorDTO.getPhone_number());
    }
}
