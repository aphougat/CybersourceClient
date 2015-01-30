package com.soriana.cybersource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class CybersourceController {

    @Autowired
    CybersourceUtils utils;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

        Properties props = new Properties();
        try {
            props.load(CybersourceController.class.getClassLoader().getResourceAsStream("local.properties"));


            model.addAttribute("requestaddress", props.getProperty("cybersource.requestaddress"));

            model.addAttribute("content", utils.collectDataAndSign(props.getProperty("cybersource.returnurl"),"192.168.0.109",props));
        } catch (IOException e) {
            e.printStackTrace();
        }

		model.addAttribute("message", "Submit request to cybersource");
		return "cybersource";
	}
}