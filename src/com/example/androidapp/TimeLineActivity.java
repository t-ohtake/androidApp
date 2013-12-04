package com.example.androidapp;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



/**
 * タイムラインを表示するクラス
 * @author t-ohtake
 *
 */
@SuppressLint("ShowToast")
public class TimeLineActivity extends Activity
{
	//インテント変数
	private Intent intent = getIntent();
	//トークン変数
	private String _accessToken 		= null;
	private String _accessTokenSecret 	= null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        
        //前Activityからトークン取得
        this.intent 			= getIntent();
        this._accessToken 		= intent.getStringExtra( "accessToken" );
        this._accessTokenSecret = intent.getStringExtra( "accessTokenSecret" );
        
		//Consumer-keyとConsumer-key-secretの設定
		TwitterKeys key 		= new TwitterKeys();
		Twitter tw = new TwitterFactory().getInstance();
		tw.setOAuthConsumer(key.getConsumerkey(), key.getConsumersecret());
		
		//AccessTokenオブジェクトの設定
		AccessToken at = new AccessToken(_accessToken, _accessTokenSecret);
		tw.setOAuthAccessToken(at);
        


    }
    
	/**
	 * タイムラインの取得
	 */
    private void getTimeLine()
    {
    	
    }

}
