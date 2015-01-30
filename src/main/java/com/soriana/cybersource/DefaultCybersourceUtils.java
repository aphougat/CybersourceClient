package com.soriana.cybersource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by abhay.narain.phougat on 1/30/2015.
 */
@Component
public class DefaultCybersourceUtils implements CybersourceUtils{

    private static final Logger LOG = Logger.getLogger(DefaultCybersourceUtils.class);

    private static int cartidnumber = 12345677;

    private String getSessionId()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        return session.getId();
    }

    private Object getSessionAttribute(String key)
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        return session.getAttribute(key);
    }

    private boolean removeSessionAttribute(String key)
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.removeAttribute(key);

        if(session.getAttribute(key) == null)
            return true;
        else return false;
    }
   private HashMap<String,String> getRequestMap()
   {
        HashMap<String,String> request = new HashMap<String,String>();
/*
        request.put("payPalButtonCreateService_run", "true" );
        request.put("payPalButtonCreateService_buttonType","buy");
        request.put("merchantID","cybersource_test_merchant_id");
        request.put("merchantReferenceCode","PK48ASBE54XCQ");
        request.put("billTo_firstName","Joe");
        request.put("billTo_lastName","Smith");
        request.put("billTo_street1","1040 Elm St.");
        request.put("billTo_city","San Jose");
        request.put("billTo_state","CA");
        request.put("billTo_postalCode","95127");
        request.put("billTo_country","US");
        request.put("shipTo_firstName","Joe");
        request.put("shipTo_lastName","Smith");
        request.put("shipTo_street1","1040 Elm St.");
        request.put("shipTo_city","San Jose");
        request.put("shipTo_state","CA");
        request.put("shipTo_postalCode","95127");
        request.put("shipTo_country","US");
        request.put("purchaseTotals_grandTotalAmount","25.99");
        request.put("purchaseTotals_taxAmount","2.55");
        request.put("purchaseTotals_freightAmount","4.95");
        request.put("purchaseTotals_currency","USD");
        request.put("paypal_cancel_return","http://paypalcancel.example.com");
        request.put("paypal_return","http://paypalsuccess.example.com");
        request.put("paypal_item_name","Nouveau Lamp");
        request.put("paypal_item_number","3362710");*/

        //request.put("access_key","cfe9195d645b3e70bf750cf3d4fa2996");
        request.put("amount","45.94");
        request.put("bill_to_address_city","San Jose");
        request.put("bill_to_address_country","US");
        request.put("bill_to_address_line1","address1");
        request.put("bill_to_address_line2","address2");
        request.put("bill_to_address_postal_code","95127");
        request.put("bill_to_email","testcard@test.com");
        request.put("bill_to_forename","hhhhtest");
        request.put("bill_to_surname","testh");
        request.put("card_cvn","123");
        request.put("card_expiry_date","03-2019");
        request.put("card_number","4111111111111111");
        request.put("card_type","001");
        request.put("currency","USD");
        request.put("customer_cookies_accepted","true");
        request.put("customer_ip_address","170.248.1.192");
        request.put("device_fingerprint_id","s18933746866080");
        request.put("item_0_quantity","1");
        request.put("item_0_sku","0282974001004");
        request.put("item_0_unit_price","39.99");
        request.put("item_1_code","shipping_only");
        request.put("item_1_name","shipping");
        request.put("item_1_quantity","1");
        request.put("item_1_sku","1");
        request.put("item_1_unit_price","5.95");
        request.put("line_item_count","2");
        request.put("locale","en-us");
        request.put("merchant_defined_data1", "testcard@test.com");
        request.put("merchant_defined_data2","0");
        request.put("merchant_defined_data22","Card");
        request.put("merchant_defined_data8","home-delivery");
        //request.put("override_custom_receipt_page","https://amt-www2.hm.com:443/fr_fr/checkout/cybersource/response");
        request.put("payment_method","card");
        request.put("profile_id","HMFRWEB");
        request.put("reference_number","18281005");
        request.put("ship_to_address_city","San Jose");
        request.put("ship_to_address_country","US");
        request.put("ship_to_address_line1","address1");
        request.put("ship_to_address_line2","address2");
        request.put("ship_to_address_postal_code","95127");
        request.put("ship_to_forename","hhhhtest");
        request.put("ship_to_surname","testh");
        //request.put("signature","KhDaVJQjUWPAKjUI/BlzAvBO5m4z/Rb2fBdA0oo6yIM=");
        //request.put("signed_date_time","2015-01-29T08:03:31Z");
        //request.put("signed_field_names","override_custom_receipt_page,bill_to_email,item_1_code,profile_id,transaction_type,locale,bill_to_address_line1,bill_to_address_line2,transaction_uuid,currency,amount,ship_to_forename,ship_to_address_postal_code,item_1_unit_price,item_1_sku,merchant_defined_data1,access_key,merchant_defined_data2,ship_to_surname,item_1_name,unsigned_field_names,ship_to_address_city,bill_to_address_city,merchant_defined_data8,merchant_defined_data22,signed_date_time,bill_to_surname,customer_ip_address,line_item_count,item_0_unit_price,bill_to_address_country,bill_to_address_postal_code,bill_to_forename,reference_number,item_0_quantity,ship_to_address_line1,customer_cookies_accepted,ship_to_address_line2,item_1_quantity,device_fingerprint_id,item_0_sku,payment_method,ship_to_address_country,signed_field_names");
        request.put("transaction_type","authorization,create_payment_token");
        //request.put("transaction_uuid","HMFRWEB-18281005-20150129090331");
        request.put("unsigned_field_names","card_type,card_cvn,card_number,card_expiry_date");

       return request;

    }


    @Override
    public String collectDataAndSign(final String returnURL, final String clientIp, final Properties props) {

        /*if (cartModel == null) {
            throw new IllegalArgumentException("Cart Model is null");
        }*/

        Map<String, String> signedData = getRequestMap();
        /*if (MapUtils.isNotEmpty(this.request)) {
            signedData.putAll(request);
        }*/

        //cartModel.getCode()
        String merchantReferenceCode = String.valueOf(cartidnumber+1);
       /* cartModel.setMerchantReferenceCode(merchantReferenceCode);
        modelService.save(cartModel);*/

        // Secure Acceptance Profile ID (obtained from the CyberSource EBC)
        String profileID = props.getProperty("cybersource.profileId");
        // Secure Acceptance Access Key (obtained from the CyberSource EBC)
        String accessKey = props.getProperty("cybersource.accessKey");
        // Current time stamp
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String transactionUUID = profileID + "-" + merchantReferenceCode + "-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Add core data to map

       /* Double totalPrice = 45.94;
        if (totalPrice != null) {
            signedData.put("amount", totalPrice.toString());
        }

        CurrencyModel currency = cartModel.getCurrency();
        if (currency != null) {
            signedData.put("currency", currency.getIsocode());
        }*/

      /*  DeliveryModeModel deliveryMode = cartModel.getDeliveryMode();
        if (deliveryMode != null && StringUtils.isNotEmpty(deliveryMode.getCode())) {
            signedData.put("merchant_defined_data8", deliveryMode.getCode());
        }*/

        signedData.put("access_key", accessKey);
        signedData.put("profile_id", profileID);
        signedData.put("transaction_uuid", transactionUUID);
        signedData.put("signed_date_time", simpleDateFormat.format(new Date()));
        signedData.put("payment_method", "card");

        //from cybersource documentation the locale HAS to be "en"
        //signedData.put("locale", HMCybersourceConstants.TOKENIZATION_LOCALE);
        signedData.put("reference_number", merchantReferenceCode);
        signedData.put("device_fingerprint_id", getSessionId());

        if (StringUtils.isNotEmpty(clientIp)) {
            signedData.put("customer_ip_address", clientIp);
        }

       /* UserModel userModel = userService.getCurrentUser();
        if (userModel != null) {

            String uid = userModel.getUid();
            if (StringUtils.isNotEmpty(uid)) {
                signedData.put("merchant_defined_data1", uid);
                signedData.put("bill_to_email", uid);
            }

            String differenceDate = getDifferenceDate(userModel.getCreationtime());
            if (StringUtils.isNotEmpty(differenceDate)) {
                signedData.put("merchant_defined_data2", differenceDate);
            }

            // Reply URL (Override)
        }*/

        //signedData.put("merchant_defined_data22", HMCybersourceConstants.CYBERSOURCE_PAYMENT_METHOD);

        // Add cookies accepted info to map
        if (getSessionAttribute("customerCookiesAccepted") != null) {
            signedData.put("customer_cookies_accepted", (String)getSessionAttribute("customerCookiesAccepted"));
            removeSessionAttribute("customerCookiesAccepted");
        }

        int index = 0;
       /* List<AbstractOrderEntryModel> orderEntryList = cartModel.getEntries();
        if (CollectionUtils.isNotEmpty(orderEntryList)) {
            for (AbstractOrderEntryModel orderEntry : orderEntryList) {

                if (orderEntry != null && orderEntry.getProduct() != null) {

                    signedData.put("item_" + index + "_name", orderEntry.getProduct().getName());
                    signedData.put("item_" + index + "_sku", orderEntry.getProduct().getCode());

                    if (orderEntry.getTotalPrice() != null && orderEntry.getTotalPrice() != null) {
                        signedData.put("item_" + index + "_unit_price", orderEntry.getTotalPrice().toString());
                    }

                    if (orderEntry.getQuantity() != null) {
                        signedData.put("item_" + index + "_quantity", orderEntry.getQuantity().toString());
                    }
                    ++index;
                }

            }
        }*/

        /*Integer itemCount = CollectionUtils.size(orderEntryList);

        if (cartModel.getDiscountedDeliveryCost() != null) {
            signedData.put("item_" + index + "_code", HMCybersourceConstants.CYBERSOURCE_SHIPPING_ITEM_CODE);
            signedData.put("item_" + index + "_name", HMCybersourceConstants.CYBERSOURCE_SHIPPING_ITEM_NAME);
            signedData.put("item_" + index + "_sku", String.valueOf(index));
            signedData.put("item_" + index + "_unit_price", cartModel.getDiscountedDeliveryCost().toString());
            signedData.put("item_" + index + "_quantity", BigInteger.ONE.toString());
            ++itemCount;
        }*/

        //signedData.put("line_item_count", itemCount.toString());

        // Add bill_to data to map
        /*AddressModel billingAddress = cartModel.getBillingAddress();
        if (billingAddress != null) {

            signedData.put("bill_to_forename", billingAddress.getFirstname());
            signedData.put("bill_to_surname", billingAddress.getLastname());
            signedData.put("bill_to_address_line1", billingAddress.getLine1());
            signedData.put("bill_to_address_city", billingAddress.getTown());
            signedData.put("bill_to_address_postal_code", billingAddress.getPostalcode());

            if (StringUtils.isNotEmpty(billingAddress.getLine2())) {
                signedData.put("bill_to_address_line2", billingAddress.getLine2());
            }

            if (billingAddress.getCountry() != null) {
                signedData.put("bill_to_address_country", billingAddress.getCountry().getIsocode());
            }
        }

        // Add ship_to data to map
        AddressModel deliveryAddress = cartModel.getDeliveryAddress();
        if (deliveryAddress != null) {
            signedData.put("ship_to_forename", deliveryAddress.getFirstname());
            signedData.put("ship_to_surname", deliveryAddress.getLastname());
            signedData.put("ship_to_address_line1", deliveryAddress.getLine1());
            signedData.put("ship_to_address_city", deliveryAddress.getTown());
            signedData.put("ship_to_address_postal_code", deliveryAddress.getPostalcode());

            if (StringUtils.isNotEmpty(deliveryAddress.getLine2())) {
                signedData.put("ship_to_address_line2", deliveryAddress.getLine2());
            }

            if (deliveryAddress.getCountry() != null) {
                signedData.put("ship_to_address_country", deliveryAddress.getCountry().getIsocode());
            }
        }*/

        signedData.put("override_custom_receipt_page", props.getProperty("cybersource.returnurl"));

        if (LOG.isDebugEnabled()) {
            LOG.debug("data to sign before filtering:" + signedData);
        }

        Iterator<String> valuesItor = signedData.values().iterator();

        while (valuesItor.hasNext()) {

            String value = valuesItor.next();
            if (value == null || "null".equalsIgnoreCase(value)) {
                valuesItor.remove();
            }
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("data to sign after filtering:" + signedData);
        }

        //this will be the return of the method
        StringBuilder stringBuf = new StringBuilder();
        stringBuf.append("<!-- Secure Acceptance Signed Data Fields -->\n");
        Iterator<Map.Entry<String, String>> signedDataIterator = signedData.entrySet().iterator();
        String listOfSignedDataFields = StringUtils.EMPTY;
        while (signedDataIterator.hasNext()) {
            Map.Entry<String, String> signedDataFields = signedDataIterator.next();
            listOfSignedDataFields += signedDataFields.getKey() + ",";
            String value = signedDataFields.getValue();
            stringBuf.append("<input type=\"hidden\" id=\"" + signedDataFields.getKey() + "\" name=\"" + signedDataFields.getKey() + "\" value=\"" + StringUtils.trimToEmpty(value)
                    + "\"/>\n");
        }

        listOfSignedDataFields += "signed_field_names";
        signedData.put("signed_field_names", listOfSignedDataFields);

        String signature = null;
        try {
            signature = sign(signedData, props);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            LOG.error("Could not sign the form, cybersource will refuse this call: " + e, e);
        }

        stringBuf.append("<input type=\"hidden\" id=\"signed_field_names\" name=\"signed_field_names\" value=\"" + listOfSignedDataFields + "\"/>\n");
        stringBuf.append("\n<!-- Secure Acceptance Signature -->\n");
        stringBuf.append("<input type=\"hidden\" id=\"signature\" name=\"signature\" value=\"" + signature + "\"/>");

        return stringBuf.toString();
    }

    public String sign(final Map<String, String> secureAcceptanceResponse,Properties props) throws InvalidKeyException, NoSuchAlgorithmException {
        return sign(buildDataToSign(secureAcceptanceResponse), props);
    }
    private static String buildDataToSign(Map<String, String> secureAcceptanceResponse) {
        String[] signedFieldNames = String.valueOf(secureAcceptanceResponse.get("signed_field_names")).split(",");
        List<String> dataToSign = new ArrayList<String>();
        for (String signedFieldName : signedFieldNames) {

            String fieldValue = secureAcceptanceResponse.get(signedFieldName);
            dataToSign.add(signedFieldName + "=" + StringUtils.trimToEmpty(fieldValue));
        }
        return StringUtils.join(dataToSign, ',');
    }
    public String sign(String data, Properties props) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(props.getProperty("cybersource.sign.secretkey").getBytes(),
                props.getProperty("cybersource.sign.algorithm"));
        Mac mac = Mac.getInstance(props.getProperty("cybersource.sign.algorithm"));
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(rawHmac).replace("\n", StringUtils.EMPTY);
    }

}
