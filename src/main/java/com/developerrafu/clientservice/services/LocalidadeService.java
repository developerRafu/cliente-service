package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {
    private final LocalidadeCliente cliente;

    public LocalidadeService(final LocalidadeCliente cliente) {
        this.cliente = cliente;
    }

    public List<EstadoResponse> getEstados() {
        return cliente.getEstados();
    }

    public List<MunicipioResponse> getMunicipios(final Long estadoId) {
        return cliente.getMunicipios(estadoId);
    }
}
