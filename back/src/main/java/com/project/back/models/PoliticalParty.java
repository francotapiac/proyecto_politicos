package com.project.back.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="political_party")
public class PoliticalParty {

    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="aprobation", nullable = false)
    private float aprobation;
    @Column(name="description", nullable = false)
    private String description;

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAprobation() {
        return aprobation;
    }

    public void setAprobation(float aprobation) {
        this.aprobation = aprobation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Methods
    /*
    * calcularAprobacion(PoliticalParty): float
    * calcularAprobaciones(PoliticalParty): float
    * rankear()
    * */
}
