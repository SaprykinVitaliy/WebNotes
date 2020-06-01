/*
package ru.saprykin.vitaliy.webnotes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saprykin.vitaliy.webnotes.DAO.DBAuthAgent;
import ru.saprykin.vitaliy.webnotes.Model.User;

@Service
public class UserService {
    @Autowired
    private DBAuthAgent dbAuthAgent;

    @Autowired
    private UserService userService;

    public void save(User user){
        dbAuthAgent.setPassword(user.getLogin(), user.getPassword());
    }

    public User findByUsername(String username){
        //TODO
        //return dbAuthAgent.findUserByUsername(username);
        User user = new User("login", "password");
        return user;
        */
/*user.setLogin(login);
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");*//*

    }
}
*/
