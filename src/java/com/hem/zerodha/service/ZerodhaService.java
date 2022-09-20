package com.hem.zerodha.service;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

public class ZerodhaService {
    private static final String API_KEY = "5rca0vj4fhxy0qu6";
    private static final String API_Secret = "iiuz3r2r03i4ufahvxcj057e2icpyyxj";
    public static final String USER_ID = "YK4302";

    public void login(String token) throws Exception, KiteException {
        KiteConnect kiteSdk = new KiteConnect(API_KEY);

        kiteSdk.setUserId(USER_ID);

        String url = kiteSdk.getLoginURL();

        User user =  kiteSdk.generateSession(token, API_Secret);

        kiteSdk.setAccessToken(user.accessToken);
        kiteSdk.setPublicToken(user.publicToken);

        kiteSdk.setSessionExpiryHook(() -> System.out.println("session expired"));

        kiteSdk.getInstruments("nse");
    }
}
