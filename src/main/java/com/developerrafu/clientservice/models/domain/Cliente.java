package com.developerrafu.clientservice.models.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAB_CLIENTE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String cpf;
}
