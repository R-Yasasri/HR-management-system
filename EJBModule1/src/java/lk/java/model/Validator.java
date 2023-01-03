/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rajitha Yasasri
 */
public class Validator {

    public static boolean isValidEmail(String email) {

        if (isEmptyText(email)) {
            return false;
        }

        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(email);

        if (m.matches()) {
            return true;
        } else {
            System.out.println("ValidationERROR caused by(Validator.sValidEmail)-" + email);
            return false;
        }
    }

    public static boolean isValidNumber(String text) {
//can validate decimal numbers(10.56..etc) too
        if (isEmptyText(text)) {
            return false;
        }

        String exp = "-?(\\d+|\\d+\\.\\d+|\\.\\d+)([eE][-+]?\\d+)?";
        Pattern p = Pattern.compile(exp);

        Matcher m = p.matcher(text);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isValidPhoneNumber(String contact) {

        if (isEmptyText(contact)) {

            return false;
        }

        if (contact.length() == 10) {

            String firstDigit = contact.substring(0, 1);
            if (!firstDigit.equals("0")) {
                System.out.println("ValidationERROR caused by(Validator.isValidPhoneNumber)-" + contact);
                return false;
            }

            if (isValidNumber(contact)) {
                return true;
            } else {
                System.out.println("ValidationERROR caused by(Validator.isValidPhoneNumber)-" + contact);
                return false;
            }
        } else {
            System.out.println("ValidationERROR caused by(Validator.isValidPhoneNumber)-" + contact);
            return false;
        }
    }

    public static boolean isValidString(String text) {

        if (isEmptyText(text)) {
            return false;
        }
        text = text.trim();
        String exp = "[A-Z\\sa-z]*";
        Pattern p = Pattern.compile(exp);

        Matcher m = p.matcher(text);

        if (m.matches()) {
            return true;
        } else {
            System.out.println("ValidationERROR caused by(Validator.isValidString)-" + text);
            return false;
        }

    }
    
    public static boolean isValidAlphaNumeric(String text) {

        if (isEmptyText(text)) {
            return false;
        }
        text = text.trim();
        String exp = "[A-Z\\sa-z0-9]*";
        Pattern p = Pattern.compile(exp);

        Matcher m = p.matcher(text);

        if (m.matches()) {
            return true;
        } else {
            System.out.println("ValidationERROR caused by(Validator.isValidAlphaNumeric)-" + text);
            return false;
        }

    }
    
    
    

    public static boolean isValidAddressString(String address) {

        if (isEmptyText(address)) {
            return false;
        }

        String exp = "[\\,a-zA-Z\\\\/\\,\\s\\w\\.]*";
        Pattern p = Pattern.compile(exp);

        Matcher m = p.matcher(address);

        if (m.matches()) {
            return true;
        } else {
            System.out.println("ValidationERROR caused by(Validator.isValidAddressString)-" + address);
            return false;
        }

    }

    public static boolean isEmptyText(String text) {

        if (text == null) {
            return true;
        }
        if (text.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyText(String text) {

        return !isEmptyText(text);
    }

    public static boolean isNotEmptyTextMany(String... text) {

        for (String string : text) {

            boolean emptyString = isEmptyText(string);
            if (emptyString) {

                return false;
            }
        }

        return true;
    }

    public static boolean isValidStringMany(String... text) {

        for (String string : text) {
            boolean validString = isValidString(string);
            if (validString) {
                //nothing to do
            } else {
                System.out.println("not valid: " + string);
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNIC(String nic) {

        if (nic.isEmpty()) {
            return false;
        }

        if (nic.length() == 10 || nic.length() == 12) {
            return true;
        } else {
            System.out.println("ValidationERROR caused by(Validator.isValidNIC)-" + nic);
            return false;
        }
    }

    public static boolean isValidDate(String date) {

        if (isEmptyText(date)) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            Date parse = sdf.parse(date);
            return true;
        } catch (Exception e) {
            System.out.println("ValidationERROR caused by(Validator.isValidDate)-" + date);
            return false;
        }
    }
}
