package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.DistributorDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Distributor;
import com.sena.crud_basic.repository.IDistributor;

@Service
public class DistributorService {

    @Autowired
    private IDistributor data;

    public List<Distributor> findAll() {
        return data.findAll();
    }

    public Optional<Distributor> findById(int id) {
        return data.findById(id);
    }

     public responseDTO deleteDistributor(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.OK.toString(),
                    "The register does not exist");
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "Se eliminó correctamente");
        return respuesta;
    }


     // register and update
     public responseDTO save(DistributorDTO distributorDTO) {
        // validación longitud del nombre
        if (distributorDTO.getDistributor_name().length() < 1 ||
                distributorDTO.getDistributor_name().length() > 50) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El nombre debe estar entre 1 y 50 caracteres");
            return respuesta;
        }
        // validación longitud del address
        if (distributorDTO.getAddress().length() < 7 ||
        distributorDTO.getAddress().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La direccion deberia de estar entre 7 y 100 caracteres");
                return respuesta;
                }
                // validación longitud del phone
                if (distributorDTO.getPhone_number().length() < 7 ||
                distributorDTO.getPhone_number().length() > 20) {
                    responseDTO respuesta = new responseDTO(
                        HttpStatus.BAD_REQUEST.toString(),
                        "El telefono deberia de estar entre 7 y 20 caracteres");
                        return respuesta;
                        }

        // otras condiciones
        // n
        Distributor distributorRegister = convertToModel(distributorDTO);
        data.save(distributorRegister);
        responseDTO respuesta = new responseDTO( 
                HttpStatus.OK.toString(),
                "Se guardó correctamente");
        return respuesta;
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