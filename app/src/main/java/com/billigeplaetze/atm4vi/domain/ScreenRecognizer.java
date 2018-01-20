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
        // TODO
        return 0;
    };
}
