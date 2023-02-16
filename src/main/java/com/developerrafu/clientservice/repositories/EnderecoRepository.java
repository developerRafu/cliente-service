package com.developerrafu.clientservice.repositories;

import com.developerrafu.clientservice.models.domain.Endereco;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

  Optional<Endereco> findByClienteId(Long clienteId);
}
