package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelo;
    private String matricula;

    @ManyToOne
    @JoinColumn(name="idChofer")
    private Chofer chofer;

    @OneToMany(mappedBy = "auto")
    @JsonIgnore
    private List<Viaje> viajes;
}
