package com.billigeplaetze.atm4vi.domain.uc;

import android.content.Context;

import com.billigeplaetze.LoggerDirtySt;
import com.billigeplaetze.atm4vi.R;
import com.billigeplaetze.atm4vi.domain.definitions.IScreenChangedListener;
import com.billigeplaetze.atm4vi.domain.definitions.ITTSEngine;
import com.billigeplaetze.atm4vi.domain.definitions.Screen;

/**
 * Created by dannynator on 21.01.18.
 */

public class ScreenChangedInteractor implements IScreenChangedListener {

    private ITTSEngine ittsEngine;
    private Context context;

    public ScreenChangedInteractor(ITTSEngine ittsEngine, Context context) {
        this.ittsEngine = ittsEngine;
        this.context = context;
        ittsEngine.initialize(context);
    }

    @Override
    public void onScreenChanged(Screen screen) {
        LoggerDirtySt.getInstance().appendLog("BeforeSound:" + screen);
        switch (screen) {
            case PIN:
                ittsEngine.readOut(context.getResources().getString(R.string.enterPin));
                break;
            case Welcome:
                ittsEngine.readOut(context.getResources().getString(R.string.insertCard));
                break;
            case Functions:
                String functions = context.getResources().getString(R.string.functionsScreen);
                functions += " " + context.getResources().getString(R.string.buttonL2);
                functions += " " + context.getResources().getString(R.string.buttonR1);
                ittsEngine.readOut(functions);
                break;
            case MoneyMoney:
                String moneyTakin = context.getResources().getString(R.string.functionsScreen);
                moneyTakin += context.getResources().getString(R.string.buttonML1);
                moneyTakin += context.getResources().getString(R.string.buttonDown) + context.getResources().getString(R.string.buttonML2);
                moneyTakin += context.getResources().getString(R.string.buttonDown) + context.getResources().getString(R.string.buttonML3);
                moneyTakin += context.getResources().getString(R.string.buttonDown) + context.getResources().getString(R.string.buttonML4);
                moneyTakin += context.getResources().getString(R.string.buttonR1);
                moneyTakin += context.getResources().getString(R.string.buttonDown) + context.getResources().getString(R.string.buttonMR2);
                moneyTakin += context.getResources().getString(R.string.buttonDown) + context.getResources().getString(R.string.buttonMR3);
                ittsEngine.readOut(moneyTakin);
                break;
            case Card:
                ittsEngine.readOut("Karte entnehmen");
                break;
            case TakeMoney:
                ittsEngine.readOut("Geld entnehmen");
                break;
            default:
                ittsEngine.readOut("Kein Geldautomat erkannt");
                break;
        }
    }
}
