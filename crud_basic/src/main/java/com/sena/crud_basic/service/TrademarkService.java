package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.TrademarkDTO;
import com.sena.crud_basic.model.Trademark;
import com.sena.crud_basic.repository.ITrademark;
import java.util.List;
import java.util.Optional;

@Service
public class TrademarkService {

    @Autowired
    private ITrademark data;

    public void save(TrademarkDTO trademarkDTO) {
        Trademark trademark = convertToModel(trademarkDTO);
        data.save(trademark);
    }

    public List<Trademark> findAll() {
        return data.findAll();
    }

    public Optional<Trademark> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public TrademarkDTO convertToDTO(Trademark trademark) {
        return new TrademarkDTO(
                trademark.getTrademark_name());
    }

    public Trademark convertToModel(TrademarkDTO trademarkDTO) {
        return new Trademark(
                0,
                trademarkDTO.getTrademark_name());
    }
}
