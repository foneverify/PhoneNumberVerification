package com.u2opia.foneverify.utility;

public class ErrorCode {

	public static String errorMessage(String strErrorCode) {
		String strMessage = "";
		if (strErrorCode == "500" || strErrorCode.equalsIgnoreCase("500")) {
			strMessage = Constant.ERRORCODE_500;
		} else if (strErrorCode == "501"
				|| strErrorCode.equalsIgnoreCase("501")) {
			strMessage = Constant.ERRORCODE_501;
		} else if (strErrorCode == "502"
				|| strErrorCode.equalsIgnoreCase("502")) {
			strMessage = Constant.ERRORCODE_502;
		} else if (strErrorCode == "503"
				|| strErrorCode.equalsIgnoreCase("503")) {
			strMessage = Constant.ERRORCODE_503;
		} else if (strErrorCode == "504"
				|| strErrorCode.equalsIgnoreCase("504")) {
			strMessage = Constant.ERRORCODE_504;
		} else if (strErrorCode == "505"
				|| strErrorCode.equalsIgnoreCase("505")) {
			strMessage = Constant.ERRORCODE_505;
		} else if (strErrorCode == "506"
				|| strErrorCode.equalsIgnoreCase("506")) {
			strMessage = Constant.ERRORCODE_506;
		} else if (strErrorCode == "507"
				|| strErrorCode.equalsIgnoreCase("507")) {
			strMessage = Constant.ERRORCODE_507;
		}
		if (strErrorCode == "700" || strErrorCode.equalsIgnoreCase("700")) {
			strMessage = Constant.ERRORCODE_700;
		} else if (strErrorCode == "701"
				|| strErrorCode.equalsIgnoreCase("701")) {
			strMessage = Constant.ERRORCODE_701;
		} else if (strErrorCode == "702"
				|| strErrorCode.equalsIgnoreCase("702")) {
			strMessage = Constant.ERRORCODE_702;
		} else if (strErrorCode == "703"
				|| strErrorCode.equalsIgnoreCase("703")) {
			strMessage = Constant.ERRORCODE_703;
		} else if (strErrorCode == "704"
				|| strErrorCode.equalsIgnoreCase("704")) {
			strMessage = Constant.ERRORCODE_704;
		} else if (strErrorCode == "705"
				|| strErrorCode.equalsIgnoreCase("705")) {
			strMessage = Constant.ERRORCODE_705;
		}

		else if (strErrorCode == "706" || strErrorCode.equalsIgnoreCase("706")) {
			strMessage = Constant.ERRORCODE_706;
		} else if (strErrorCode == "707"
				|| strErrorCode.equalsIgnoreCase("707")) {
			strMessage = Constant.ERRORCODE_707;
		} else if (strErrorCode == "708"
				|| strErrorCode.equalsIgnoreCase("708")) {
			strMessage = Constant.ERRORCODE_708;
		} else if (strErrorCode == "709"
				|| strErrorCode.equalsIgnoreCase("709")) {
			strMessage = Constant.ERRORCODE_709;
		} else if (strErrorCode == "710"
				|| strErrorCode.equalsIgnoreCase("710")) {
			strMessage = Constant.ERRORCODE_710;
		} else if (strErrorCode == "711"
				|| strErrorCode.equalsIgnoreCase("711")) {
			strMessage = Constant.ERRORCODE_711;
		}

		return strMessage;
	}
}
