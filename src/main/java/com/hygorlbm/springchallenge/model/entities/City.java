package com.hygorlbm.springchallenge.model.entities;

import com.hygorlbm.springchallenge.model.enums.UF;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private Long id;

    @Column(name="CITY_NAME", nullable = false)
    private String name;

    @Column(name="CITY_STATE", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private UF state;

    @OneToMany
    @JoinColumn(name = "city_id")
    private List<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UF getState() {
        return state;
    }

    public void setState(UF state) {
        this.state = state;
    }
}
