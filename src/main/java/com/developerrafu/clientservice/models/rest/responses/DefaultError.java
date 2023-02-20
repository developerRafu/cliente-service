package com.developerrafu.clientservice.models.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultError {
  private String message;
  private Integer code;
  private List<String> details;
}
