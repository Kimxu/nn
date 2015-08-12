package kimxu.nn.model;

import cn.bmob.v3.BmobObject;

/**
 * 错误日志
 */
public class Logger extends BmobObject{

    public static final String ACTION="action";
    public static final String MESSAGE="message";
    public static final String EVENT_ID="eventId";
    //事件错误类型
    private String action;
    //事件错误信息
    private String message;
    //事件id
    private String eventId;

    public Logger(String action, String message,String eventId) {
        this.action = action;
        this.message = message;
        this.eventId=eventId;
    }

    public String getAction() {
        return action;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
