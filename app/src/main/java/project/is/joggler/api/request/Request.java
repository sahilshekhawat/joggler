package project.is.joggler.api.request;

import android.os.Bundle;

import project.is.joggler.api.ApiConfig;
import project.is.joggler.api.response.Response;

import org.codehaus.jackson.JsonParseException;

import java.io.IOException;
import java.io.InputStream;


public abstract class Request
{
    public static final String API_ENDPOINT = ApiConfig.DEV_API ? ApiConfig.API_ENDPOINT_DEVELOPMENT : ApiConfig.API_ENDPOINT_PRODUCTION;
    public static final String CHARSET = "UTF-8";
    public static final String BOUNDARY = "0xKhTmLbOuNdArY";

    private Bundle mMetaData = null;

    public abstract String getRequestMethod();
    public abstract String getAddress();
    public abstract Response<?> parseResponse(InputStream stream) throws IOException, JsonParseException;


    public byte[] getContent()
    {
        return null;
    }


    public String getBasicAuthUsername()
    {
        return null;
    }


    public String getBasicAuthPassword()
    {
        return null;
    }


    public boolean isMultipart()
    {
        return false;
    }


    public Bundle getMetaData()
    {
        return mMetaData;
    }


    public void setMetaData(Bundle metaData)
    {
        mMetaData = metaData;
    }
}
