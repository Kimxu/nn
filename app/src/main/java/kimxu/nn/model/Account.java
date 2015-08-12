package kimxu.nn.model;

import cn.bmob.v3.BmobObject;

/**
 * 用户类
 */
public class Account extends BmobObject{
    public static String USERNAME="username";
    public static String PASSWORD="password";
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
