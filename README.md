

This is a Java wrapper around the MailChimp API.

##### Example code for using the client

    MailChimpClient mailChimpClient = MailChimpClient.builder()
      .withApiBase("us1")
      .withBasicAuthentication("abc")
      .build();
    List subscriberList = mailChimpClient.getList("123");

## Maven / Gradle

This library can be included from Maven, e.g using gradle:

    dependencies {
        compile 'com.github.micheal-swiggs:mailchimp-java:1.0.0'
    }


##### Installing

    ./gradlew install
