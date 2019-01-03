package ro.rosmof.security;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.stream.Stream;

public class ProviderTests {

    @Test
    public void secureRandom() throws Exception {
        SecureRandom random = new SecureRandom();
        random.setSeed(new Date().getTime());

        byte[] some = new byte[64];

        for (int i = 0; i < 10; i++) {
            random.nextBytes(some);
            for (int j = 0; j < 64; j++) {
                System.out.print((int) some[j] + " - ");
            }
            System.out.println("");
        }
    }

    @Test
    public void signature() throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        PrivateKey prik = loadPrivateKey();
        PublicKey pubk = loadPublicKey();

        //signature hashing
        signature.initSign(prik);
        byte[] content = Files.readAllBytes(Paths.get("/Users/AlexandruG/Dropbox (Personal)/999. Musiv/01. various/06 America.m4a"));
        signature.update(content);
        byte[] signedContent = signature.sign();

        System.out.println("the document is signed, len=" + signedContent.length);

        //signature verification
        Signature versign = Signature.getInstance("SHA256withRSA");
        versign.initVerify(pubk);
        versign.update(content);
        if (versign.verify(signedContent)) {
            System.out.println("verification completed!");
        } else {
            System.out.println("failed to verify the signature");
        }

    }

    private RSAPrivateKey loadPrivateKey() throws Exception {
        RSAPrivateKey pk = null;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("private.pem").toURI())))) {
            StringBuffer buffer = new StringBuffer();

            boolean inKey = false;
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (!inKey) {
                    if (line.startsWith("-----BEGIN ") &&
                            line.endsWith(" PRIVATE KEY-----")) {
                        inKey = true;
                    }
                    continue;
                } else {
                    if (line.startsWith("-----END ") &&
                            line.endsWith(" PRIVATE KEY-----")) {
                        inKey = false;
                        break;
                    }
                    buffer.append(line);
                }
            }

            byte[] encoded = DatatypeConverter.parseBase64Binary(buffer.toString());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            pk = (RSAPrivateKey) kf.generatePrivate(keySpec);
        }
        return pk;
    }

    private RSAPublicKey loadPublicKey() throws Exception {
        RSAPublicKey key = null;
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(getClass().getClassLoader().getResourceAsStream("public.pem")))) {
            StringBuilder builder = new StringBuilder();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith("-----BEGIN") || line.startsWith("-----END")) {
                    continue;
                }
                builder.append(line);
            }

            byte[] converted = DatatypeConverter.parseBase64Binary(builder.toString());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(converted);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            key = (RSAPublicKey) kf.generatePublic(keySpec);
        } catch (Exception e) {
            System.out.println("something went wrong badly. " + e);
        }

        return key;
    }


    /**
     * This takes some .m4a files and creates a hex digest with RSA256 (32 bytes, 64 hex chars)
     */
    @Test
    public void messageDigest() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (Stream<Path> spath = Files.walk(Paths.get("/Users/AlexandruG/Dropbox (Personal)/999. Musiv/01. various/"))) {
            spath.filter(s -> s.toString().endsWith(".m4a")).forEach(a -> toHex(digest, a));
        }
    }

    private void toHex(MessageDigest digest, Path f) {
        try {
            digest.update(Files.readAllBytes(f));
            System.out.println("File [" + f.toString() + "] has digest [" + new String(Hex.encodeHex(digest.digest())) + "]");
        } catch (Exception e) {
            //ignore
        }
    }
}
