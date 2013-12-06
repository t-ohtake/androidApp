package com.example.androidapp;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;




/**
 * タイムラインを表示するクラス
 * @author t-ohtake
 *
 */
@SuppressLint("ShowToast")
public class TimeLineActivity extends Activity
{
	//トークン変数
	private String _accessToken 		= null;
	private String _accessTokenSecret 	= null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        
        //プリファレンスからトークンを取得
        PreferencesUtil pref 	= new PreferencesUtil(this.getSharedPreferences("private_data", Context.MODE_PRIVATE));
        this._accessToken 		= pref.getString("accessToken", "");
        this._accessTokenSecret = pref.getString("accessTokenSecret", "");
        
		//Consumer-keyとConsumer-key-secretの設定
		TwitterKeys key 		= new TwitterKeys();
		Twitter tw = new TwitterFactory().getInstance();
		tw.setOAuthConsumer(key.getConsumerkey(), key.getConsumersecret());
		
		//AccessTokenオブジェクトの設定
		AccessToken at = new AccessToken(_accessToken, _accessTokenSecret);
		tw.setOAuthAccessToken(at);
		
		try
		{
			//タイムラインの取得
			ResponseList<Status> homeTl = tw.getHomeTimeline();
			System.out.println();
		}
		catch (TwitterException e)
		{
			String LOG_TAG = "ERROR:";
		    e.printStackTrace();
		    
		    Log.i(LOG_TAG, "タイムライン取得失敗", e);
		    if(e.isCausedByNetworkIssue())
		    {
		         Toast.makeText(this, "ネットーワークの問題です", Toast.LENGTH_LONG);
		    }
		}
    }
    
	/**
	 * タイムラインの取得
	 */
    private void getTimeLine()
    {
    	
    }

}
