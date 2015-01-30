package com.soriana.cybersource;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by abhay.narain.phougat on 1/30/2015.
 */
public interface CybersourceUtils {

    public Map<String,String> collectDataAndSign(final String returnURL,  final Properties props,final Map<String,String> map) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException;

    public String getSessionId();

}
