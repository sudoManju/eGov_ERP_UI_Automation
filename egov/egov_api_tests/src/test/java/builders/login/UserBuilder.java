package builders.login;

import entities.login.User;

public class UserBuilder {

    User user = new User();

    public UserBuilder() {
        user.setUser_name("abcd");
        user.setEmail("abc@gmail.com");
    }

    public User build(){
        return user;
    }
}
