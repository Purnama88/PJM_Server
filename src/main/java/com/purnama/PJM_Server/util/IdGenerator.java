/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.util;

import java.util.Date;

/**
 *
 * @author p_cor
 */
public class IdGenerator {
    
    public static String generateId(){
        return String.valueOf(new Date().getTime());
    }
    
}
