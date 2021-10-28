package com.nevexis.mediaSubscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.mediaSubscription.model.Message.MessageBuilder;
import com.nevexis.mediaSubscription.service.MessageService;

@RestController
public class Controller {
	@Autowired
	MessageService messageService;
	
	@PostMapping(value = "/subscribeNewUser")
	public void registerNewUser(@RequestParam String email)
	{
		messageService.registerNewSubscriber(email);
	}
	@PostMapping(value = "/subscription")
	public void addSubscriber(@RequestParam Long subscriberId,@RequestParam Long publisherId)
	{
		
		messageService.addSubscriber(subscriberId,publisherId);
	}
	@PostMapping(value = "/publish")
	public void sentMessage(@RequestParam Long publisherId,@RequestParam String from,@RequestParam String header,@RequestParam String mainText)
	{
		MessageBuilder builder = new MessageBuilder(mainText);
		builder.from(from);
		builder.header(header);		
		messageService.sentNewMessage(publisherId,builder.build());
		
	}
	@PostMapping(value = "/addPublisher")
	public void addPublisher()
	{
		
		messageService.addPublisher();
	}
}
