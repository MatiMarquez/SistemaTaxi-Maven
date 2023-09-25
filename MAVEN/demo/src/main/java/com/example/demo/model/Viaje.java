package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Viaje {
    @Id
    private Integer id;
    private String origen;
    private String destino;
    private double costo;
    @ManyToOne
    @JoinColumn(name="idAuto")
    private Auto auto;
}