package kimxu.nn.model;

import cn.bmob.v3.BmobObject;

/**
 * 用户类
 */
public class Account extends BmobObject{
    public static String USERNAME="username";
    public static String PASSWORD="password";
    public static String PARTNER="partner";
    private String username;
    private String partner;
    private String password;

    public Account(String username, String password,String partner) {
        this.username = username;
        this.password = password;
        this.partner=partner;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
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
