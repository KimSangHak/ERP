package com.yuhannci.erp.service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataCryptoService {
	
	// 2017-05-08 :: IV 를 암호화된 데이터 끝에 붙여서 저장하도록 함
	
	final static Encoder base64Encoder = Base64.getEncoder();
	final static Decoder base64Decoder = Base64.getDecoder();
	
	final static String encryptionPassword = "OOPSMYBABYSUHYUN";	// KEY
	
	final String transformationName = "AES/CBC/PKCS5PADDING";
	final String algorithmName = "AES";
	final int initialVectorSize = 16;
	
	String bytesToHex(byte[] src){
		StringBuilder sb = new StringBuilder(src.length * 2);
		for(byte b : src)
			sb.append(String.format("%02X", b));
		return sb.toString();
	}
	String bytesToHex(byte[] src, int offset ,int len){
		StringBuilder sb = new StringBuilder(src.length * 2);
		for(int i=0;i<len;i++)
			sb.append(String.format("%02X", src[i + offset]));
		return sb.toString();
	}
	
    public String encrypt(String value) {
        try {                        
            SecretKeySpec skeySpec = new SecretKeySpec(encryptionPassword.getBytes("UTF-8"), algorithmName);
            byte[] seed = SecureRandom.getSeed(initialVectorSize); 
        	IvParameterSpec iv = new IvParameterSpec(seed);
        	
            Cipher cipher = Cipher.getInstance(transformationName);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            byte[] resultIv = cipher.getIV();
            if(resultIv.length != initialVectorSize)
            	throw new AssertionError("IV size = " + resultIv.length);
                        
            byte[] src = new byte[encrypted.length + resultIv.length];
            System.arraycopy(resultIv, 0, src, 0, resultIv.length);           
            System.arraycopy(encrypted, 0, src, resultIv.length, encrypted.length);
                                    
            String encoded = base64Encoder.encodeToString(src);
            //log.debug("Encrypted [" + value + "] to " + encrypted.length + "+" + resultIv.length + " bytes ==> " + encoded);
            //log.debug("raw iv = " + bytesToHex(resultIv) + ", data = " + bytesToHex(encrypted));
            
            return encoded;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String decrypt(String encrypted) {
        try {
        	byte[] token = base64Decoder.decode(encrypted);
        	//log.debug("origin[" + token.length + "/" + encrypted + "] to " + (token.length - initialVectorSize) + " + " + initialVectorSize );
            //log.debug("decrypt raw iv = " + bytesToHex(token, 0, initialVectorSize) + ", data = " + bytesToHex(token, initialVectorSize, token.length - initialVectorSize));
        	
            IvParameterSpec iv = new IvParameterSpec(token, 0, initialVectorSize);
            
            SecretKeySpec skeySpec = new SecretKeySpec(encryptionPassword.getBytes("UTF-8"), algorithmName);

            Cipher cipher = Cipher.getInstance(transformationName);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(token, initialVectorSize, token.length - initialVectorSize);
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

	// Object 는 JSON 으로 serialzing 해서 변환시킨다
	public <T extends Object> String encrypt(T obj){
		ObjectMapper om = new ObjectMapper();
		String serialized = null;
		try {
			serialized = om.writeValueAsString(obj);			
			return encrypt(serialized);
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return null;
	}
	
	public <T extends Object> T decrypt(String enc, Class<T> t){
		ObjectMapper om = new ObjectMapper();
		try {
			String data = decrypt(enc);
			return om.readValue(data, t);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
