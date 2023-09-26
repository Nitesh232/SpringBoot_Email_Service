package com.nitesh.springboot.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nitesh.springboot.Utils.EmailUtils;
import com.nitesh.springboot.service.EmailService;

import lombok.RequiredArgsConstructor;



/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * @author Nitesh H. Mishra * 
 * @version 1.0
 * @license Nitesh H. Mishra
 * @since 16/09/2023
 */


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	
	private final JavaMailSender emailSender;
	
	
	@Value("${spring.mail.username}")
	private String fromMail;

	
	
	
	/**
     * This method will send compose and send the message
     * */
	
	@Override
	@Async
	public void sendSimpleMailMessage(String to, String subject, String name) {
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setFrom(fromMail);
	        message.setText(EmailUtils.getEmailVerificataionMessage(name));
	        emailSender.send(message);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
		
	}





}
