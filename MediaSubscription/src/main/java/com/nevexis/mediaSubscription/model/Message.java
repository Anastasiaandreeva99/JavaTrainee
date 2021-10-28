package com.nevexis.mediaSubscription.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Message {

	private String from;
	private String header;
	private String about;
	private String mainText;
	private Timestamp date;

	public static class MessageBuilder {
		private String mainText;
		private String from = "";
		private String header = "";
		private String about = "";
		private Timestamp date = new Timestamp(System.currentTimeMillis());

		public MessageBuilder(String mainText) {
			this.mainText = mainText;
		}

		public MessageBuilder from(String from) {
			this.from = from;
			return this;
		}

		public MessageBuilder header(String header) {
			this.header = header;
			return this;
		}

		public MessageBuilder about(String about) {
			this.about = about;
			return this;
		}

		public MessageBuilder date(Timestamp date) {
			this.date = date;
			return this;
		}

		public Message build() {
			return new Message(this);
		}

	}

	private Message(MessageBuilder builder) {
		mainText = builder.mainText;
		from = builder.from;
		header = builder.header;
		about = builder.about;
		date = builder.date;
	}

	public String getFrom() {
		return from;
	}

	public String getHeader() {
		return header;
	}

	public String getAbout() {
		return about;
	}

	public String getMainText() {
		return mainText;
	}

	public Timestamp getDate() {
		return date;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Message from:");
		result.append(from);
		result.append("Header:");
		result.append(header);
		result.append("about");
		result.append(about);
		result.append("mainText");
		result.append(mainText);
		return result.toString();
	}

}
