/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author p_cor
 */
public class GlobalFunctions {
    
    public static String encrypt(String x) throws Exception {
        MessageDigest d = MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return Arrays.toString(d.digest());
    }
    
}
