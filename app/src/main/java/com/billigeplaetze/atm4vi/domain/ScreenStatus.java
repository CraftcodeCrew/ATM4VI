package com.billigeplaetze.atm4vi.domain;

import com.billigeplaetze.atm4vi.domain.definitions.Screen;

/**
 * Created by djuelg on 21.01.18.
 */

public class ScreenStatus {
    private static final ScreenStatus ourInstance = new ScreenStatus();
    private Screen status = Screen.Unknown;

    public static ScreenStatus getInstance() {
        return ourInstance;
    }

    public Screen getStatus() {
        return this.status;
    }

    public void updateStatus(Screen status) {
        this.status = status;
    }

    private ScreenStatus() {
    }
}
