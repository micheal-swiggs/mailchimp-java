package com.mailchimp.domain;

/**
 * @author Ed Bras
 */
public final class JsonConstants {

    public static final String ACCOUNT_ID = "account_id";

    public static final String ACCOUNT_NAME = "account_name";

    public static final String ID = "id";

    public static final String MEMBER_RATING = "member_rating";

    public static final String EMAIL = "email_address";

    public static final String FIRST_NAME = "FNAME";

    public static final String LAST_NAME = "LNAME";

    public static final String STATUS = "status";

    public static final String STATUS_IF_NEW = "status_if_new";

    public static final String SIGNUP_DATE = "timestamp_signup";

    public static final String LAST_CHANGED = "last_changed";

    public static final String LISTS = "lists";

    public static final String LIST_ID = "list_id";

    public static final String COUNTRY_CODE = "country_code";

    public static final String MEMBERS = "members";

    public static final String MEMBER_COUNT = "member_count";

    public static final String TOTAL_ITEMS = "total_items";

    public static final String STATS = "stats";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String TAG = "tag";

    public static final String REQUIRED = "required";

    public static final String CONTACT = "contact";

    public static final String COMPANY = "company";

    public static final String ADDRESS1 = "address1";

    public static final String ADDRESS2 = "address2";

    public static final String CITY = "city";

    public static final String STATE = "state";

    public static final String ZIP = "zip";

    public static final String COUNTRY = "country";

    public static final String PHONE = "phone";

    public static final String CAMPAIGN_DETAILS = "campaign_defaults";

    public static final String FROM_NAME = "from_name";

    public static final String FROM_EMAIL = "from_email";

    public static final String SUBJECT = "subject";

    public static final String LANGUAGE = "language";

    public static final String OPERATIONS = "operations";

    public static final String METHOD = "method";

    public static final String PATH = "path";

    public static final String BODY = "body";

    public static final String PARAMS = "params";

    // Batch
    public static final String TOTAL_OPERATIONS = "total_operations";

    public static final String FINISHED_OPERATIONS = "finished_operations";

    public static final String ERRORED_OPERATIONS = "errored_operations";

    public static final String RESPONSE_BODY_URL = "response_body_url";

    public static final String SUBMITTED_AT = "submitted_at";

    public static final String COMPLETED_AT = "completed_at";

    public static final String PERMISSION_REMINDER = "permission_reminder";

    public static final String EMAIL_TYPE_OPTION = "email_type_option";

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
