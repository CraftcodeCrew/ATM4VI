package com.billigeplaetze.atm4vi.services.ui;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.billigeplaetze.atm4vi.domain.definitions.ITTSEngine;
import com.billigeplaetze.atm4vi.domain.definitions.exceptions.NotYetInitializedException;

import java.util.Locale;

/**
 * Created by dannynator on 20.01.18.
 */

public class TTSService implements ITTSEngine, TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private boolean isInitialized;

    @Override
    public void initialize(Context context) {
        tts = new TextToSpeech(context,this);

    }

    @Override
    public void readOut(String message) throws NotYetInitializedException {
        if (isInitialized) {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }
        else {
            throw new NotYetInitializedException("DU spast!");
        }
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.GERMAN);
            isInitialized = true;
        }
    }
    }

