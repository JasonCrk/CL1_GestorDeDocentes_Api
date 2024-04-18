package com.CL1.GestorDeDocentes.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria", columnDefinition = "INT")
    private int id;

    @Column(name = "nombre", nullable = false, unique = true, columnDefinition = "VARCHAR(45)")
    private String name;
}
