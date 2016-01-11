package com.mailchimp;


/**
 * @author Ed Bras
 */
public final class JsonConstants {

    public static final String ID = "id";

    public static final String MEMBER_RATING = "member_rating";

	public static final String EMAIL = "email_address";

	public static final String FIRST_NAME = "FNAME";

	public static final String LAST_NAME = "LNAME";

	public static final String STATUS = "status";

    public static final String STATUS_IF_NEW = "status_if_new";

	public static final String MERGE_VARS = "merge_fields";

	public static final String SIGNUP_DATE = "timestamp_signup";

    public static final String LAST_CHANGED = "last_changed";

    public static final String LIST_ID = "list_id";

    public static final String COUNTRY_CODE = "country_code";

    public static final String MEMBERS = "members";

    public static final String MEMBER_COUNT = "member_count";

    public static final String TOTAL_ITEMS = "total_items";

    public static final String STATS = "stats";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String TAG = "tag";

    public static final String MERGE_FIELDS = "merge_fields";


	private static final String TITLE = "title";

	private static final String DETAIL = "detail";


	private JsonConstants() {
	}

	public static String getEmail() {
		return EMAIL;
	}

	public static String getFirstName() {
		return FIRST_NAME;
	}

	public static String getLastName() {
		return LAST_NAME;
	}

	public static String getStatus() {
		return STATUS;
	}

	public static String getMergeVars() {
		return MERGE_VARS;
	}

	public static String getSignupDate() {
		return SIGNUP_DATE;
	}

	public static String getTitle() {
		return TITLE;
	}

	public static String getDetail() {
		return DETAIL;
	}

}
