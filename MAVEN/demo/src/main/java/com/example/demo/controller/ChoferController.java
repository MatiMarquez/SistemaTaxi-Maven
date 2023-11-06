package com.example.demo.controller;

import com.example.demo.model.Auto;
import com.example.demo.model.Chofer;
import com.example.demo.services.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chofer")
@CrossOrigin(origins = "*")
public class ChoferController {

    @Autowired
    private ChoferService cs;

    @GetMapping("")
    public List<Chofer> getAll(){
        return cs.getAll();
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody Chofer c){
        return cs.add(c);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Chofer c){
        return cs.update(id, c);
    }

    @PostMapping("/{id}/setAuto")
    public ResponseEntity setAuto(@PathVariable Integer id, @RequestBody Auto auto){
        return cs.setAuto(id, auto);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id){
        return cs.delete(id);
    }
}
