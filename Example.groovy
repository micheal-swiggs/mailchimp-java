package com.mailchimp

class Example {

    public static void main(def args) {
        String listId = "EXISTING-LIST-ID"
        String apiKey = "API-KEY";
        String apiBase = "API-BASE e.g us12";
        MailChimpClient mailChimp = MailChimpFactory.create(apiKey, apiBase);

        println mailChimp.getMembersList(listId)
        println mailChimp.getPagedListMembers(listId,0,1)
    }
}
