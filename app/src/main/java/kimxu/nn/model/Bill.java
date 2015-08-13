package kimxu.nn.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by xuzhiguo on 15/8/13.
 */
public class Bill extends BmobObject{
    public static final String ACTION ="action";
    public static final String MONEY ="money";
    public static final String USERNAME ="username";
    public static final String PARTNER ="partner";

    private String action;
    private String money;
    private String username;
    private String partner;

    public Bill(String action, String money, String username) {
        this.action = action;
        this.money = money;
        this.username = username;

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
}
