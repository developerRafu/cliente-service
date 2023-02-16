package com.developerrafu.clientservice.repositories;

import com.developerrafu.clientservice.models.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCep(final String cep);

    Optional<Endereco> findByClienteId(Long clienteId);
}
