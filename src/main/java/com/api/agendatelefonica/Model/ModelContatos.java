package com.api.agendatelefonica.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Campo não informado")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "O nome deve iniciar com letra maiúscula")
    private String nome;
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Campo não informado")
    @Size(min = 6, max = 14, message = "O número de telefone precisa ter entre 6 e 14 caracteres")
    private String numeroTelefone;
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Campo não informado")
    @Email(message = "Informe um e-mail válido")
    private String email;
    @Column(nullable = false, length = 50)
    private LocalDateTime dataRegistro;

}
