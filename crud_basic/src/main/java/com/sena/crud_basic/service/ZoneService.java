package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.ZoneDTO;
import com.sena.crud_basic.model.Zone;
import com.sena.crud_basic.repository.IZone;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {

    @Autowired
    private IZone data;

    public void save(ZoneDTO zoneDTO) {
        Zone zone = convertToModel(zoneDTO);
        data.save(zone);
    }

    public List<Zone> findAll() {
        return data.findAll();
    }

    public Optional<Zone> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public ZoneDTO convertToDTO(Zone zone) {
        return new ZoneDTO(
                zone.getZone_name(),
                zone.getCity());
    }

    public Zone convertToModel(ZoneDTO zoneDTO) {
        return new Zone(
                0,
                zoneDTO.getZone_name(),
                zoneDTO.getCity());
    }
}
