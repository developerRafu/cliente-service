package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.helpers.builders.ClienteResponseMockBuilder;
import com.developerrafu.clientservice.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientRestControllerTest {
    ClienteService service;
    ClientRestController controller;

    @BeforeEach
    void setUp() {
        service = mock(ClienteService.class);
        controller = new ClientRestController(service);
    }

    @Test
    void shouldReturnClienteResponse() {
        final var response = ClienteResponseMockBuilder.getBuilder().defaultValues().build();
        when(service.getByCPF(anyString())).thenReturn(response);
        final var result = controller.getByCpf(ConstantsHelper.MOCKED_CPF);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(response, result.getBody());
    }
}
