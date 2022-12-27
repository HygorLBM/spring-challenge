package com.hygorlbm.springchallenge.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hygorlbm.springchallenge.model.enums.Gender;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private Long id;
    @Column(name= "CPF", unique = true, nullable = false)
    private String cpf;

    @Column(name= "NAME")
    private String name;

    @Column(name= "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name= "GENDER")
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birth_date;

    @ManyToOne
    @JoinColumn(name = "city_ID", referencedColumnName = "id")
    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
