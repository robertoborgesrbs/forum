package br.com.alura.forum.service.infra;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.alura.forum.infra.NewReplyMailFactory;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;

@Service
public class ForumMailService {

	private static final Logger logger = LoggerFactory
			.getLogger(ForumMailService.class);
	
//	@Autowired
//	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private NewReplyMailFactory newReplyMailFactory;
	
	@Async
	public void sendNewReplyMailAsync(Answer answer) {
		Topic answeredTopic = answer.getTopic();
		
//		SimpleMailMessage message = new SimpleMailMessage();
		MimeMessagePreparator messagePreparator = (mimeMessage) -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			
			messageHelper.setTo(answeredTopic.getOwnerEmail());
			messageHelper.setSubject("Novo comentario em: " + answeredTopic.getShortDescription());
			
			String messageContent = this.newReplyMailFactory
					.generateNewReplyMailContent(answer);
			
			messageHelper.setText(messageContent, true);
		};
//		
//		message.setTo(answeredTopic.getOwnerEmail());
//		message.setSubject("Novo comentario em: " + answeredTopic.getShortDescription());
//		
//		message.setText("Ola " + answeredTopic.getOwnerEmail() + "\n\n" +
//				"Ha uma nova mensagem no forum! " + answeredTopic.getShortDescription() +
//				"comentou no topico: " + answeredTopic.getShortDescription());
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			logger.error("Nao foi possivel enviar email para " + answer.getTopic()
					.getOwnerEmail(),e.getMessage());
		}
	}
}
