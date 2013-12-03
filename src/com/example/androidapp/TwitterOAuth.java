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
	//アプリの認証オブジェクト、Oauth認証オブジェクト作成
	public static RequestToken _req = null;
	public static OAuthAuthorization _oauth = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//レイアウト指定
        Button btn = (Button)findViewById(R.id.attestation);//認証ボタン
        
        //認証ボタン押下時の動作
        btn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				executeOauth();
			}
		});
    }
    
    //ボタン押下時に呼出し
    private void executeOauth()
    {
    	//Twitetr4jの設定を読み込む
    	Configuration conf = ConfigurationContext.getInstance();
    	_oauth = new OAuthAuthorization(conf);

    	//Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
		TwitterKeys key 		= new TwitterKeys();	
		_oauth.setOAuthConsumer(key.getConsumerkey(), key.getConsumersecret());

		try
		{
			//コールバックURLを指定
			_req = _oauth.getOAuthRequestToken("com.example.androidapp://TwitterOauth");
		}
		catch (TwitterException e)
		{
			e.printStackTrace();
		}
		
		//uri取得
		String uri;
		uri = _req.getAuthorizationURL();
		
		startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(uri)), 0);
		
		//画面遷移
		Intent intent = new Intent(TwitterOauth.this, CallBackActivity.class);
        startActivity(intent);
    }
}