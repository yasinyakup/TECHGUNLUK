package com.yaytech.techgunluk.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yaytech.techgunluk.dto.RegisterRequest;
import com.yaytech.techgunluk.exception.TechGunlukException;
import com.yaytech.techgunluk.model.NotificationEmail;
import com.yaytech.techgunluk.model.User;
import com.yaytech.techgunluk.model.VerificationToken;
import com.yaytech.techgunluk.repository.UserRepository;
import com.yaytech.techgunluk.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;
import static java.time.Instant.now;

import java.util.Optional;
import java.util.UUID;
import static com.yaytech.techgunluk.utils.Constants.ACTIVATION_EMAIL;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor

public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailContentBuilder MailContentBuilder;
	private final MailService mailService;
	
	@Transactional
	public void signUp(RegisterRequest registerRequest) {
		// save register info into DB
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setCreated(now());
		user.setEnabled(false);
		
		userRepository.save(user);
		
		String token = generateToken(user);
		String message = MailContentBuilder.build("Thank you for signing up to TechGunluk, please click on the below url to activate your account: "
				+ACTIVATION_EMAIL+"/"+token);
		mailService.sendMail(new NotificationEmail("Please activate your account"
				,user.getEmail(), message));
	}
	
	public String generateToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		
		verificationTokenRepository.save(verificationToken);
		
		return token;

	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(()->new TechGunlukException("Invalid token"));
		
		fetchUserAndEnable(verificationToken.get());
		
	}

	private void fetchUserAndEnable(VerificationToken verificationToken) {
		
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(
				()-> new TechGunlukException("User not fount with id - "+username));
		user.setEnabled(true);
		userRepository.save(user);
		
	}
}
