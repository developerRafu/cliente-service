package com.developerrafu.clientservice.helpers;

import com.developerrafu.clientservice.exceptions.InternalExecutionException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Slf4j
public class CompletableFutureHelper {
    private CompletableFutureHelper() {
    }

    public static <T> T get(final CompletableFuture<T> future) {
        try {
            return future.get();
        }catch (ExecutionException | InterruptedException e) {
            log.error(ErrorsEnum.EXTERNAL_EXECUTION_EXCEPTION.getFormattedMessage(e.getMessage()));
            throw new InternalExecutionException(e);
        }
    }

}
