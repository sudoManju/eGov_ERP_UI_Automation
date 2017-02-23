package builders.marriageRegistration;

import entities.marriageRegistration.NewLoginInfo;

public class NewLoginInfoBuilder extends NewLoginInfo {
    NewLoginInfo objec2 = new NewLoginInfo();

    public NewLoginInfoBuilder(){

    }
    public NewLoginInfoBuilder withusername(String username)
    {
        objec2.setUsername(username);
        return this;
    }

    public NewLoginInfoBuilder withpassword(String password){
        objec2.setPassword(password);
        return this;
    }

    public NewLoginInfo build(){
        return objec2;
    }
}
