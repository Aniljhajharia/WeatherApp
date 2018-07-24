package com.example.user.weatherapp.model;

public class Guid {
    private String isPermaLink;

    public String getIsPermaLink ()
    {
        return isPermaLink;
    }

    public void setIsPermaLink (String isPermaLink)
    {
        this.isPermaLink = isPermaLink;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isPermaLink = "+isPermaLink+"]";
    }
}
