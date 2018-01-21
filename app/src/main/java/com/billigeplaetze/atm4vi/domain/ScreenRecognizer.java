package com.billigeplaetze.atm4vi.domain;

import com.billigeplaetze.LoggerDirtySt;
import com.billigeplaetze.atm4vi.domain.definitions.IScreenChangedListener;
import com.billigeplaetze.atm4vi.domain.definitions.Screen;
import com.billigeplaetze.atm4vi.services.ocr.pojo.Line;
import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;
import com.billigeplaetze.atm4vi.services.ocr.pojo.Region;
import com.billigeplaetze.atm4vi.services.ocr.pojo.Word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djuelg on 21.01.18.
 */

public class ScreenRecognizer {
    private final List<String> texts;
    private final IScreenChangedListener bob;
    public ScreenRecognizer(ReceivedData receivedData, IScreenChangedListener bob) {
         texts = makeText(receivedData);
        this.bob = bob;
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
                                texts.add(w.getText().toLowerCase());
                            }
                        }
                    }
                }
            }
        }
        LoggerDirtySt.getInstance().appendLog(texts.toString());
        return texts;
    }

    public Screen recognize(){

        if (  listContainsWord("vorteile") ||  listContainsWord("geben") && listContainsWord("karte") || listContainsWord("insert") || listContainsWord("your")) {
            compareScreens(Screen.Welcome);
            return Screen.Welcome;
        }
        else if (listContainsWord("funk") || listContainsWord("funktion") || listContainsWord("handy") || listContainsWord("einzah") || listContainsWord("kontostand")) {
            compareScreens(Screen.Functions);
            return Screen.Functions;
        }
        else if (listContainsWord("geheim") || listContainsWord("verdeckt") || listContainsWord( "korrektur") || listContainsWord("eingab")) {
            compareScreens(Screen.PIN);
            return Screen.PIN;
        }
        else if (listContainsWord("gewünschten") || listContainsWord("betrag") || listContainsWord("auswahl") || listContainsWord("bieten") || listContainsWord("scheinarten") || listContainsWord("alternative")) {
            compareScreens(Screen.MoneyMoney);
            return Screen.MoneyMoney;
        } else if(listContainsWord("entneh") && listContainsWord("kart")) {
            compareScreens(Screen.Card);
            return Screen.Card;
        } else if (listContainsWord("entneh") && listContainsWord("geld")){
            compareScreens(Screen.TakeMoney);
            return Screen.TakeMoney;
        } else {
            return Screen.Unknown;
        }
    }

    private boolean listContainsWord( String word) {
        boolean value = false;
        for (String temp : texts) {
            if(temp.contains(word)) {
                value = true;
            }
        }
        return value;
    }


    private void compareScreens(Screen current) {
        if (!ScreenStatus.getInstance().getStatus().equals(current)) {
            ScreenStatus.getInstance().updateStatus(current);
            bob.onScreenChanged(current);
        }
    }
}
