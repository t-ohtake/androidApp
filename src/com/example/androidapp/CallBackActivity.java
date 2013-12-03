package com.example.androidapp;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


//コールバック専用画面
@SuppressLint("ShowToast")
public class CallBackActivity extends Activity
{	
	//トークン変数
	private static String _accessToken = null;
	private static String _accessTokenSecret = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callback);
        
        AccessToken token = null;
        
        //oauth_verifierを取得する
        Uri uri = getIntent().getData();
        String verifier = uri.getQueryParameter("oauth_verifier");
        
        try
        {
        	//AccessToken取得
        	token = TwitterOauth._oauth.getOAuthAccessToken(TwitterOauth._req, verifier);
            _accessToken = token.getToken();
            _accessTokenSecret = token.getTokenSecret();
        }
        catch(TwitterException e)
        {
        	e.printStackTrace();
        }
        
        
        //画面遷移実行
        Intent intent = new Intent(CallBackActivity.this, TweetActivity.class);
        intent.putExtra( "accessToken", _accessToken );
        intent.putExtra( "accessTokenSecret", _accessTokenSecret );
        startActivity(intent);
    }
}
