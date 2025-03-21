package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.StoreDTO;
import com.sena.crud_basic.model.Store;
import com.sena.crud_basic.repository.IStore;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private IStore data;

    public void save(StoreDTO storeDTO) {
        Store store = convertToModel(storeDTO);
        data.save(store);
    }

    public List<Store> findAll() {
        return data.findAll();
    }

    public Optional<Store> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public StoreDTO convertToDTO(Store store) {
        return new StoreDTO(
                store.getStore_name(),
                store.getAddress(),
                store.getPhone_number());
    }

    public Store convertToModel(StoreDTO storeDTO) {
        return new Store(
                0,
                storeDTO.getStore_name(),
                storeDTO.getAddress(),
                storeDTO.getPhone_number(), null);
    }
}
