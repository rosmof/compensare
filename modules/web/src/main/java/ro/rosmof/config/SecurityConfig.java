package ro.rosmof.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("ro.rosmof.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService visitorDetailsService;

    @Autowired
    private AuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /* path "/home/all" should be public*/
//        http.
//                authorizeRequests()
//                .antMatchers("/home/all").hasAnyRole()
//                .and()
//                .httpBasic();
//
//        /* all other paths are accessed based on authentication*/
//        http.
//                authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .httpBasic();
//
//        http
//                .requiresChannel()
//                .antMatchers("/rest/*").requiresSecure();

        /**
         * This allows x509 authentication; combined with a configuration in tomcat
         * that requests the certificate from the client.js it provides a valid model.
         *
         * The only thing is that it only works with chrome...
         * */
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .x509()
//                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
//                .userDetailsService(x509DetailsService());

    }

//    @Bean
//    @Qualifier("x509")
//    public UserDetailsService x509DetailsService() {
//        return username -> {
//            if(username.toLowerCase().contains("rosmof")){
//                System.out.println("xxxxxxxxxxx");
//                return new User(username,"", AuthorityUtils.createAuthorityList("ADMIN"));
//            } else {
//                System.out.println("yyyyyyyyyyyy");
//                throw new UsernameNotFoundException("not found");
//            }
//        };
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //auth.userDetailsService(visitorDetailsService);
//        //auth.authenticationProvider(authProvider);
//    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(7);
    }
}
