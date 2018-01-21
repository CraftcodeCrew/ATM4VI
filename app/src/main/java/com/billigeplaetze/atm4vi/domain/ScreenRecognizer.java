package com.billigeplaetze.atm4vi.domain;

import com.billigeplaetze.atm4vi.services.ocr.pojo.Line;
import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;
import com.billigeplaetze.atm4vi.services.ocr.pojo.Region;
import com.billigeplaetze.atm4vi.services.ocr.pojo.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djuelg on 21.01.18.
 */

public class ScreenRecognizer {
    private final List<String> texts;
    public ScreenRecognizer(ReceivedData receivedData) {
         texts = makeText(receivedData);
    }

    public static List<String> makeText(ReceivedData receivedData){
        List<String> texts = new ArrayList<>();
        if (receivedData.getRegions() == null) {
            return texts;
        } else {
            for (Region r : receivedData.getRegions()){
                if (r.getLines() != null) {
                    for (Line l : r.getLines()) {
                        if (l.getWords() != null) {
                            for (Word w : l.getWords()){
                                texts.add(w.getText());
                            }
                        }
                    }
                }
            }
        }
        return texts;
    }

    public int recognize(){
        if (texts.contains("geben") && texts.contains("Karte") || texts.contains("insert") && texts.contains("card")) {
            return 1;
        }
        if (texts.contains("Funktion") && (texts.contains("Auszahlung") || texts.contains("Einzahlung") || texts.contains("Kontostand"))) {
            return 2;
        }
        if (texts.contains("Geheimzahl") && texts.contains("verdeckt") || texts.contains("Korrekturtaste") && texts.contains("Eingabe")) {
            return 3;
        }
        if (texts.contains("anderer") && texts.contains("Betrag") || texts.contains("Scheinarten") && texts.contains("alternative")) {
            return 4;
        }
            return -1;
    }

}
