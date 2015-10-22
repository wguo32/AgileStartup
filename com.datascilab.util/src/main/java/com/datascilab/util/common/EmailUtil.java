package com.datascilab.util.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class EmailUtil {


	static Logger log = LoggerFactory.getLogger(EmailUtil.class);

	Properties p;

	private static class EmailUtilHolder {

		private static EmailUtil instance = new EmailUtil();
	}

	public static EmailUtil getInstance() {

		return EmailUtilHolder.instance;

	}

	public String host;

	public int port;

	public String user;

	public String pass;

	public String from;

	public String to;

	EmailUtil() {

		super();
		init();
	}

	private void init() {

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("email.properties");

		p = new Properties();

		try {
			p.load(inputStream);
		} catch (IOException e1) {
			log.warn(e1.getMessage() + "  " + e1.getCause());
		}

		host = get("host");
		port = Integer.parseInt(get("port"));
		user = get("user");
		pass = get("passwd");
		from = get("from");
		to = get("to");
	}

	public void sendByDefualt(String subject, String msg) {

		sendByDefualt(getToArray(), subject, msg);
	}

	public void sendByDefualt(String[] toArray, String subject, String msg) {

		Email email;
		if (p == null) {
			log.warn("basic email.properties conf loaded failed ");
			return;
		}
		try {
			email = new SimpleEmail();
			email.setHostName(host);
			email.setSmtpPort(port);
			email.setAuthenticator(new DefaultAuthenticator(user, pass));
			email.setSSLOnConnect(false);
			email.setFrom(from);

			email.addTo(toArray);
			email.setSubject(subject);
			email.setMsg(msg);
			email.send();
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
	}

	public String[] getToArray() {

		String[] toArray = null;
		if (!Strings.isNullOrEmpty(to)) {
			toArray = to.split(",");
		}

		return toArray;
	}

	public String get(String key) {

		return p.getProperty(key);
	}

	public static void main(String[] args) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.163.com");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator(
					"15110002001@163.com", "dfqhbngaphhvmuok"));
			email.setSSLOnConnect(false);

			email.setFrom("15110002001@163.com");
			email.setSubject("TestMail");
			email.setMsg("This is a test mail ... :-)");
			email.addTo("guow@suiyii.com", "vicre@126.com", "vicre@qq.com",
					"vicre@live.com");

			// email.send();
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		
		EmailUtil.getInstance().sendByDefualt("Chunzhen-监控-水木-二手房", "Detail:\n1.test\n2.test\n----Finished-----");
		

	}

}