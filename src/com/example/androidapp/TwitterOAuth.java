package com.example.androidapp;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//OAuth認証クラス
public class TwitterOAuth extends Activity
{
	public static RequestToken _req = null;
	public static OAuthAuthorization _oauth = null;
	 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				executeOauth();
			}
		});
	}
	
	//認証を行い「
	private void executeOauth()
	{
		//認証キー取得
		TwitterKeys key 		= new TwitterKeys();
		String consumerkey 		= key.getConsumerkey();
		String consumersecret 	= key.getConsumersecret();
		
		//Twitetr4Jの設定を読み込む
		Configuration conf = ConfigurationContext.getInstance();
		 
		//Oauth認証オブジェクト作成
		//Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
		_oauth = new OAuthAuthorization(conf);		
		_oauth.setOAuthConsumer(consumerkey, consumersecret);
		
		//アプリの認証オブジェクト作成
		try
		{
			//OAuth用のURLを作成
			_req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
		}
		catch (TwitterException e)
		{
			e.printStackTrace();
		}
		
		//OAuth用のURLを作成
		String uri;
		uri = _req.getAuthorizationURL();
		
		//OAuth用のURLでブラウザを起動
		startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(uri)), 0);
	}
}
