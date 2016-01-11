package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Explanation and usage of the possible values: <a href="http://kb.mailchimp.com/api/article/how-to-manage-subscribers">Mail Chimp status usage</a>
 * @author Ed Bras
 */
public enum SubscribeStatus {

    //subscribed, unsubscribed, pending, cleaned;
    Subscribed("subscribed"),

	/**
	 * Used to archive a subscriber.
	 */
    UnSubscribed("unsubscribed"),

	/**
	 * Used for double opt-in. The subscriber will receive an email to confirm his subscription.<br>
	 * Subscribing a subscriber with the same email address, will result in an http 400 error indicating that the user already exists. As such, he will
	 * not receive a confirmation email again.
	 */
    Pending("pending"),

	/**
	 * Used to archive a subscriber.
	 */
    Cleaned("cleaned");

	//private static final SubscribeStatus[] ACTIVE = new SubscribeStatus[] { Pending, Subscribed };

	//private static final SubscribeStatus[] ARCHIVED = new SubscribeStatus[] { Cleaned, UnSubscribed };

    private String value;

    SubscribeStatus(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

	//public static SubscribeStatus[] getActive() {
		//return ACTIVE;
	//}

	//public static SubscribeStatus[] getArchived() {
		//return ARCHIVED;
	//}

	/**
	 * @return true if this status is an active status (Pending or Subscribed)
	 */
	//public boolean isActive() {
		//return contains(ACTIVE);
	//}

	/**
	 * @return true if this status is an archived status (Pending or Subscribed)
	 */
	//public boolean isArchived() {
		//return contains(ARCHIVED);
	//}

	//public boolean isSubscribed() {
		//return equals(Subscribed);
	//}

	//public boolean isUnSubscribed() {
		//return equals(UnSubscribed);
	//}

	//public boolean isPending() {
		//return equals(Pending);
	//}

	//public boolean isCleaned() {
		//return equals(Cleaned);
	//}

	//@Override
	//public String toString() {
		//return getValue();
	//}

	////
	////

	/**
	 * @return true if the specified status name is contained in the array of status names.
	 */
	//private boolean contains(final SubscribeStatus[] statusNames) {
		//for (final SubscribeStatus sts : statusNames) {
			//if (equals(sts)) {
				//return true;
			//}
		//}
		//return false;
	//}

}
