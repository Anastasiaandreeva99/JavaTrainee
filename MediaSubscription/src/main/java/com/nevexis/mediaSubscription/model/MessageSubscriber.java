package com.nevexis.mediaSubscription.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class MessageSubscriber implements Subscriber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	
	@ManyToMany
	private List<MessagePublisher> publishers;

	public MessageSubscriber() {
	}

	public MessageSubscriber(String email) {
		this.email = email;
	}

	@Override
	public void update(Message m) {
		System.out.println(m.toString() + "sent to:" + email);
	}

   public void addPublisher(MessagePublisher publisher)
   {
	   if(null==publishers)publishers = new ArrayList<>();
		if (!publishers.contains(publisher)) {
			publishers.add(publisher);
		}
   }
}
