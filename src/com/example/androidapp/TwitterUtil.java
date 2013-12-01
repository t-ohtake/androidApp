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
    	
		// �C���X�^���X�̏�����  
		twitter = TwitterFactory.getSingleton();  
		
		// Twitter�ɓo�^�����A�v����Consumer key �� Consumer secret��ݒ�  
		twitter.setOAuthConsumer(consumer_key, consumer_secret); 
		  
		// RequestToken�g�[�N���v��  
		// �F�؃y�[�W��URL��Ԃ��̂�WebView�ŕ\�������[�U�[�Ƀ��O�C�����Ă��炤  
		requestToken = twitter.getOAuthRequestToken();
		
		return requestToken.getAuthorizationURL();  
	}
	    
	// Access�g�[�N���擾�B������Web�ŕ\�����ꂽPIN������B  
	// �F�؍ς݂�Twitter�C���X�^���X��Ԃ�  
	public Twitter getAccessToken(String pin) throws TwitterException
	{
		// RequestToken��PIN���Access�g�[�N���擾  
		AccessToken _accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		return twitter;  
	} 
}
