package com.api.agendatelefonica.Service;

import com.api.agendatelefonica.Model.ModelContatos;
import com.api.agendatelefonica.Repositories.RepositoryContatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesContatos {
    @Autowired
    RepositoryContatos repositoryContatos;

    //Método para cadastrar um contato
    public ModelContatos cadastrarContato(ModelContatos modelContatos) {
        modelContatos.getNome();
        modelContatos.getNumeroTelefone();
        modelContatos.getId();
        modelContatos.getEmail();
        modelContatos.getDataRegistro();

        return repositoryContatos.save(modelContatos);

    }

    //Método para alterar um contato
    public ModelContatos alterarContato(ModelContatos modelContatos) {
        modelContatos.getId();
        modelContatos.getNome();
        modelContatos.getNumeroTelefone();
        modelContatos.getEmail();
        modelContatos.getDataRegistro();

        return repositoryContatos.save(modelContatos);

    }

    //Método para buscar todos os contato
    public List<ModelContatos> buscarContatos() {
        return repositoryContatos.findAll();
    }

    //Método para buscar contato pelo ID
    public Optional<ModelContatos> buscarContatoID(Long id) {
        return repositoryContatos.findById(id);

    }

    //Método para excluir um contato pelo ID
    public void excluirContato(Long id) {
        repositoryContatos.deleteById(id);
    }

    //método para informar se numero de telefone já existe e e-mail
    public boolean existsByNumeroTelefone(String numeroTelefone) {
        return repositoryContatos.existsByNumeroTelefone(numeroTelefone);
    }

    public boolean existsByEmail(String email) {
        return repositoryContatos.existsByEmail(email);
    }


}
