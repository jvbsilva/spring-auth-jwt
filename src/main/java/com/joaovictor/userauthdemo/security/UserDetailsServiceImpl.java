package com.joaovictor.userauthdemo.security;
import com.joaovictor.userauthdemo.domain.user.User;
import com.joaovictor.userauthdemo.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is called by spring security to identify the user being authenticated.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //This is the user defined in the application
        final var possibleUser = userRepository.findByEmail(username);
        if (possibleUser.isEmpty()){
            throw new UsernameNotFoundException("Email not found");
        }
        return new UserDetailsImpl(possibleUser.get());
    }
}
