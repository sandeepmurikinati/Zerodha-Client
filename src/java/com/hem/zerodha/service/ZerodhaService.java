package com.hem.zerodha.service;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.OHLCQuote;
import com.zerodhatech.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZerodhaService {
    private static final String API_KEY = "5rca0vj4fhxy0qu6";
    private static final String API_Secret = "iiuz3r2r03i4ufahvxcj057e2icpyyxj";
    public static final String USER_ID = "YK4302";
    public static final String NIFTY_INSTRUMENT = "NIFTY 50";

    public static final String NFO_NIFTY = "NIFTY22922";

    static KiteConnect kiteSdk = new KiteConnect(API_KEY);
    private static ObservableList<Instrument> instruments;

    public static void login(String token) throws Exception, KiteException {

        kiteSdk.setUserId(USER_ID);

        String url = kiteSdk.getLoginURL();

        User user =  kiteSdk.generateSession(token, API_Secret);

        kiteSdk.setAccessToken(user.accessToken);
        kiteSdk.setPublicToken(user.publicToken);

        kiteSdk.setSessionExpiryHook(() -> System.out.println("session expired"));

/*        List<Instrument> instruments = kiteSdk.getInstruments("NSE");

        System.out.println(instruments);

        List<Instrument> nfoinstruments = kiteSdk.getInstruments("NFO");

        System.out.println(nfoinstruments);*/

/*        String[] nifty = {NIFTY_INSTRUMENT};

        Map<String, LTPQuote> ltp = kiteSdk.getQuote(nifty);

        LTPQuote ltpQuote = ltp.get(nifty);
        System.out.println(ltpQuote.lastPrice);*/
    }

    public static ObservableList<Instrument> getTableModel() {
        int niftyLtp = 17625;

        int mod = niftyLtp / 100;

        instruments = FXCollections.observableArrayList(
                new Instrument(NFO_NIFTY + (mod - 1) + "00" + "CE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod) + "00" + "CE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod + 1) + "00" + "CE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod + 2) + "00" + "CE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod + 1) + "00" + "PE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod) + "00" + "PE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod - 1) + "00" + "PE", "0", "0", "0"),
                new Instrument(NFO_NIFTY + (mod - 2) + "00" + "PE", "0", "0", "0")
        );

        setQuotes();
        return instruments;
    }

    private static void setQuotes() {
        List<String> symbols = new ArrayList<>();
        instruments.forEach( each -> {
            symbols.add(each.getPriceSymbol());
        });
        try {
            Map<String, OHLCQuote> ohlc = kiteSdk.getOHLC(symbols.toArray(new String[symbols.size()]));
            ohlc.keySet().forEach( key -> {
                OHLCQuote ohlcQuote = ohlc.get(key);
                Instrument instrument = getInstrument(key);
                if (instrument != null) {
                    instrument.setOHLC(ohlcQuote);
                }
            });
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    private static Instrument getInstrument(String symbol) {
        return instruments.stream().filter(each -> each.isMatching(symbol)).findAny().orElse(null);
    }

}
