package org.karane;

import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class PemExample {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024);
        KeyPair pair = gen.generateKeyPair();

        String pem = """
            -----BEGIN PUBLIC KEY-----
            %s
            -----END PUBLIC KEY-----
            """.formatted(Base64.getMimeEncoder(64, new byte[]{'\n'})
                               .encodeToString(pair.getPublic().getEncoded()));

        System.out.println(pem);
    }
}
