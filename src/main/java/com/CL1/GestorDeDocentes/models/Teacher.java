package com.CL1.GestorDeDocentes.models;

import com.CL1.GestorDeDocentes.models.enums.Gender;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "docente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddocente", columnDefinition = "INT")
    private int id;

    @Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(45)")
    private String name;

    @Column(name = "dni", nullable = false, unique = true, columnDefinition = "CHAR(8)")
    private String dni;

    @Column(name = "fechaNacimiento", nullable = false, columnDefinition = "DATE")
    private LocalDate birthdate;

    @Column(name = "sueldo", nullable = false, columnDefinition = "DOUBLE")
    private BigDecimal salary;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(45)")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false, columnDefinition = "VARCHAR(45)")
    private Gender gender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "idcategoria")
    private Category category;
}
