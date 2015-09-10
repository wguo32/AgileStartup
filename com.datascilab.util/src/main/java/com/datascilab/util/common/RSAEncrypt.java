package com.datascilab.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

public class RSAEncrypt {

	private Logger log = LoggerFactory.getLogger(RSAEncrypt.class);

	public static final String ALGORITHM_RSA = "RSA";

	public static final String PRIVATE_KEY_FILE = "id_rsa";

	public static final String PUBLIC_KEY_FILE = "id_rsa.pub";

	private static class RSAEncryptHolder {

		private static final RSAEncrypt INSTANCE = new RSAEncrypt();
	}

	public static RSAEncrypt getInstance() {
		return RSAEncryptHolder.INSTANCE;
	}

	private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSbvYlwWtfIi29cQ0UH20ePckx"
			+ "\r"
			+ "qzk80t83J5L811H5mWfZ7bUYAXcchTmoYaDSntLaW9XOK85ROL8nPT9DV/rXtdpg"
			+ "\r"
			+ "ezfwACcsY+0WniVkQOhVn5Q1wDQV7gSb42zIwCIRNG8xgR1CuVQBlfSZesek8bsm"
			+ "\r" + "bSwx2xBzS+saNF2vjQIDAQAB" + "\r";

	private static final String DEFAULT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANJu9iXBa18iLb1x"
			+ "\r"
			+ "DRQfbR49yTGrOTzS3zcnkvzXUfmZZ9nttRgBdxyFOahhoNKe0tpb1c4rzlE4vyc9"
			+ "\r"
			+ "P0NX+te12mB7N/AAJyxj7RaeJWRA6FWflDXANBXuBJvjbMjAIhE0bzGBHUK5VAGV"
			+ "\r"
			+ "9Jl6x6TxuyZtLDHbEHNL6xo0Xa+NAgMBAAECgYAWBGgasS8TyBazEIYGyfb3yA0p"
			+ "\r"
			+ "9jlJ5OJX+fsGG11HWUalZZqiBV06DbJnrLdP8jArippHNQwkMWTGrUDWQflLsffK"
			+ "\r"
			+ "WNfck/Kt/zOTtuhPNPE63T/msogv1u8lnaz0Xw8h67YzagtcEEAElwvgRkcwQKT4"
			+ "\r"
			+ "eVirfgpkUSDN8Vay/QJBAOqnG49yI4zi6Djwtepa2Lv+7X6csmVz0q5WIaDTS2ve"
			+ "\r"
			+ "zxCyUbkXTMs1CE5wSkKd2E+7cR0jQpzdd3GNu7Lc1/MCQQDlk82whAANmKS35pPo"
			+ "\r"
			+ "GGtwle9VxBsjicdB9Z2o1h5QNjrA1c8CWPzZ/q+Kxoc2/Rcx/Pp7HPOfMo9Npd6O"
			+ "\r"
			+ "eLp/AkAoi49bjeFOqrZtgEouoFvqv/XRdsnsdcLllMRiwZ+6l9k3jVhJnxkJ07QH"
			+ "\r"
			+ "uadbM2L5A+5bloggCrRkzpSsJzS5AkALSkyITMWP5ZzcKmvFs0W9YLJHfoBzK/hv"
			+ "\r"
			+ "pvuc7QLKoRAcoacvj17H/b12TrMhoiq825sZ6N+WSa9Ae1LJoOhTAkEAiObRugFy"
			+ "\r"
			+ "SKfXu8vNfgNYoWVFfcVrpDIqBKID9m0WXYHObcldHhZl1JyNadlkMxqN2jElzXIO"
			+ "\r" + "RQSs08+kLcLgbA==" + "\r";

	private RSAPrivateKey privateKey;

	private RSAPublicKey publicKey;

	public RSAEncrypt() {
		super();
		// init keys
		try {
			loadPrivateKey(DEFAULT_PRIVATE_KEY);
			loadPublicKey(DEFAULT_PUBLIC_KEY);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}

	}

	public String getClassRootPath() {
		return this.getClass().getResource("/").getPath();
	}

	public String getPublicKeyPath() {
		return this.getClassRootPath() + "/" + PUBLIC_KEY_FILE;
	}

	public String getPrivateKeyPath() {
		return this.getClassRootPath() + "/" + PRIVATE_KEY_FILE;
	}

	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public void genKeyPair() {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
		this.publicKey = (RSAPublicKey) keyPair.getPublic();
	}

	public void loadPublicKey(InputStream in) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPublicKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	public void loadPublicKey(String publicKeyStr) throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (IOException e) {
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	public void loadPrivateKey(InputStream in) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPrivateKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	public void loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			this.privateKey = (RSAPrivateKey) keyFactory
					.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	public byte[] encryptNative(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	public byte[] decryptNative(RSAPrivateKey privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 字节数据转十六进制字符串
	 * 
	 * @param data
	 *            输入数据
	 * @return 十六进制内容
	 */
	public static String byteArrayToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			// 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
			stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
			// 取出字节的低四位 作为索引得到相应的十六进制标识符
			stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
			if (i < data.length - 1) {
				stringBuilder.append(' ');
			}
		}
		return stringBuilder.toString();
	}

	public String decrypt(String input) {
		String output = null;
		byte[] decodedByte = java.util.Base64.getDecoder().decode(input);
		try {
			output = new String(decryptNative(getPrivateKey(), decodedByte));
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return output;
	}

	public String encrypt(String input) {
		String output = null;
		try {
			output = java.util.Base64.getEncoder().encodeToString(
					encryptNative(getPublicKey(), input.getBytes()));
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return output;
	}

	public static void main(String[] args) {
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
		// rsaEncrypt.genKeyPair();

		// 加载公钥
		try {
			rsaEncrypt.loadPublicKey(RSAEncrypt.DEFAULT_PUBLIC_KEY);
			System.out.println("加载公钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载公钥失败");
		}

		// 加载私钥
		try {
			rsaEncrypt.loadPrivateKey(RSAEncrypt.DEFAULT_PRIVATE_KEY);
			System.out.println("加载私钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载私钥失败");
		}

		// 测试字符串
		String encryptStr = "1q2w3e";

		try {
			// 加密
			byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(),
					encryptStr.getBytes());
			// 解密
			byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(),
					cipher);
			System.out.println("密文长度:" + cipher.length);
			System.out.println(RSAEncrypt.byteArrayToString(cipher));
			System.out.println(cipher);
			System.out.println("明文长度:" + plainText.length);
			System.out.println(RSAEncrypt.byteArrayToString(plainText));
			System.out.println(new String(plainText));
			String encrpted = "SR8M4R6TfWASUQX75FIA8RzfC5fEn+4StDXkzXWe3u/inkFeBL6r2zv0QZG6HenVN9yV/mHQVlxgND8lNnn7p2cpW9byJ4sjawGyUxwVELlll79KQkaHcVt6BxcXc1kdXi76vlo85473iOhT3gczydoa/e867XHTMBqIvSVf2Vw=";
			//
			System.out.println(RSAEncrypt.getInstance().decrypt(encrpted));
			//
			String enc = RSAEncrypt.getInstance().encrypt("1q2w3e");
			System.out.println(enc);
			System.out.println(RSAEncrypt.getInstance().decrypt(enc));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
