package kimxu.nn.log;


import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import kimxu.nn.model.Logger;
import kimxu.nn.utils.logger.Klog;

/**
 *  记录日志
 */
public class SmartLogger{

    /**
     * 缓存队列的最大容量
     */
    private static final int CACHE_QUEUE_MAX_SIZE = 5;
    /**
     * 用于缓存LogHelper的队列，这样可以保证较为频繁的记录日志的时候能够创建最少的LogHelper对象，最佳效果就是至始至终只有一个LogHelper
     */
    private static final Queue<LogHelper> logHelperCacheQueue = new LinkedList<LogHelper>();

    /**
     * 开启事务
     * @return
     */
    public static LogHelper beginTransaction(){
        LogHelper logHelper;
        synchronized (logHelperCacheQueue) {
            //Retrieves and removes the head of this queue
            logHelper = logHelperCacheQueue.poll();
        }
        if(logHelper == null){
            logHelper = new LogHelper();
        }
        return logHelper;
    }

    private static void recycleLogHelper(LogHelper logHelper){
        logHelper.reset();
        // 如果缓存队列没有满才将当前的放进去，此举是为了防止缓存池中存在过多的LogHelper却得不到释放
        synchronized (logHelperCacheQueue) {
            if(logHelperCacheQueue.size() < CACHE_QUEUE_MAX_SIZE){
                logHelperCacheQueue.offer(logHelper);
            }
        }
    }







    public static  class LogHelper{
       private DashBoardLogHelper dashBoardLogHelper;
        private UmengLogHelper umengLogHelper;

        private LogHelper(){

        }

        /**
         * 重置
         */
        private void reset(){
            if(dashBoardLogHelper != null){
                dashBoardLogHelper.reset();
            }
            if(umengLogHelper != null){
                umengLogHelper.reset();
            }
        }
        /**
         * 设置友盟日志
         * @param umengEventId 友盟事件ID
         */
        public UmengLogHelper umeng(String umengEventId){
            if(umengEventId == null || "".equals(umengEventId.trim())){
                throw new IllegalArgumentException("param umengEventId is null or empty");
            }
            if(umengLogHelper == null){
                umengLogHelper = new UmengLogHelper(this);
            }
            return umengLogHelper.eventId(umengEventId);
        }
        /**
         * 设置友盟日志
         * @param dashId 个人事件ID
         */
        public DashBoardLogHelper dashBoard(String dashId){
            if(TextUtils.isEmpty(dashId)){
                throw new IllegalArgumentException("param dashId is null or empty");
            }
            if(dashBoardLogHelper == null){
                dashBoardLogHelper = new DashBoardLogHelper(this);
            }
            return dashBoardLogHelper.eventId(dashId);
        }

        /**
         * 写出数据
         * @param context 上下文
         */
        public void commit(Context context){
            if(dashBoardLogHelper != null){
                dashBoardLogHelper.executeWrite(context);
            }
            if(umengLogHelper != null){
                umengLogHelper.executeWrite(context);
            }
            // 回收LogHelper
            SmartLogger.recycleLogHelper(this);
        }
        /**
         * 添加公共参数
         * @param key 参数键
         * @param value 参数值
         */
        public LogHelper addCommonParam(String key, String value){
            if(dashBoardLogHelper != null && dashBoardLogHelper.eventId != null){
                dashBoardLogHelper.addDashBoardParam(key, value);
            }

            if(umengLogHelper != null && umengLogHelper.eventId != null){
                umengLogHelper.addUmengParam(key, value);
            }
            return this;
        }




    }

    public static class DashBoardLogHelper{
        private LogHelper logHelper;
        private String eventId;
        private Map<String, String> params;
        DashBoardLogHelper(LogHelper logHelper){
            this.logHelper=logHelper;
        }

        void reset(){
            this.eventId=null;
            if (params!=null)
                params.clear();

        }


        /**
         * 添加参数
         * @param key 参数键
         * @param value 参数值
         */
        public DashBoardLogHelper addDashBoardParam(String key, String value){
            if(TextUtils.isEmpty(key)){
                throw new NullPointerException("（"+eventId+"）param key is null or empty");
            }
            if(params == null){
                params = new HashMap<String, String>();
            }
            params.put(key, value);
            return this;
        }
        /**
         * 设置事件ID
         * @param eventId 事件ID
         */
        DashBoardLogHelper eventId(String eventId){
            this.eventId = eventId;
            return this;
        }
        void executeWrite(Context context){
            if(eventId == null){
                return;
            }
            if (params != null) {
                String action =params.get(Logger.ACTION);
                String msg =params.get(Logger.MESSAGE);
                 new Logger(action,msg,eventId).save(context, null);
             }
            Klog.d("DashBoard：" + eventId + (params != null ? params.toString() : ""));

        }
        /**
         * 设置友盟日志
         * @param umengEventId 友盟事件ID
         */
        public UmengLogHelper umeng(String umengEventId){
                return logHelper.umeng(umengEventId);
        }
        /**
         * 写日志
         * @param context 上下文
         */
        public void commit(Context context){
            logHelper.commit(context);
        }


    }

    public static class UmengLogHelper{
        private LogHelper logHelper;
        private String eventId;
        private Map<String, String> params;

        public UmengLogHelper(LogHelper logHelper){
            this.logHelper = logHelper;
        }

        /**
         * 返回
         */
        public LogHelper backToLogHelper(){
            return logHelper;
        }

        /**
         * 重置
         */
        void reset(){
            this.eventId = null;
            if(params != null){
                params.clear();
            }
        }

        /**
         * 设置事件ID
         * @param eventId 事件ID
         */
        UmengLogHelper eventId(String eventId){
            this.eventId = eventId;
            return this;
        }

        /**
         * 添加参数
         * @param key 参数键
         * @param value 参数值
         */
        public UmengLogHelper addUmengParam(String key, String value){
            if(key == null || "".equals(key.trim())){
                throw new NullPointerException("（"+eventId+"）param key is null or empty");
            }
            if(params == null){
                params = new HashMap<String, String>();
            }
            params.put(key, value);
            return this;
        }

        /**
         * 设置位置，key默认为list_item_position
         * @param position 位置
         */
        public UmengLogHelper position(int position){
            int tempPosition = position;
			/* ****这端代码是从UMengConstant抄过来的，不知道为何大于500的时候变成501，暂时保留吧**** */
            if(tempPosition > 500){
                tempPosition = 501;
            }
			/* ****这端代码是从UMengConstant抄过来的，不知道为何大于500的时候变成501，暂时保留吧**** */
            addUmengParam("list_item_position", String.valueOf(tempPosition));
            return this;
        }

        void executeWrite(Context context){
            if(eventId == null){
                return;
            }

            if (params != null) {
                MobclickAgent.onEvent(context, eventId, params);
            } else {
                MobclickAgent.onEvent(context, eventId);
            }
                Klog.d("UMENG：" + eventId + (params != null ? params.toString() : ""));

        }

        /**
         * 设置DashBoard日志
         * @param dashBoardLogId DashBoard日志ID
         */
        public DashBoardLogHelper dashBoard(String dashBoardLogId){
            return logHelper.dashBoard(dashBoardLogId);
        }

        /**
         * 添加公共参数
         * @param key 参数键
         * @param value 参数值
         */
        public LogHelper addCommonParam(String key, String value){
            return logHelper.addCommonParam(key, value);
        }

        /**
         * 写日志
         * @param context 上下文
         */
        public void commit(Context context){
            logHelper.commit(context);
        }
    }


    /**
     *  SmartLogger.beginTransaction().umeng("refresh").addCommonParam("123", "123").
     *  dashBoard("111").addDashBoardParam(Logger.ACTION, "333").addDashBoardParam(Logger.MESSAGE,"333").
     *  commit(mActivity);

     */

}