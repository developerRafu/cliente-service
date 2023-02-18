package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.helpers.builders.ClienteMockBuilder;
import com.developerrafu.clientservice.helpers.builders.ClienteResponseMockBuilder;
import com.developerrafu.clientservice.helpers.builders.EnderecoResponseMockBuilder;
import com.developerrafu.clientservice.mappers.ClienteMapper;
import com.developerrafu.clientservice.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClienteServiceTest {
    ClienteRepository repository;
    EnderecoService enderecoService;
    ClienteMapper mapper;
    ClienteService service;

    @BeforeEach
    void setUp() {
        repository = mock(ClienteRepository.class);
        enderecoService = mock(EnderecoService.class);
        mapper = Mappers.getMapper(ClienteMapper.class);
        service = new ClienteService(repository, enderecoService, mapper);
    }

    @Test
    void shouldSaveCliente() {
        final var cliente = ClienteMockBuilder.getBuilder().defaultValues().build();
        when(repository.save(cliente)).thenReturn(cliente);
        final var result = service.save(cliente);
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void shouldReturnClienteResponse(){
        final var cliente = ClienteMockBuilder.getBuilder().defaultValues().build();
        when(repository.findByCpf(anyString())).thenReturn(Optional.of(cliente));
        when(enderecoService.findEnderecoByClienteId(any())).thenReturn(EnderecoResponseMockBuilder.getBuilder().defaultValues().build());
        final var result = service.getByCPF(ConstantsHelper.MOCKED_CPF);
        assertTrue(result.isPresent());
        assertNotNull(result.get().getEndereco());
    }

    @Test
    void shouldReturnEmpty(){
        when(repository.findByCpf(anyString())).thenReturn(Optional.empty());
        final var result = service.getByCPF(ConstantsHelper.MOCKED_CPF);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEnderecoEmpty(){
        final var cliente = ClienteMockBuilder.getBuilder().defaultValues().build();
        when(repository.findByCpf(anyString())).thenReturn(Optional.of(cliente));
        final var result = service.getByCPF(ConstantsHelper.MOCKED_CPF);
        assertTrue(result.isPresent());
        assertNull(result.get().getEndereco());
    }
}
