package com.gitlab.esenbogagnu.urmsweb.service;

import com.gitlab.esenbogagnu.urmsweb.domain.Department;
import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Service
public class EmailService {

	@Value("${site.address}")
	private String address;

	@Autowired
	private JavaMailSender mailSender;

	private ITemplateResolver htmlResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setOrder(Integer.valueOf(3));
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	private TemplateEngine templateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlResolver());
		return templateEngine;
	}

	public void sendRiskAsMail(Risk risk) throws MessagingException {

		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

		Context context = new Context();
		context.setVariable("risk", risk);
		context.setVariable("address", address);
		String content = templateEngine().process("templates/mail/riskmail.html", context);
		List<String> to = new ArrayList<>();
		for (Department d : risk.getDepartments()) {
			to.add(d.getEmail());
		}
		messageHelper.setText(content, true);
		messageHelper.setTo(Arrays.copyOf(to.toArray(), to.toArray().length, String[].class));
		messageHelper.setFrom("urmsuite@gmail.com");
		messageHelper.setSubject("URMS - Notification About Risk Report: " + risk.getId());

		mailSender.send(mimeMessage);
	}
}
