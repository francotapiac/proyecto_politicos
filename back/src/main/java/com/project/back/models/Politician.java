package com.project.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="politician")
public class Politician implements Serializable{
    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="real_name", nullable = false)
    private String realName;
    @Column(name="aka_name", nullable = false)
    private String akaName;
    @Column(name="aprobation", nullable = false)
    private float aprobation;
    @Column(name="aprobation_actuality", nullable = false)
    private float aprobationActuality;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="count_negative", nullable = true)
    private Integer countNegative;
    @Column(name="count_positive", nullable = true)
    private Integer countPositive;
    @Column(name="count_very_negative", nullable = true)
    private Integer countVeryNegative;
    @Column(name="count_very_positive", nullable = true)
    private Integer countVeryPositive;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_political_party",referencedColumnName = "id")
    @JsonIgnore
    private PoliticalParty politicalParty;

    public Politician(){}


    //Getters y Setters
    public String getRealName() {
        return realName;
    }

    public String getAkaName() {
        return akaName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAkaName(String akaName) {
        this.akaName = akaName;
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

    public Integer getCountNegative() {
        return countNegative;
    }

    public Integer getCountPositive() {
        return countPositive;
    }

    public Integer getCountVeryNegative() {
        return countVeryNegative;
    }

    public Integer getCountVeryPositive() {
        return countVeryPositive;
    }

    public PoliticalParty getPoliticalParty(){return politicalParty;}

    public void setCountNegative(Integer countNegative) {
        this.countNegative = countNegative;
    }

    public void setCountPositive(Integer countPositive) {
        this.countPositive = countPositive;
    }

    public void setCountVeryNegative(Integer countVeryNegative) {
        this.countVeryNegative = countVeryNegative;
    }

    public void setCountVeryPositive(Integer countVeryPositive) {
        this.countVeryPositive = countVeryPositive;
    }

    public void setPoliticalParty(PoliticalParty politicalParty){
        this.politicalParty = politicalParty;
    }
    //Metodos
    /*
    * calcularAprobaciones(Politician):float
    * calcularAprobacionActualidad():float
    * rankear()
    * */
    public Float calculateApprobation(Integer total, Integer positive, Integer verypositive){
        Integer totalPositive = positive + verypositive;
        if(total == 0.0){
            return (float) total;
        }
        Float approbation = (float) ((totalPositive * 100) / total);
        return approbation;
    }

}
