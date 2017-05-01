package net.pd.aldaaya.common;

public interface AldaayaConstants {

	int ERROR = 0;
	int OK = 1;

	int ACTIVE = 1;
	int INACTIVE = 0;
	String GENERAL_SUCCESS = "Operation done successfully";
	String GENERAL_Error = "Some thing went wrong";
	public String ERROR_MSG_ID_CAN_T_BE_NULL = "Id can't be null";
	public String ERROR_MSG_MOBILE_CAN_T_BE_NULL = "Mobile can't be null";
	public String XA_TOKEN = "xa-token";

	/**
	 * date format like 2017-01-23 18:14:41
	 */
	public String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	// public String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public int RANDOM_PASSWORD_LENGTH = 6;

	String EMAIL_SUBJECT_FORGET_PASSWORD = "نسيان كلمة المرور";
	String EMAIL_BODY_FORGET_PASSWORD = "تم اعادة تعيين لكلمة المرور الخاصة بكم لتصبح : ";
	int INBOX_TYPE = 2;
	 int OUTBOX_TYPE = 3;
}
