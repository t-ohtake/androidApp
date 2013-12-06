package com.example.androidapp;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;


/**
 * コールバック専用画面
 * @author t-ohtake
 *
 */
@SuppressLint({ "ShowToast", "CommitPrefEdits" })
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
        
        //プリファレンスへアクセストークンを格納
        PreferencesUtil pref 	= new PreferencesUtil(this.getSharedPreferences("private_data", Context.MODE_PRIVATE));
        pref.putString("accessToken", 		_accessToken);
        pref.putString("accessTokenSecret", _accessTokenSecret);
        
        //画面遷移実行
        Intent intent = new Intent(CallBackActivity.this, TweetActivity.class);
        startActivity(intent);
    }
}
