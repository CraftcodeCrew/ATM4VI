package com.billigeplaetze.atm4vi.domain.definitions.exceptions;

/**
 * Created by dannynator on 20.01.18.
 */

public class NotYetInitializedException extends UnsupportedOperationException {

    public NotYetInitializedException(String message) {
        super(message);
    }
}
