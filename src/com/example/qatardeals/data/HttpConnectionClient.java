package com.example.qatardeals.data;

import java.io.IOException;
import java.net.CookieStore;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLPeerUnverifiedException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.net.ParseException;
import android.util.Log;

public class HttpConnectionClient {
	
    private static final String BASE_URL = "http://talenweave.com/qatardeals2/index.php?route=common/api";
	  
	  private static AsyncHttpClient client = new AsyncHttpClient();
	  
	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  client.get(getAbsoluteUrl(url), params, responseHandler);
	      Log.v("called", getAbsoluteUrl(url));
	  }
	  
	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }
	  
	  private static String getAbsoluteUrl(String relativeUrl) {
	      return BASE_URL + relativeUrl;
	  }
	  
	  public static void get(String url, RequestParams params, Activity activity, AsyncHttpResponseHandler responseHandler) {
		  client.get(getAbsoluteUrl(url), params, responseHandler);
	      Log.v("called", getAbsoluteUrl(url));
	  }
	  


}
