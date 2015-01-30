package com.soriana.cybersource;

import java.util.Properties;

/**
 * Created by abhay.narain.phougat on 1/30/2015.
 */
public interface CybersourceUtils {

    public String collectDataAndSign(final String returnURL, final String clientIp, final Properties props);
}
