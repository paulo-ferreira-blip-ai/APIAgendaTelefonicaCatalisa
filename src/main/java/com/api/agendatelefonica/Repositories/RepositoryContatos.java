package com.api.agendatelefonica.Repositories;

import com.api.agendatelefonica.Model.ModelContatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RepositoryContatos extends JpaRepository<ModelContatos, Long> {
    boolean existsByNumeroTelefone(String numeroTelefone);

    boolean existsByEmail(String email);
}
