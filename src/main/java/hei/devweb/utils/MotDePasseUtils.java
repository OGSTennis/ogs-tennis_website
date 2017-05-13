package hei.devweb.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class MotDePasseUtils {
	// Algorithme de hash utilis�
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	// Taille du sel
	public static final int SALT_BYTE_SIZE = 24;
	// Taille du hash g�n�r�
	public static final int HASH_BYTE_SIZE = 24;

	// Nombre d'it�ration effectu�es par l'algorithme
	// Le but est de ralentir le traitement pour rendre compliqu� le piratage de
	// nos mots de passe par "brute force"
	public static final int PBKDF2_ITERATIONS = 20000;

	// G�n�ration du mot de passe hash�
	public static String genererMotDePasse(String motDePasse) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Cr�ation du sel
		SecureRandom random = new SecureRandom();
		byte[] sel = new byte[SALT_BYTE_SIZE];
		random.nextBytes(sel);

		// Hash du mot de passe
		byte[] hash = genererHash(motDePasse, sel);

		// format salt:hash
		return toHex(sel) + ":" + toHex(hash);
	}

	// Validation du mot de passe
	// On ne d�chiffre pas le hash, on va recalculer le hash avec le mot de
	// passe saisi et v�rifier qu'on obtient le m�me r�sultat
	public static boolean validerMotDePasse(String motDePasse, String hashCorrect) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// S�paration du hash et du sel
		String[] params = hashCorrect.split(":");
		byte[] sel = fromHex(params[0]);
		byte[] hash = fromHex(params[1]);

		// G�n�ration du hash du mot de passe test� avec le m�me sel
		byte[] hashAValider = genererHash(motDePasse, sel);
		// Comparaison des deux hash
		return Arrays.equals(hash, hashAValider);
	}

	// M�thode calculant le hash
	// C'est l� qu'est toute la s�curisation, on utilise des classes
	// javax.crypto.
	private static byte[] genererHash(String motDePasse, byte[] sel)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), sel, PBKDF2_ITERATIONS, HASH_BYTE_SIZE * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}

	// M�thode de transformation byte[] -> String
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	// M�thode de transformation String -> byte[]
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}

	// G�n�ration des mots de passe de nos utilisateurs
	public static void main(String[] args) {
		try {
			System.out.println(String.format("admin=%s", MotDePasseUtils.genererMotDePasse("admin")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
