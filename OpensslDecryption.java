import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;


public class OpensslDecryption {
    public static void main(String[] args) throws IOException, InterruptedException {
        String encryptedValue = "U2FsdGVkX1/cZB5bCZFSEtA4HkwUfuEqOj9kXojbK9s=";
        String password="pass!3234542";
        String pass = decrypt(encryptedValue,password);
    }

    private static String decrypt(String encryptedValue, String password) throws IOException, InterruptedException {

// decrypted_string=$(echo "$encrypted_string" | openssl enc -aes-256-cbc -d -k "$password" -pbkdf2 -base64)
        // Create the ProcessBuilder object and specify the command and its arguments
        String command = String.format("echo %s | openssl enc -aes-256-cbc -d -k %s -pbkdf2 -base64 > test.txt", encryptedValue, password);
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        pb.inheritIO();

        // Start the process and get the Process object
        Process p = pb.start();
        int exitCode = p.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String encryptedString ;

        while ((encryptedString = reader.readLine()) != null ){
            System.out.println("Encrypted string: " + encryptedString);
        }

        reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        encryptedString =null;

        while ((encryptedString = reader.readLine()) != null ){
            System.out.println("error: " + encryptedString);
        }
        // Wait for the process to finish and get the exit code

        // Read the output from the process

        // Print the encrypted string

        System.out.println("Exit code: " + exitCode);


        reader = new BufferedReader(new FileReader("test.txt"));

        // Read the first line from the file
        String line = reader.readLine();

        // Print the line
        System.out.println(line);
        return line;
    }
}
