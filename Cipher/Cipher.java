package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {
    private int[] encryptedMessage;
	
	public Cipher(int[] data){
		this.encryptedMessage  = data;
	} 
	
	public int[] getEncryptedMessage() {
		return encryptedMessage;
	}
	
	public boolean isNotACharacter(int asciiNumber) {
		if (asciiNumber < 32 || asciiNumber > 126)
			return true;
		else
			return false;
	}

	public int[] findKey(int[] encryptedMessage) {
		int i, spaces = 0, flag = 0, maxSpaces = 0;
		int[] key = new int[3];
		int[] k = new int[3];
		
		for (k[0] = 'a'; k[0] <= 'z'; k[0]++)
			for (k[1] = 'a'; k[1] <= 'z'; k[1]++)
				for (k[2] = 'a'; k[2] <= 'z'; k[2]++) {
					spaces = 0;
					flag = 0;
					
					for (i = 0; i < encryptedMessage.length; i++) {
						if ((k[i % 3] ^ encryptedMessage[i]) == 32) {
							spaces = spaces + 1;
						}
						
						if (isNotACharacter(k[i % 3] ^ encryptedMessage[i])) {
							flag = flag - 1;
						}
					}
					if ((spaces > maxSpaces) && (flag == 0)) {
						maxSpaces = spaces;
						key[0] = k[0];
						key[1] = k[1];
						key[2] = k[2];
					}
				}
		return key;
	}

	public static void main(String[] args) {

		File file = new File("cipher.txt");
		try {
			Scanner sc = new Scanner(file);
			String str = sc.nextLine();

			String[] lineVector = str.split(",");

			int[] data = new int[lineVector.length];
			int i;
			
			for (i = 0; i < lineVector.length; i++) {
				data[i] = Integer.parseInt(lineVector[i]);
			}
	
			Cipher cp = new Cipher(data);
			
			String message = "";
			int[] key = cp.findKey(data);

			System.out.print("The Key is: ");
			for (i = 0; i < 3; i++) {
				System.out.print((char) key[i]);
			}

			System.out.println("");

			for (i = 0; i < lineVector.length; i++) {
				message = message + ((char) (key[i % 3] ^ data[i]));
			}
			
			//System.out.print(message);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}