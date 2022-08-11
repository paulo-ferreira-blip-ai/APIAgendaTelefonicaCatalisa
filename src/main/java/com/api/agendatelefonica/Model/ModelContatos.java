package com.api.agendatelefonica.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendatelefonica")
public class ModelContatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false, length = 50)
    @NotBlank
    private String nome;
    @Column(nullable = false, unique = false, length = 50)
    @NotBlank
    @Size(min = 6, max = 14)
    private String numeroTelefone;
    @Column(nullable = false, unique = false, length = 50)
    @NotBlank
    @Email
    private String email;
    @Column(nullable = false, length = 50)
    private LocalDateTime dataRegistro;

}
