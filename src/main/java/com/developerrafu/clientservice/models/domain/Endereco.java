package com.developerrafu.clientservice.models.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAB_ENDERECO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String numero;
    private Long clienteId;
}
