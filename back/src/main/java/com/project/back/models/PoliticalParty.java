package com.project.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="political_party")
public class PoliticalParty implements Serializable{

    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name", nullable = false)
    private String realName;
    @Column(name="aka_name", nullable = false)
    private String akaName;
    @Column(name="aprobation", nullable = false)
    private float aprobation;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="aprobation_actuality", nullable = false)
    private float aprobationActuality;
    @Column(name="count_negative", nullable = true)
    private Integer countNegative;
    @Column(name="count_positive", nullable = true)
    private Integer countPositive;
    @Column(name="count_very_negative", nullable = true)
    private Integer countVeryNegative;
    @Column(name="count_very_positive", nullable = true)
    private Integer countVeryPositive;
    @Column(name="url_image", nullable = true)
    private String url_image;

    @OneToMany(mappedBy = "politicalParty",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Politician> politicians;

    public PoliticalParty(){}

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

    public String getUrlImage() {return url_image;}

    public void setUrlImage(String url_image) {this.url_image = url_image; }

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

    //Entregando lista de politicos asociados al partido politico
    public List<Politician> getPoliticians(){ return politicians;}

    public Float calculateApprobation(Integer total, Integer positive, Integer verypositive){
        Integer totalPositive = positive + verypositive;
        if(total == 0.0){
            return (float) total;
        }
        Float approbation = (float) ((totalPositive * 100) / total);
        return approbation;
    }



}
