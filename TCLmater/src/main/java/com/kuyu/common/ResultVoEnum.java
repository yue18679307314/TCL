package com.kuyu.common;

public enum ResultVoEnum{

	REFUSED_THE_BINDING_USER(CommonConstants.REFUSED_THE_BINDING_USER_MSG,CommonConstants.REFUSED_THE_BINDING_USER_CODE),
	SELECT_IS_NULL(CommonConstants.SELECT_IS_NULL_MSG,CommonConstants.SELECT_IS_NULL_CODE),
	AUDIT_DAY_IS_OUT_OF_DATE(CommonConstants.AUDIT_DAY_IS_OUT_OF_DATE_MSG,CommonConstants.AUDIT_DAY_IS_OUT_OF_DATE_CODE),
	ONLY_ALLOW_ONE(CommonConstants.ONLY_ALLOW_ONE_MSG, CommonConstants.ONLY_ALLOW_ONE_CODE),
	HAVE_ACTIVITY(CommonConstants.HAVE_ACTIVITY_MSG, CommonConstants.HAVE_ACTIVITY_CODE),
	WORK_TIME_ERROR(CommonConstants.WORK_TIME_ERROR_MSG, CommonConstants.WORK_TIME_ERROR_CODE),
	AUDIT_USER_FAIL(CommonConstants.AUDIT_USER_FAIL_MSG,CommonConstants.AUDIT_USER_FAIL_CODE),
	OPEN_CITY_TOO_LONG(CommonConstants.OPEN_CITY_TOO_LONG_MSG, CommonConstants.OPEN_CITY_TOO_LONG_CODE),
	OPEN_BRANCH_TOO_LONG(CommonConstants.OPEN_BRANCH_TOO_LONG_MSG,CommonConstants.OPEN_BRANCH_TOO_LONG_CODE),
	NAME_TOO_LONG(CommonConstants.NAME_TOO_LONG_MSG,CommonConstants.NAME_TOO_LONG_CODE),
	ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY(CommonConstants.ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY_MSG,CommonConstants.ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY_CODE),
	REJECT_USER_NOT_PASSED(CommonConstants.REJECT_USER_NOT_PASSED_MSG,CommonConstants.REJECT_USER_NOT_PASSED_CODE),
	REJECT_USER_FAIL(CommonConstants.REJECT_USER_FAIL_MSG,CommonConstants.REJECT_USER_FAIL_CODE),
	ACCOUNTNAME_NOT_EQUAL_NAME(CommonConstants.ACCOUNTNAME_NOT_EQUAL_NAME_MSG,CommonConstants.ACCOUNTNAME_NOT_EQUAL_NAME_CODE),
	JOIN_ACTIVITY_DAY_OVER(CommonConstants.JOIN_ACTIVITY_DAY_OVER_MSG,CommonConstants.JOIN_ACTIVITY_DAY_OVER_CODE),
	TOO_MANY_ACTIVITY_REQUESTS(CommonConstants.TOO_MANY_ACTIVITY_REQUESTS_MSG,CommonConstants.TOO_MANY_ACTIVITY_REQUESTS_CODE),
	JOIN_ACTIVITY_DAY_WRONG(CommonConstants.JOIN_ACTIVITY_DAY_WRONG_MSG,CommonConstants.JOIN_ACTIVITY_DAY_WRONG_CODE),
	ATTENDENCE_IS_EXIST(CommonConstants.ATTENDENCE_IS_EXIST_MSG,CommonConstants.ATTENDENCE_IS_EXIST_CODE),
	SETTLEMENT_DONE(CommonConstants.SETTLEMENT_DONE_MSG,CommonConstants.SETTLEMENT_DONE_CODE),
	ACCOUNT_NOT_FOUND(CommonConstants.ACCOUNT_VALUE_NOT_FOUND_MSG,CommonConstants.ACCOUNT_VALUE_NOT_FOUND_CODE),
	MOBILE_TOO_SHORT(CommonConstants.MOBILE_TOO_SHORT_MSG,CommonConstants.MOBILE_TOO_SHORT_CODE),
	ADDRESS_TOO_LONG(CommonConstants.ADDRESS_TOO_LONG_MSG,CommonConstants.ADDRESS_TOO_LONG_CODE),
	PERSON_NULL(CommonConstants.PERSON_NULL_MSG,CommonConstants.PERSON_NULL_CODE),
	FINANCIAL_NULL(CommonConstants.FINANCIAL_NULL_MSG,CommonConstants.FINANCIAL_NULL_CODE),
	AUDIT__PASS(CommonConstants.AUDIT__PASS_MSG,CommonConstants.AUDIT__PASS_CODE),
	PERSON_NUM_OVER(CommonConstants.PERSON_NUM_OVER_MSG,CommonConstants.PERSON_NUM_OVER_CODE),
	REWARD_OVER_PREDICTION(CommonConstants.REWARD_OVER_PREDICTION_MSG,CommonConstants.REWARD_OVER_PREDICTION_CODE),
	ADJUST_MONEY_NULL(CommonConstants.ADJUST_MONEY_NULL_MSG,CommonConstants.ADJUST_MONEY_NULL_CODE),
	MODIFIY_NOT_FOUND(CommonConstants.MODIFIY_NOT_FOUND_MSG,CommonConstants.MODIFIY_NOT_FOUND_CODE),
	FSSC_BILL_CODE(CommonConstants.FSSC_BILL_MSG,CommonConstants.FSSC_BILL_CODE),
	SETTLEMENT_IS_NULL(CommonConstants.SETTLEMENT_IS_NULL_MSG,CommonConstants.SETTLEMENT_IS_NULL_CODE),
	CHECK_STATUS(CommonConstants.CHECK_STATUS_MSG,CommonConstants.CHECK_STATUS_CODE),
	VALID_WORK_HOURS_NULL(CommonConstants.VALID_WORK_HOURS_NULL_MSG,CommonConstants.VALID_WORK_HOURS_NULL_CODE),
	USERNAME_OR_PASSWORD_ISNULL(CommonConstants.USERNAME_OR_PASSWORD_ISNULL_MSG,CommonConstants.USERNAME_OR_PASSWORD_ISNULL_CODE),
	USERNAME_OR_PASSWORD_ERROR(CommonConstants.USERNAME_OR_PASSWORD_ERROR_MSG,CommonConstants.USERNAME_OR_PASSWORD_ERROR_CODE),
	ACTIVITY_TIME_IS_NULL(CommonConstants.ACTIVITY_TIME_IS_NULL_MSG,CommonConstants.ACTIVITY_TIME_IS_NULL_CODE),
	URL_IS_NULL(CommonConstants.URL_IS_NULL_MSG,CommonConstants.URL_IS_NULL_CODE),
	AUDIT_NO_PASS(CommonConstants.AUDIT_NO_PASS_MSG,CommonConstants.AUDIT_NO_PASS_CODE),
	NOT_FOUNT_404(CommonConstants.NOT_FOUNT_404_MSG,CommonConstants.NOT_FOUNT_404_CODE),
	NOT_LOGIN(CommonConstants.NOT_LOGIN_MSG,CommonConstants.NOT_LOGIN_CODE),
	STATEMENT_NOT_EXIST(CommonConstants.STATEMENT_NOT_EXIST_MSG,CommonConstants.STATEMENT_NOT_EXIST_CODE),
	REQUEST_STATUS(CommonConstants.REQUEST_STATUS_MSG,CommonConstants.REQUEST_STATUS_CODE),
	HOUR_STATE2(CommonConstants.HOUR_STATE2_MSG,CommonConstants.HOUR_STATE2_CODE),
	HOUR_STATE0(CommonConstants.HOUR_STATE0_MSG,CommonConstants.HOUR_STATE0_CODE),
	PERSON_CODE_NOT_FOUND(CommonConstants.PERSONCODE_NOT_FOUND_MSG,CommonConstants.PERSONCODE_NOT_FOUND_CODE),
	OPENID_NOT_FOUND(CommonConstants.OPENID_NOT_FOUND_MSG,CommonConstants.OPENID_NOT_FOUND_CODE),
	CANNOT_SIGN_IN(CommonConstants.CANNOT_SIGN_IN_MSG,CommonConstants.CANNOT_SIGN_IN_CODE),
	ACTIVITY_NOT_FOUND(CommonConstants.ACTIVITY_NOT_FOUND_MSG,CommonConstants.ACTIVITY_NOT_FOUND_CODE),
	ERROR(CommonConstants.SYSTEM_ERROR_MSG,CommonConstants.SYSTEM_ERROR_CODE),
	FAIL(CommonConstants.FAIL_MSG,CommonConstants.FAIL_CODE),
	PAGE_ERROR(CommonConstants.PAGE_ERROR_MSG,CommonConstants.PAGE_ERROR_CODE),
	VALID_CODE_ERROR(CommonConstants.VALID_CODE_ERROR_MSG,CommonConstants.VALID_CODE_ERROR_CODE),
	VALID_CODE_OVER_TIME(CommonConstants.VALID_CODE_OVER_TIME_MSG,CommonConstants.VALID_CODE_OVER_TIME),
	WEIXIN_BINGIN_ECHO(CommonConstants.WEIXIN_BINGIN_ECHO_MSG,CommonConstants.WEIXIN_BINGIN_ECHO_CODE),
	USER_BINGIN_ECHO(CommonConstants.USER_BINGIN_ECHO_MSG,CommonConstants.USER_BINGIN_ECHO_CODE),
	UNBINDING_FAIL(CommonConstants.UNBINDING_FAIL_MSG,CommonConstants.UNBINDING_FAIL_CODE),
	PARAM_IS_ERROR(CommonConstants.PARAM_IS_ERROR_MSG,CommonConstants.PARAM_IS_ERROR_CODE),
	ACTIVITY_END(CommonConstants.ACTIVITY_IS_ENDING_MSG,CommonConstants.ACTIVITY_IS_ENDING_CODE),
	NOT_PERMISSION(CommonConstants.NOT_PERMISSION_MSG,CommonConstants.NOT_PERMISSION_CODE),
	ACTIVITY_NO_ERROR(CommonConstants.ACTIVITY_NO_ERROR_MSG,CommonConstants.ACTIVITY_NO_ERROR_CODE),
	ACTIVITY_AUDIT_REASON_NOT_EMPTY(CommonConstants.ACTIVITY_AUDIT_REASON_NOT_EMPTY_MSG,CommonConstants.ACTIVITY_AUDIT_REASON_NOT_EMPTY_CODE),
	ACTIVITY_NOT_EXISTS(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG,CommonConstants.ACTIVITY_DATA_NOT_EXISTS_CODE),
	ACTIVITY_IS_JOIN(CommonConstants.ACTIVITY_DATA_IS_JOIN_MSG,CommonConstants.ACTIVITY_DATA_IS_JOIN_CODE),
	ACTIVITY_FINISH(CommonConstants.ACTIVITY_RECRUIT_FINISH_MSG,CommonConstants.ACTIVITY_RECRUIT_FINISH_CODE),
	NOT_INFORMATION(CommonConstants.NOT_INFORMATION_MSG,CommonConstants.NOT_INFORMATION_CODE);

	String value = "";
	String name = "";

	private ResultVoEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getNameByKey(String value) {
		for (ResultVoEnum spe : ResultVoEnum.values()) {
			if (spe.getValue() .equals (value)) {
				return spe.getName();
			}
		}
		return "";
	}
}
