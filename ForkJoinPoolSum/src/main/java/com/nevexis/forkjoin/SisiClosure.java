package com.nevexis.forkjoin;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SisiClosure {

	private static final Logger LOGGER = Logger.getLogger(SisiClosure.class.getName());

	@FunctionalInterface
	interface MessageInterface {
		String message(String to);
	}

	@FunctionalInterface
	interface LogInterface {
		void log(String logMessage);
	}

	public static MessageInterface getMessage() {
		String from = "sisi";
		return ((MessageInterface) (t) -> {
			return MessageFormat.format("This message is sent from {0} to {1}.", from, t);
		});
	}

	public static LogInterface logMessage() {
		String loggerName = "Sisi's logger";
		return (s) -> {
			LOGGER.log(Level.INFO, MessageFormat.format("{0} : {1}", loggerName,s));
		};

	}

	public static void main(String args[]) {
		System.out.println(getMessage().message("Rally"));
		logMessage().log("some info");
	}
}