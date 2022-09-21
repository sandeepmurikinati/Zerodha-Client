package com.hem.zerodha.service;

import com.zerodhatech.models.OHLCQuote;
import javafx.beans.property.SimpleStringProperty;

public class Instrument {
    public SimpleStringProperty tradingSymbol;
    public SimpleStringProperty quantity;
    public SimpleStringProperty ltp;
    public SimpleStringProperty pnl;

    public Instrument(String tradingSymbol, String quantity, String ltp, String pnl) {
        this.tradingSymbol = new SimpleStringProperty(tradingSymbol);
        this.quantity = new SimpleStringProperty(quantity);
        this.ltp = new SimpleStringProperty(ltp);
        this.pnl = new SimpleStringProperty(pnl);
    }


    public String getTradingSymbol() {
        return tradingSymbol.get();
    }

    public SimpleStringProperty tradingSymbolProperty() {
        return tradingSymbol;
    }

    public void setTradingSymbol(String tradingSymbol) {
        this.tradingSymbol.set(tradingSymbol);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getLtp() {
        return ltp.get();
    }

    public SimpleStringProperty ltpProperty() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp.set(ltp);
    }

    public String getPnl() {
        return pnl.get();
    }

    public SimpleStringProperty pnlProperty() {
        return pnl;
    }

    public void setPnl(String pnl) {
        this.pnl.set(pnl);
    }

    public String getPriceSymbol() {
        return "NFO:" + tradingSymbol.get();
    }

    public boolean isMatching(String symbol) {
        String s = "NFO:" + tradingSymbol.get();
        return s.equals(symbol);
    }

    public void setOHLC(OHLCQuote ohlcQuote) {
        ltp.setValue(Double.toString(ohlcQuote.lastPrice));
    }
}
