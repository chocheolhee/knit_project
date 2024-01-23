package com.toy.knit.service;

import com.toy.knit.config.PasswordEncoderConfig;
import com.toy.knit.entity.Member;
import com.toy.knit.repository.MemberRepository;
import com.toy.knit.request.auth.Login;
import com.toy.knit.request.auth.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public void signup(Signup signup) {
        Optional<Member> findMember = memberRepository.findByEmail(signup.getEmail());

        if (findMember.isPresent()) {
            throw new IllegalArgumentException("이미 회원가입된 이메일 입니다.");
        }

        PasswordEncoderConfig encoder = new PasswordEncoderConfig();

        String encryptedPassword = encoder.encodePassword(signup.getPassword());

        Member member = Member.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .name(signup.getName())
                .createdAt(ZonedDateTime.now())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public Long signIn(Login login) {
        Member member = memberRepository.findByEmail(login.getEmail()).orElseThrow(IllegalAccessError::new);

        PasswordEncoderConfig encoder = new PasswordEncoderConfig();

        var matches = encoder.matches(login.getPassword(), member.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member.getId();
    }

}
