package com.api.agendatelefonica.Controller;


import com.api.agendatelefonica.Model.ModelContatos;
import com.api.agendatelefonica.Service.ServicesContatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerContatos {
    @Autowired
    ServicesContatos servicesContatos;

    @PostMapping(path = "/agenda")
    public ResponseEntity<Object> cadastrarContato(@RequestBody @Valid ModelContatos modelContatos) {
        if (servicesContatos.existsByNumeroTelefone(modelContatos.getNumeroTelefone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Número de telefone já está sendo usado!");
        }
        if (servicesContatos.existsByEmail(modelContatos.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: E-mail já está em uso!");
        }

        modelContatos.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC-03:00")));
        return ResponseEntity.status(HttpStatus.CREATED).body(servicesContatos.cadastrarContato(modelContatos));
    }

    @GetMapping(path = "/agenda")
    public List<ModelContatos> buscarContatos() {
        return servicesContatos.buscarContatos();
    }

    @PutMapping(path = "/agenda/{id}")
    public ModelContatos alterarContato(@RequestBody ModelContatos modelContatos) {
        return servicesContatos.alterarContato(modelContatos);
    }

    @GetMapping(path = "/agenda/{id}")
    public Optional<ModelContatos> buscarContatoId(@PathVariable Long id) {
        return servicesContatos.buscarContatoID(id);
    }

    @DeleteMapping(path = "/agenda/{id}")
    public void excluirContato(@PathVariable Long id) {
        servicesContatos.excluirContato(id);
    }

}
