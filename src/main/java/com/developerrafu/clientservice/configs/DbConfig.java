package com.developerrafu.clientservice.configs;

import com.developerrafu.clientservice.models.domain.Cliente;
import com.developerrafu.clientservice.models.domain.Endereco;
import com.developerrafu.clientservice.services.ClienteService;
import com.developerrafu.clientservice.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    private final ClienteService clienteService;
    private final EnderecoService enderecoService;

    @Autowired
    public DbConfig(final ClienteService clienteService, final EnderecoService enderecoService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
    }

    @Bean
    public Cliente init() {
        final var endereco = Endereco.builder().cep("01153000").numero("22").build();
        final var cliente = Cliente.builder().cpf("70288841395").nome("User 1").build();
        final var cliente2 = Cliente.builder().cpf("80784768412").nome("User 2").build();
        clienteService.save(cliente);
        clienteService.save(cliente2);
        endereco.setClienteId(cliente.getId());
        enderecoService.save(endereco);
        return cliente;
    }
}
