package com.paw.trello.security;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class SecurityKeyGenerator {

    public Key generateKey() {
        String keyString = "security-key-string";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }

}
