package com.billigeplaetze.atm4vi.domain.definitions;

import android.content.Context;

import com.billigeplaetze.atm4vi.domain.definitions.exceptions.NotYetInitializedException;

/**
 * Created by dannynator on 20.01.18.
 */

public interface ITTSEngine {

    void initialize(Context context);

    void readOut(String message) throws NotYetInitializedException;

}
