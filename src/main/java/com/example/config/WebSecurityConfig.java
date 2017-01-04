package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/javascript/**",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll() // indexは全ユーザーアクセス許可
                .anyRequest().authenticated();  // それ以外は全て認証無しの場合アクセス不許可

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/login")   // 認証処理のパス
                .loginPage("/index")            // ログインフォームのパス
                //.failureHandler(new SampleAuthenticationFailureHandler())       // 認証失敗時に呼ばれるハンドラクラス
                .defaultSuccessUrl("/menu")     // 認証成功時の遷移先
                .usernameParameter("login_id").passwordParameter("login_password");  // ユーザー名、パスワードのパラメータ名

        // ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
                .logoutSuccessUrl("/index");                                        // ログアウト完了時のパス

    }

//    public static class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
//
//        @Override
//        public void onAuthenticationFailure(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            AuthenticationException authenticationException)
//                throws IOException, ServletException {
//
//            String errorId = "";
//            // ExceptionからエラーIDをセットする
//            if (authenticationException instanceof BadCredentialsException) {
//                errorId = SampleErrorMessageMap.MSG_ERROR_0001;
//            }
//
//            // ログイン画面にリダイレクトする
//            response.sendRedirect(
//                    request.getContextPath() + "/" + request.getParameter("identifier") + "/index?error=" + errorId);
//        }
//    }

}
