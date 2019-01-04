package ro.rosmof.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("visitorDetailsService")
public class VisitorUserDetailsService implements UserDetailsService {

    private static List<User> userList = new ArrayList<>();

    @Autowired
    public VisitorUserDetailsService(PasswordEncoder pencoder) {
        userList.add(new User("admin", pencoder.encode("abcd"), Collections.singleton(new SimpleGrantedAuthority("ADMIN"))));
        userList.add(new User("user", pencoder.encode("abcd"), Collections.singleton(new SimpleGrantedAuthority("USER"))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> galist = new ArrayList<>();
        galist.add(new SimpleGrantedAuthority("ADMIN"));

        Optional<User> found = userList.stream().filter(u -> u.getUsername().equals(username)).findAny();
        if (!found.isPresent()) {
            System.out.println("no such used");
            throw new UsernameNotFoundException("Failed to find user");
        }

        return new VisitorDetails(found.get().getUsername(), found.get().getPassword(), found.get().getAuthorities());
    }
}
