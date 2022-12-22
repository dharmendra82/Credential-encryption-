import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class DecryptPassword {
    public static void main(String[] args) throws Exception {
        // Get the encrypted password, salt, and IV from storage
        // You can use a database or a file to retrieve these values
        byte[] encryptedPassword = ...;
        byte[] salt = ...;
        byte[] iv = ...;

        // Generate the secret key
        SecretKey secretKey = SecretKeyFactory.getInstance("AES")
            .generateSecret(new PBEKeySpec("mypassword".toCharArray(), salt, 65536, 256));

        // Create the Cipher object and initialize it for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        // Decrypt the password
        byte[] decryptedPassword = cipher.doFinal(encryptedPassword);

        // Convert the decrypted password to a string
        String password = new String(decryptedPassword);

        // Print the decrypted password
        System.out.println(password);
    }
}
