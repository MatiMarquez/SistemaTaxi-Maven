package com.example.demo.controller;

import com.example.demo.model.Viaje;
import com.example.demo.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService vs;

    @GetMapping("")
    public List<Viaje> getAll(){
        return vs.getAll();
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody Viaje v){
        return vs.add(v);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Viaje v){
        return vs.update(id, v);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id){
        return vs.delete(id);
    }
}
