package com.developerrafu.clientservice.repositories;

import com.developerrafu.clientservice.models.domain.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findByCpf(String cpf);
}
