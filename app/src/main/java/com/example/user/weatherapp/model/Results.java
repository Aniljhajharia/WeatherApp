package com.example.user.weatherapp.model;

public class Results {
    private Channel channel;

    public Channel getChannel ()
    {
        return channel;
    }

    public void setChannel (Channel channel)
    {
        this.channel = channel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [channel = "+channel+"]";
    }
}
