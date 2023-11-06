package com.example.demo.services;

import com.example.demo.model.Auto;
import com.example.demo.model.Chofer;
import com.example.demo.model.Viaje;
import com.example.demo.repository.AutoRepository;
import com.example.demo.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@Service
public class AutoService {
    @Autowired
    public AutoRepository ar;

    @Autowired
    public ViajeRepository vr;

    public AutoService(AutoRepository ar){
        this.ar = ar;
    }

    public List<Auto> getAll(){
        try {
            return ar.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity add(Auto a){
        try {
            ar.save(a);
            return ResponseEntity.status(CREATED).body(a);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity update(Integer id, Auto a){
        try {
            Auto au = ar.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Auto no encontrado!!"));
            au.setMatricula(a.getMatricula());
            au.setModelo(a.getModelo());
            au.setChofer(a.getChofer());
            ar.save(au);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer id){
        try {
            ar.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity setViaje(Integer id, Viaje viaje){
        try {
            Auto auto = ar.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Propietario no encontrado!!"));
            viaje.setAuto(auto);
            vr.save(viaje);
            auto.getViajes().add(viaje);
            ar.save(auto);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
