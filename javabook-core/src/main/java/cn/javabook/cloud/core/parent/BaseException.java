package cn.javabook.cloud.core.parent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常
 * 
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = -3276316806344164226L;
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final BaseException EXCEPTION_PUBLIC_DB_INSERT_FAILED = new BaseException(10100, "data insert exception"); // 数据插入异常
	public static final BaseException EXCEPTION_PUBLIC_DB_QUERY_FAILED = new BaseException(10101, "data query exception"); // 数据查询异常
	public static final BaseException EXCEPTION_PUBLIC_DB_UPDATE_FAILED = new BaseException(10102, "data update exception"); // 数据更新异常
	public static final BaseException EXCEPTION_PUBLIC_DB_DELETE_FAILED = new BaseException(10103, "data delete exception"); // 数据删除异常

	protected int errorcode; // 具体异常码
	protected String errormsg; // 异常信息

	public BaseException(int errorcode, String errormsg, Object... args) {
		super(String.format(errorcode + ", " + errormsg, args));
		this.errorcode = errorcode;
		this.errormsg = String.format(errormsg, args);
	}

	public BaseException() {
		super();
	}

	public BaseException(String errormsg, Throwable cause) {
		super(errormsg, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String errormsg) {
		super(errormsg);
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
}
