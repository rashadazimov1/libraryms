package com.project.libraryms.services;


import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.project.libraryms.entities.RefreshToken;
import com.project.libraryms.entities.User;
import com.project.libraryms.repos.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RefreshTokenService {

	@Value("${refresh.token.expires.in}")
	Long expireSeconds;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if(token == null) {
			token =	new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}

	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserId(userId);
	}

}
