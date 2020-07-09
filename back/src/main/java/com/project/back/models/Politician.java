package com.project.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="politician")
public class Politician {
    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="aprobation", nullable = false)
    private float aprobation;
    @Column(name="aprobation_actuality", nullable = false)
    private float aprobationActuality;
    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_political_party", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoliticalParty idPoliticalParty;

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

    public float getAprobationActuality() {
        return aprobationActuality;
    }

    public void setAprobationActuality(float aprobationActuality) {
        this.aprobationActuality = aprobationActuality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Metodos
    /*
    * calcularAprobacion(Politician):float
    * calcularAprobaciones(Politician):float
    * calcularAprobacionActualidad():float
    * rankear()
    * */
}
