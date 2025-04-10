/******************************************************************************

                            AES-256 encrypt and decrypt.
                It takes a text entered by keyboard and encrypts it in AES-256. 
                It then returns it to its original state. 
                This is a simple example of how to encrypt and decrypt text.
                Author: Joanmacat

*******************************************************************************/

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AES256EncryptDecrypt {

	// void main
	public static void main (String[] arguments) throws Exception {

		// We enter the main text to encrypt.
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the string to encrypt");
		String originalText = scanner.nextLine();

		// Generate the AES-256 key to encrypt. Param: (Integer) keySize
		SecretKey aesKey = generateAESKey(256);

		// Encrypt the text. Param: (string) originalText
		String encryptedText = encryptAES(originalText, aesKey);
		System.out.println("Encrypted Text: " + encryptedText);

		// Decrypt the text
		String decryptedText = decryptAES(encryptedText, aesKey);
		System.out.println("Decrypted Text: " + decryptedText);

	}

	// GenerateAESKey static SecretKey
	public static SecretKey generateAESKey(int keySize) throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keySize);
		return keyGen.generateKey();
	}

	// EncryptAES static SecretKey
	public static String encryptAES(String data, SecretKey aesKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encryptedBytes = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	// DecryptAES static Secret Key

	public static String decryptAES(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
	}
}