package com.example.demo.services;

import com.example.demo.model.Auto;
import com.example.demo.model.Viaje;
import com.example.demo.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class ViajeService {
    @Autowired
    public ViajeRepository vr;

    public ViajeService(ViajeRepository vr){
        this.vr = vr;
    }

    public List<Viaje> getAll(){
        return vr.findAll();
    }

    public ResponseEntity add(Viaje v){
        try {
            vr.save(v);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity update(Integer id, Viaje v){
        try {
            Viaje vi = vr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Auto no encontrado!!"));
            vi.setOrigen(v.getOrigen());
            vi.setDestino(v.getDestino());
            vi.setCosto(v.getCosto());
            vi.setAuto(v.getAuto());
            vr.save(vi);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer id){
        vr.deleteById(id);
        return ResponseEntity.status(OK).build();
    }
}