package com.developerrafu.clientservice.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.helpers.builders.*;
import com.developerrafu.clientservice.services.EnderecoService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

class EnderecoControllerTest {
  EnderecoService service;
  EnderecoController controller;

  @BeforeEach
  void setUp() {
    service = mock(EnderecoService.class);
    controller = new EnderecoController(service);
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
  }

  @Test
  void shouldReturnCep() {
    when(service.findCep(anyString()))
        .thenReturn(ViaCepResponseMockBuilder.getBuilder().defaultValues().build());
    final var result = controller.getByCep(ConstantsHelper.MOCKED_CEP);
    assertResponse(result);
  }

  @Test
  void ShouldReturnEstados() {
    when(service.getEstados())
        .thenReturn(
            List.of(
                EstadoResponseMockBuilder.getBuilder().defaultValues().build(),
                EstadoResponseMockBuilder.getBuilder().defaultValues().withUF("RJ").build(),
                EstadoResponseMockBuilder.getBuilder().defaultValues().withUF("PA").build()));
    final var result = controller.getEstados();
    assertResponse(result);
  }

  @Test
  void ShouldReturnMunicipios() {
    when(service.getMunicipios(any()))
        .thenReturn(List.of(MunicipioResponseMockBuilder.getBuilder().defaultValues().build()));
    final var result = controller.getMunicipiosbByUf(1L);
    assertResponse(result);
  }

  @Test
  void shouldSaveEndereco() {
    when(service.saveRequest(any()))
        .thenReturn(EnderecoMockBuilder.getBuilder().defaultValues().build());
    final var result =
        controller.upsert(EnderecoRequestMockBuilder.getBuilder().defaultValues().build());
    assertNotNull(result);
    assertNotNull(result.getBody());
    assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  private void assertResponse(final ResponseEntity result) {
    assertNotNull(result);
    assertNotNull(result.getBody());
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }
}
