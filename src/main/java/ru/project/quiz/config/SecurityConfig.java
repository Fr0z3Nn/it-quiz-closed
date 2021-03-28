package ru.project.quiz.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.project.quiz.service.Impl.UserServiceImpl;
import ru.project.quiz.service.UserService;


@Configuration
@AllArgsConstructor
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserServiceImpl userServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();
                /*.antMatcher("/swagger-ui.html").authorizeRequests()
                .anyRequest().permitAll();*/
        /*http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .cors().disable()
                .csrf().disable();
        http.headers().frameOptions().disable();*/
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userServiceImpl);
    }
}
