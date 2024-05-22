package com.ssafy.tripmate.user.service;

import com.ssafy.tripmate.config.security.jwt.TokenProvider;
import com.ssafy.tripmate.global.RefreshTokenValidationException;
import com.ssafy.tripmate.user.dto.RefreshToken;
import com.ssafy.tripmate.user.dto.TokenDto;
import com.ssafy.tripmate.user.dto.User;
import com.ssafy.tripmate.user.dto.request.LoginRequest;
import com.ssafy.tripmate.user.dto.request.SignUpRequest;
import com.ssafy.tripmate.user.dto.request.TokenRequest;
import com.ssafy.tripmate.user.dto.response.AfterLoginResponse;
import com.ssafy.tripmate.user.dto.response.SignStatus;
import com.ssafy.tripmate.user.repository.RefreshTokenRepository;
import com.ssafy.tripmate.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public UserService(UserRepository userRepository,
                       RefreshTokenRepository refreshTokenRepository,
                       TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public AfterLoginResponse signUpMember(SignUpRequest request) {
        User member = userRepository.save(memberMapper.dtoToMemberEntity(request));
        TokenDto tokenDto = tokenProvider.makeToken(member);
        saveRefreshToken(member.getId().toString(),tokenDto);

        return new AfterLoginResponse(SignStatus.SIGNUP,tokenDto);
    }

    @Transactional
    public AfterLoginResponse login(LoginRequest request) {
        User member = userRepository.findByIdAndPassword(request.getEmail(),request.getPassword()).orElseThrow(EntityNotFoundException::new);
        TokenDto tokenDto = tokenProvider.makeToken(member);
        saveRefreshToken(member.getId().toString(),tokenDto);

        return new AfterLoginResponse(SignStatus.SIGNIN,tokenDto);
    }

    private void saveRefreshToken(String userName,TokenDto token) {
        refreshTokenRepository.save(new RefreshToken(userName,token.getRefreshToken()));
    }

    public TokenDto renewalAccessToken(TokenRequest request) {
        final String refreshToken = request.getRefreshToken();

        if(!tokenProvider.validateToken(refreshToken)) {
            throw new RefreshTokenValidationException();
        }

        Optional<Authentication> authentication =
                tokenProvider.getAuthentication(request.getAccessToken());

        return authentication.map(auth -> new TokenDto(makeAccessToken(auth,refreshToken),refreshToken)).orElseThrow(AuthorityException::new);
    }

    public String makeAccessToken(final Authentication auth, final String refreshToken) {
        String userInfo = auth.getName();
        if(!getRefreshTokenValue(userInfo).equals(refreshToken)) {
            throw new RefreshTokenInfoMismatchException();
        }

        return tokenProvider.createAccessToken(auth);
    }

    public String getRefreshTokenValue(String tokenKey) {
        return refreshTokenRepository
                .findByTokenKey(tokenKey)
                .orElseThrow(EntityNotFoundException::new)
                .getTokenValue();
    }

    public void logout() {
        MemberResponse curMember = memberService.getMemberDtoByJwt();
        refreshTokenRepository.deleteById(curMember.getMemberId());
    }

}
