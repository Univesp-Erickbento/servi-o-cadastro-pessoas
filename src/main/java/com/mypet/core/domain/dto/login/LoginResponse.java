package com.mypet.core.domain.dto.login;

public record LoginResponse(String accessToken, Long expiresIn) {
}
