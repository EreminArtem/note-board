package ru.eremin.noteboard;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.eremin.noteboard.entity.RoleType;
import ru.eremin.noteboard.service.api.IUserService;

/**
 * @autor Artem Eremin on 26.12.2018.
 */

@Component
public class Bootstrap implements InitializingBean {

    private BCryptPasswordEncoder passwordEncoder;
    private IUserService userService;

    @Autowired
    public Bootstrap(final BCryptPasswordEncoder passwordEncoder, final IUserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.initUser("admin", passwordEncoder.encode("pass"), "admin@email", RoleType.USER);
    }
}
