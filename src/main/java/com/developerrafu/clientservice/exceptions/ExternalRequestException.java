package com.developerrafu.clientservice.exceptions;

import com.developerrafu.clientservice.helpers.Issue;
import lombok.Getter;

@Getter
public class ExternalRequestException extends RuntimeException {
    private final Issue issue;
    public ExternalRequestException(final Issue issue) {
        super(issue.getError().getFormattedMessage(issue.getClient()));
        this.issue = issue;
    }

}
