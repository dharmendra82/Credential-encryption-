import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptPassword {
    public static void main(String[] args) throws Exception {
        // Generate a unique subscript (salt)
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Get the password to encrypt
        String password = "mypassword";

        // Generate a secret key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        // Create the Cipher object and initialize it for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Get the initialization vector (IV)
        byte[] iv = cipher.getIV();

        // Encrypt the password
        byte[] encryptedPassword = cipher.doFinal(password.getBytes());

        // Store the encrypted password, salt, and IV for later use
        // You can use a database or a file to store these values
        // ...
    }
}
