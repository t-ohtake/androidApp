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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
	
	//ListView表示用
	static ArrayAdapter<String> adapter;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        
        //プリファレンスからトークンを取得
        PreferencesUtil pref 	= new PreferencesUtil(this.getSharedPreferences("private_data", Context.MODE_PRIVATE));
        this._accessToken 		= pref.getString("accessToken", "");
        this._accessTokenSecret = pref.getString("accessTokenSecret", "");
        
		//タイムライン取得処理
		getTimeLine();
		
        //【つぶやくボタン押下時の動作】
        Button moveTweetBtn = (Button)findViewById(R.id.moveTweetBtn);
        moveTweetBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				//TweetActivetyへの遷移メソッド呼出し
				moveTweet();
			}
		});

    }
    
	/**
	 * タイムラインの取得
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void getTimeLine()
    {
		//Consumer-keyとConsumer-key-secretの設定
		TwitterKeys key 		= new TwitterKeys();
		Twitter tw = new TwitterFactory().getInstance();
		tw.setOAuthConsumer(key.getConsumerkey(), key.getConsumersecret());
		
		//AccessTokenオブジェクトの設定
		AccessToken at = new AccessToken(_accessToken, _accessTokenSecret);
		tw.setOAuthAccessToken(at);

		// リストビューに表示するためのデータを設定
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
		try
		{
			//タイムラインの取得
			ResponseList<Status> homeTl = tw.getHomeTimeline();
			
			for (Status status : homeTl)
			{
				//ユーザIDとつぶやきを取得　
				String userId = status.getUser().getScreenName();
				String tweet = status.getText();
				
				//
				adapter.add(userId + "\n" + tweet);
			}
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
		
        //ListViewに値を設定
        ListView  listView = (ListView)findViewById(R.id.TimelineListView);
        listView.setAdapter(adapter);
    }
    
	/**
	 *===========================================
	 * TimeLineActivityへ遷移処理
	 * ===========================================
	 */
	public void moveTweet()
	{
        //TimeLineActivityへ遷移
		Intent intent = new Intent(TimeLineActivity.this, TweetActivity.class);
        startActivity(intent);
	}

}
