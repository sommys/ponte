package hu.ponte.hr.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Service class for handling signature-related operations
 */
@Service
public class SignService {
    /**
     * Path to the private key file in the resources folder
     */
    private static final String PRIVATE_KEY_PATH = "config/keys/key.private";
    /**
     * Algorithm for the key
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * Algorithm for the signature
     */
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    /**
     * @param imageData - the byte array that contains the image data to be signed
     * @return the created signature using the private key with SHA256withRSA in Base64 coding
     */
    public String createSignature(byte[] imageData) {
        try {
            // Load private key and decode it
            InputStream privateKeyFile = new ClassPathResource(PRIVATE_KEY_PATH).getInputStream();
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyFile.readAllBytes());
            PrivateKey finalKey = keyFactory.generatePrivate(keySpec);

            // Create signature for the imageData byte array
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(finalKey);
            signature.update(imageData);
            byte[] signatureData = signature.sign();

            //Return the Base64 encoded string
            return Base64.getEncoder().encodeToString(signatureData);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
