package com.api.agendatelefonica.Controller;


import com.api.agendatelefonica.Model.ModelContatos;
import com.api.agendatelefonica.Service.ServicesContatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Object> alterarContato(@RequestBody @Valid ModelContatos modelContatos) {
        if (servicesContatos.existsByNumeroTelefone(modelContatos.getNumeroTelefone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Número de telefone já está sendo usado!");
        }
        if (servicesContatos.existsByEmail(modelContatos.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: E-mail já está em uso!");
        }

        modelContatos.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC-03:00")));
        return ResponseEntity.status(HttpStatus.CREATED).body(servicesContatos.alterarContato(modelContatos));
    }

    @GetMapping(path = "/agenda/{id}")
    public Optional<ModelContatos> buscarContatoId(@PathVariable Long id) {
        return servicesContatos.buscarContatoID(id);
    }

    @DeleteMapping(path = "/agenda/{id}")
    public void excluirContato(@PathVariable Long id) {
        servicesContatos.excluirContato(id);
    }
    //método para aplicar validações feitas na classe model
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validacaoDeErros(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campoNome = ((FieldError) error).getField();
            String mensagemDeErro = error.getDefaultMessage();
            errors.put(campoNome, mensagemDeErro);
        });
        return errors;
    }

}
