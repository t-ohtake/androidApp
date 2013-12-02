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

public class TwitterOauth extends Activity
{
	//アプリの認証オブジェクト作成
	RequestToken _req = null;
	//Oauth認証オブジェクト作成
	OAuthAuthorization _oauth = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//レイアウト指定
        Button btn = (Button)findViewById(R.id.attestation);//認証ボタン
        
        btn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				executeOauth();
			}
		});
    }

    private void executeOauth()
    {
    	//Twitetr4jの設定を読み込む
    	Configuration conf = ConfigurationContext.getInstance();
    	_oauth = new OAuthAuthorization(conf);

		//認証キー取得
		TwitterKeys key 		= new TwitterKeys();
		String consumerkey 		= key.getConsumerkey();
		String consumersecret 	= key.getConsumersecret();

		//Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
		_oauth.setOAuthConsumer(consumerkey, consumersecret);

		try {
			_req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		//collbackのURL取得
		String _uri;
		_uri = _req.getAuthorizationURL();
		startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
    }
}