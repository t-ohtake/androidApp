package com.example.androidapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterUtil
{	    
	private Twitter twitter; 
	private RequestToken requestToken;  
	    
	public String requestToken(String consumer_key,String consumer_secret) throws TwitterException
	{  
    	//this.CONSUMER_KEY = getPropertyVal("consumerKey");
    	//this.CONSUMER_SECRET= getPropertyVal("consumerSecret");
    	
		// インスタンスの初期化  
		twitter = TwitterFactory.getSingleton();  
		
		// Twitterに登録したアプリのConsumer key と Consumer secretを設定  
		twitter.setOAuthConsumer(consumer_key, consumer_secret); 
		  
		// RequestTokenトークン要求  
		// 認証ページのURLを返すのでWebViewで表示しユーザーにログインしてもらう  
		requestToken = twitter.getOAuthRequestToken();
		
		return requestToken.getAuthorizationURL();  
	}
	    
	// Accessトークン取得。引数にWebで表示されたPINを入れる。  
	// 認証済みのTwitterインスタンスを返す  
	public Twitter getAccessToken(String pin) throws TwitterException
	{
		// RequestTokenとPINよりAccessトークン取得  
		AccessToken _accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		return twitter;  
	} 
}
