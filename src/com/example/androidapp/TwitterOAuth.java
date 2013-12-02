package com.example.androidapp;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.OAuthProvider;
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

		//認証キー取得
		TwitterKeys key 		= new TwitterKeys();
		String consumerkey 		= key.getConsumerkey();
		String consumersecret 	= key.getConsumersecret();

		//Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
		_oauth.setOAuthConsumer(consumerkey, consumersecret);

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
    
/*いらん
    //web画面遷移時に実行
    @Override
    protected void onResume()
    {
        super.onResume();
     
        if (_req != null) {
            //認証処理に入っている場合はレイアウトを変更
            setContentView(R.layout.auth_twitter_activity_type);
            //Button authBtn = (Button) findViewById(R.id.btn_auth_pin);
            //authBtn.setOnClickListener(this);
        }
    }
*/ 
    
//TEST====未使用=========================================
    
	//おためし
	private OAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	
    //おためし
	private void askOAuth()
	{
		
		
			TwitterKeys key 		= new TwitterKeys();
			String consumerkey 		= key.getConsumerkey();
			String consumersecret 	= key.getConsumersecret();

			
			consumer = new CommonsHttpOAuthConsumer(consumerkey, consumersecret);
			provider = new CommonsHttpOAuthProvider("http://twitter.com/oauth/request_token","http://twitter.com/oauth/access_token","http://twitter.com/oauth/authorize");
			provider.setOAuth10a(true);
		
	}
}