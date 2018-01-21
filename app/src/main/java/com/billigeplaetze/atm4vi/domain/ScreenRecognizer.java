package com.billigeplaetze.atm4vi.domain;

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
                                texts.add(w.getText());
                            }
                        }
                    }
                }
            }
        }
        appendLog(texts.toString());
        return texts;
    }

    public Screen recognize(){
        if (texts.contains("Vorteile") || texts.contains("geben") && texts.contains("Karte") || texts.contains("insert") && texts.contains("your")) {
            compareScreens(Screen.Welcome);
            return Screen.Welcome;
        }
        if (texts.contains("Funktion") || texts.contains("Funktion") || texts.contains("Handy") || texts.contains("Einzahlung") || texts.contains("Kontostand")) {
            compareScreens(Screen.Functions);
            return Screen.Functions;
        }
        if (texts.contains("Geheimzahl") && texts.contains("verdeckt") || texts.contains("Korrekturtaste") && texts.contains("Eingabe")) {
            compareScreens(Screen.PIN);
            return Screen.PIN;
        }
        if (texts.contains("gewünschten") && texts.contains("Betrag") || texts.contains("Auswahl") && texts.contains("bieten") || texts.contains("Scheinarten") && texts.contains("alternative")) {
            compareScreens(Screen.MoneyMoney);
            return Screen.MoneyMoney;
        }
        return Screen.Unknown;
    }

    public static void appendLog(String text)
    {
        File logFile = new File("sdcard/log.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void compareScreens(Screen current) {
        if (!ScreenStatus.getInstance().getStatus().equals(current)) {
            ScreenStatus.getInstance().updateStatus(current);
            bob.onScreenChanged(current);

        }
    }
}
