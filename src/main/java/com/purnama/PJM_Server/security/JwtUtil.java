/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 *
 * @author p_cor
 */
public class JwtUtil {
    
    private static final String secret = "purnomojayamobil";
    
    
    public static int parseToken(String token){
        try{
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            
            int userid = (int)body.get("user");
            
            return userid;
        }
        catch(JwtException | ClassCastException e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public static int parseToken2(String token){
        try{
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            
            int warehouseid = (int)body.get("warehouse");
            
            return warehouseid;
        }
        catch(JwtException | ClassCastException e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public static String generateToken(int userid, int warehouseid){
        
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        
        Claims  claims = Jwts.claims().setSubject("PJM Session");
        claims.put("user", userid);
        claims.put("warehouse", warehouseid);
        
        //if it has been specified, let's add the expiration
//        if (ttlMillis >= 0) {
        long expMillis = nowMillis + 1000 * 60 * 5 /*5 minutes*/;
        Date exp = new Date(expMillis);
//        }

        
        return Jwts.builder()
                .setIssuer("PJM")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
