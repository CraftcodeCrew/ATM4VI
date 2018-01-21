package com.billigeplaetze.atm4vi.domain;

/**
 * Created by djuelg on 21.01.18.
 */

public class ScreenStatus {
    private static final ScreenStatus ourInstance = new ScreenStatus();
    private int status;

    public static ScreenStatus getInstance() {
        return ourInstance;
    }

    public int getStatus() {
        return this.status;
    }

    public void updateStatus(int status) {
        this.status = status;
    }

    private ScreenStatus() {
    }
}
