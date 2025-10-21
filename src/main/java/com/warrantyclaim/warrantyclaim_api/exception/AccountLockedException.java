package com.warrantyclaim.warrantyclaim_api.exception;

public class AccountLockedException extends RuntimeException {
    public AccountLockedException(String message) {
        super(message);
    }
}