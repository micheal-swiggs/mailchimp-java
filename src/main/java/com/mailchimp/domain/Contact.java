package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contact
{
    @JsonProperty(value = JsonConstants.COMPANY)
    private String company;

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    @JsonProperty(value = JsonConstants.ADDRESS1)
    private String address1;

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    @JsonProperty(value = JsonConstants.ADDRESS2)
    private String address2;

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    @JsonProperty(value = JsonConstants.CITY)
    private String city;

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @JsonProperty(value = JsonConstants.STATE)
    private String state;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @JsonProperty(value = JsonConstants.ZIP)
    private String zip;

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    @JsonProperty(value = JsonConstants.COUNTRY)
    private String country;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @JsonProperty(value = JsonConstants.PHONE)
    private String phone;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
