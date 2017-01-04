package com.example.config;

import com.example.auth.UserDetailsServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    @NonNull
    final private UserDetailsServiceImpl userDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        // 認証するユーザーを設定する
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder()); // 入力値をbcryptでハッシュ化した値でパスワード認証を行う

    }

}
