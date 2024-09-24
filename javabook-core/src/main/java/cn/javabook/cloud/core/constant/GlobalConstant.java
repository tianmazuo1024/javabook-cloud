package cn.javabook.cloud.core.constant;

/**
 * 全局常量
 *
 */
public abstract class GlobalConstant {
    public static final int PAGE_DEFAULT_SIZE_PER_PAGE = 20;

    // threadpool
    public static final int THREADPOOL_CORE_POOL_SIZE = 128; // 线程池维护线程的最少数量
    public static final int THREADPOOL_MAX_POOL_SIZE = 1024; // 线程池维护线程的最大数量
    public static final int THREADPOOL_KEEP_ALIVE_TIME = 30000; // 线程空闲时间（毫秒）
    public static final int THREADPOOL_QUEUE_CAPACITY = 1024; // 线程池所使用的缓冲队列

    // message queue
    public static final int MESSAGE_QUEUE_MESSAGE_HANDLE_DURATION = 480; // 消息存放超过下面设置的时间才可以取出处理
    public static final int MESSAGE_QUEUE_MESSAGE_MAX_SEND_TIMES = 5; // 消息最多发送次数
    // 消息间隔发送时间（单位：分）
    public static final int MESSAGE_QUEUE_MESSAGE_SEND_1_TIME = 0;
    public static final int MESSAGE_QUEUE_MESSAGE_SEND_2_TIME = 1;
    public static final int MESSAGE_QUEUE_MESSAGE_SEND_3_TIME = 2;
    public static final int MESSAGE_QUEUE_MESSAGE_SEND_4_TIME = 5;
    public static final int MESSAGE_QUEUE_MESSAGE_SEND_5_TIME = 10;
    // queue
    public static final String MESSAGE_QUEUE_CONSUMER_TRANSACTION_QUEUE = "CONSUMER_TRANSACTION_QUEUE";

    // guid
    public static final Long GUID_APP_MESSAGE_DATANODE = 1L;        // 消息
    public static final Long GUID_APP_TASK_DATANODE = 2L;           // 任务
    public static final Long GUID_APP_TRADE_DATANODE = 3L;          // 交易
    public static final Long GUID_APP_PAYMENT_DATANODE = 4L;        // 支付
    public static final Long GUID_APP_ACCOUNTING_DATANODE = 5L;     // 会计分录

    // mongo
    public static final String MONGO_TABLE_TRANSACTIONMESSAGE = "transactionMessage";

    // object：saved
    public static final String RIDES_PAYMENT_ORDER_SAVED_PREFIX = "payment_order_saved:";             // 保存支付订单对象
    public static final String RIDES_TRADE_SAVED_PREFIX = "trade_saved:";                             // 保存交易对象
    public static final String RIDES_ACCOUNT_BILL_SAVED_PREFIX = "account_bill_saved:";               // 保存账单对象
    public static final String RIDES_ACCOUNT_BALANCE_SAVED_PREFIX = "account_balance_saved:";         // 保存余额状态
    public static final String RIDES_ACCOUNTINT_VOUCHER_SAVED_PREFIX = "accounting_voucher_saved:";   // 保存会计分录对象

    public static final String DATE_FORMAT_DATE_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DATE_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_SECOND = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_DAY = "yyyyMMdd";
    public static final String CHAR_CODE_UTF8 = "UTF-8";
    public static final Long EXPIRE_MAX_TIMEOUT = 24 * 60 * 60L; // 最大失效时间（1天，单位秒）
}
