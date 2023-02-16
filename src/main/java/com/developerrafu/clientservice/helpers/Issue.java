package com.developerrafu.clientservice.helpers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Issue {
    private String details;
    private ErrorsEnum error;
    private Integer code;
    private String Client;
}
