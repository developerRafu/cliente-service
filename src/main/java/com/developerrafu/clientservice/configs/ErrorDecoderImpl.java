package com.developerrafu.clientservice.configs;

import com.developerrafu.clientservice.exceptions.ExternalRequestException;
import com.developerrafu.clientservice.exceptions.UnprocessedResponseException;
import com.developerrafu.clientservice.helpers.ErrorsEnum;
import com.developerrafu.clientservice.helpers.Issue;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ErrorDecoderImpl implements ErrorDecoder {
  @Override
  public Exception decode(final String methodKey, final Response response) {
    try {
      String bodyContent =
          IOUtils.toString(response.body().asInputStream(), Charset.defaultCharset());

      final var error = ErrorsEnum.EXTERNAL_REQUEST_EXCEPTION;
      final var issue = new Issue();
      issue.setDetails(bodyContent);
      issue.setError(error);
      issue.setClient(response.toString());
      issue.setCode(response.status());

      log.error(error.getFormattedMessage(response.toString()));
      throw new ExternalRequestException(issue);
    } catch (IOException e) {
      final var message = ErrorsEnum.UNPROCESSED_RESPONSE.getFormattedMessage(methodKey);
      log.error(message, e);
      throw new UnprocessedResponseException(message);
    }
  }
}
