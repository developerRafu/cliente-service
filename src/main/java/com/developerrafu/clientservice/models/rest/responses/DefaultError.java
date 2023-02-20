package com.developerrafu.clientservice.models.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

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
