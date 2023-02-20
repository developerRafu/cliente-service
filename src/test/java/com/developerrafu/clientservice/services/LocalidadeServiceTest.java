package com.developerrafu.clientservice.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.developerrafu.clientservice.helpers.builders.EstadoResponseMockBuilder;
import com.developerrafu.clientservice.helpers.builders.MunicipioResponseMockBuilder;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocalidadeServiceTest {
  LocalidadeClient client;
  LocalidadeService service;

  @BeforeEach
  void setUp() {
    client = mock(LocalidadeClient.class);
    service = new LocalidadeService(client);
  }

  @Test
  void shouldReturnEstados() {
    when(client.getEstados())
        .thenReturn(List.of(EstadoResponseMockBuilder.getBuilder().defaultValues().build()));
    final var result = service.getEstados();
    assertNotNull(result);
    assertNotEquals(Collections.emptyList(), result);
  }

  @Test
  void shouldReturnMunicipios() {
    when(client.getMunicipios(any()))
        .thenReturn(List.of(MunicipioResponseMockBuilder.getBuilder().defaultValues().build()));
    final var result = service.getMunicipios(1L);
    assertNotNull(result);
    assertNotEquals(Collections.emptyList(), result);
  }
}
