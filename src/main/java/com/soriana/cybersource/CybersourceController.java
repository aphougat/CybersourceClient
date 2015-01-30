package com.soriana.cybersource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/checkout")
public class CybersourceController {

    @Autowired
    CybersourceUtils utils;

    private static int cartidnumber = 12345677;

	@RequestMapping(value = "cybersource.do",method = RequestMethod.POST)
	public String signandtokenize(ModelMap model, HttpServletRequest request) {

        HashMap signedData = new LinkedHashMap();

        Properties props = new Properties();
        try {
            props.load(CybersourceController.class.getClassLoader().getResourceAsStream("local.properties"));
            String merchantReferenceCode = String.valueOf(cartidnumber+=1);

            // Secure Acceptance Profile ID (obtained from the CyberSource EBC)
            String profileID = props.getProperty("cybersource.profileId");
            // Secure Acceptance Access Key (obtained from the CyberSource EBC)
            String accessKey = props.getProperty("cybersource.accessKey");
            // Current time stamp
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            /*String transactionUUID = profileID + "-" + merchantReferenceCode + "-"
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
*/
            signedData.put("access_key", accessKey);
            signedData.put("profile_id", profileID);
            signedData.put("transaction_uuid", UUID.randomUUID());

            signedData.put("override_custom_receipt_page", props.getProperty("cybersource.returnurl"));
            signedData.put("signed_field_names", props.getProperty("cybersource.signed_field_names"));
            signedData.put("unsigned_field_names", props.getProperty("cybersource.unsigned_field_names"));

            signedData.put("signed_date_time", simpleDateFormat.format(new Date()));
            signedData.put("reference_number", merchantReferenceCode);
            signedData.put("device_fingerprint_id", utils.getSessionId());
            signedData.put("locale", "en");
           /* if (getSessionAttribute("customerCookiesAccepted") != null) {
                signedData.put("customer_cookies_accepted", (String)getSessionAttribute("customerCookiesAccepted"));
                removeSessionAttribute("customerCookiesAccepted");
            }*/

            request.setCharacterEncoding("UTF-8");

            Enumeration paramsEnum = request.getParameterNames();
            while (paramsEnum.hasMoreElements()) {
                String paramName = (String) paramsEnum.nextElement();
                String paramValue = request.getParameter(paramName);
                if(!paramName.contains("submit"))
                {
                    signedData.put(paramName, paramValue);
                }

            }

            model.addAttribute("requestaddress", props.getProperty("cybersource.requestaddress"));

            model.addAttribute("content", utils.collectDataAndSign(props.getProperty("cybersource.returnurl"),props, signedData));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        //model.addAttribute("message", "Submit request to cybersource");
		return "cybersource";
	}


    @RequestMapping(value = "cybersource.do", method = RequestMethod.GET)
    public String startProcess()
    {
        return "signeddatafields";
    }

    @RequestMapping(value = "return.do", method = RequestMethod.POST)
    public String returnProcess(HttpServletRequest request,Model model)
    {
        HashMap params = new HashMap();
        Enumeration paramsEnum = request.getParameterNames();

        while (paramsEnum.hasMoreElements()) {
            String paramName = (String) paramsEnum.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);

        }

        model.addAttribute("responseMap",params);
        return "cybersourceResponse";
    }


}