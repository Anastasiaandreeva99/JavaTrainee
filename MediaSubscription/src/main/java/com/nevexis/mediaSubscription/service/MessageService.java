package com.nevexis.mediaSubscription.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nevexis.mediaSubscription.model.Message;
import com.nevexis.mediaSubscription.model.MessageSubscriber;
import com.nevexis.mediaSubscription.model.MessagePublisher;

@Service
public class MessageService {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public void registerNewSubscriber(String email) {
		MessageSubscriber newSubscriber = new MessageSubscriber(email);
		em.persist(newSubscriber);
	}

	@Transactional
	public void addSubscriber(Long subscriberId, Long publisherId) {
		MessagePublisher publisher = em.find(MessagePublisher.class, publisherId);
		MessageSubscriber subscriber = em.find(MessageSubscriber.class, subscriberId);
		publisher.subscribe(subscriber);
		subscriber.addPublisher(publisher);
		em.merge(publisher);
		em.merge(subscriber);

	}

	public void sentNewMessage(Long publisherId, Message message) {
		MessagePublisher publisher = em.find(MessagePublisher.class, publisherId);
		publisher.publish(message);
	}

	@Transactional
	public void addPublisher() {
		MessagePublisher publisher = new MessagePublisher();
		em.persist(publisher);
	}
}
