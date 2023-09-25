package com.example.demo.services;

import com.example.demo.model.Chofer;
import com.example.demo.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@Service
public class ChoferService {
    @Autowired
    public ChoferRepository cr;

    public ChoferService(ChoferRepository cr){
        this.cr = cr;
    }

    public List<Chofer> getAll(){
        return cr.findAll();
    }

    public ResponseEntity add(Chofer c){
        try {
            cr.save(c);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity update(Integer id, Chofer c){
        try {
            Chofer chofer = cr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Propietario no encontrado!!"));
            chofer.setNombre(c.getNombre());
            chofer.setDni(c.getDni());
            cr.save(chofer);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer id){
        cr.deleteById(id);
        return ResponseEntity.status(OK).build();
    }
}
