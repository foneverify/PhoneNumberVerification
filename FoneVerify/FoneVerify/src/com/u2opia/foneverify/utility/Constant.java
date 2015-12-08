package com.u2opia.foneverify.utility;

public class Constant {

	public static final String OTPVERIFY_URL_NEW = "http://apifv.foneverify.com/U2opia_Verify/v1.0/flow/update";
	public static final String OTP_URL_NEW = "http://apifv.foneverify.com/U2opia_Verify/v1.0/flow/sms";
	//replace below values with your app key and custoemr id
	public static final String APP_SECRET = "XXXXXXXXXXXXXXX";
	public static final String CUSTOMER_ID_NEW = "XXXXXXXXXXXXX";
	
	//replace below value with your country code
	public static final String COUNTRY_CODE_NEW = "XX";

	public static final String CUSTOMER_ID_PREFERENCE = "customerIdPreference";
	public static final String CUSTOMER_ID = "customerId";
	public static final String MSISDN_PREFERENCE = "msisdnPreference";
	public static final String MSISDN = "msisdn";
	public static final String APPKEY_PREFERENCE = "appKeyPreference";
	public static final String APPKEY = "appKey";
	public static final String COUNTRY_CODE_PREFERENCE = "countryCodePreference";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String VERIFICATION_ID_PREFERENCE = "verificationIdPreference";
	public static final String VERIFICATION_ID = "verificationId";
	public static final String OTP_URL_PREFERANCE = "otpUrlPreference";
	public static final String OTP_URL = "otpUrl";
	public static final String OTPVERIFY_URL_PREFERANCE = "otpVerifyUrlPreference";
	public static final String OTPVERIFY_URL = "otpVerifyUrl";	
	public static final String DID_NUMBER_PREFERANCE = "didNumberPreference";
	public static final String DID_NUMBER = "didNumber";
	

	// Error messages
	public static final String ERRORCODE_500 = "Unknown Response. Please Try again later.";
	public static final String ERRORCODE_501 = "Invalid Customer id";
	public static final String ERRORCODE_502 = "Invalid app key";
	public static final String ERRORCODE_503 = "Invalid Country Code - Please enter the correct ISO Country Code and Try Again";
	public static final String ERRORCODE_504 = "Invalid Number - Please enter the correct Number and Try Again";
	public static final String ERRORCODE_505 = "Invalid verification id";
	public static final String ERRORCODE_506 = "Request already exists";
	public static final String ERRORCODE_700 = "Verification Failed";
	public static final String ERRORCODE_701 = "Trying fallback";
	public static final String ERRORCODE_702 = "Wrong OTP - Please enter the correct OTP";
	public static final String ERRORCODE_703 = "This mobile number already verified";
	public static final String ERRORCODE_704 = "This is not valid request";

	// New error code added as per today requirement date 2nd sep 15
	public static final String ERRORCODE_507 = "No active DID found";
	public static final String ERRORCODE_705 = "Trying fallback sms delivered";// "TRYING_FALLBACK_SMS_DELIVERED";
	public static final String ERRORCODE_706 = "TRYING_FALLBACK_SMS_NOT_DELIVERED";
	public static final String ERRORCODE_707 = "SMS_DELIVERED_SUCCESSFULLY";
	public static final String ERRORCODE_708 = "SMS_DELIVERY_REPORT_PENDING";
	public static final String ERRORCODE_709 = "WRONG_DID_PROVIDED";
	public static final String ERRORCODE_710 = "DID_VERIFICATION_PENDING";
	public static final String ERRORCODE_711 = "COUNTRY_NOT_SUPPORTED_OTP_SENT";

}
