package com.example.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.SimpleAdapter;
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
        
		//タイムライン取得処理
		getTimeLine();
		
		
        //【更新押下時の動作】
        Button updateBtn = (Button)findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				//TweetActivetyへの遷移メソッド呼出し
				updateTimeLine();
			}
		});
        
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
        
        //【シンプルゲームボタン押下時の動作】
        Button moveSimplegameBtn = (Button)findViewById(R.id.moveSimplegameBtn);
        moveSimplegameBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				//TweetActivetyへの遷移メソッド呼出し
				moveSimplegame();
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
		
        //リストビューに表示するためのデータを設定
        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
        
		try
		{
			//タイムラインの取得
			ResponseList<Status> homeTl = tw.getHomeTimeline();
			
			for (Status status : homeTl)
			{
				//ユーザIDとつぶやきを取得　
				String userId = status.getUser().getScreenName();
				String tweet = status.getText();
				
				//test
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("userId", userId);
				map.put("tweet", tweet);
				data.add(map);
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
		
		SimpleAdapter sa = new SimpleAdapter(this, data,R.layout.timelinrow, new String[]{"userId","tweet"},new int[]{R.id.userId,R.id.tweet});

        //ListViewに値を設定
        ListView  listView = (ListView)findViewById(R.id.TimelineListView);
        listView.setAdapter(sa);
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
	
	/**
	 *===========================================
	 * TimeLineActivityへ遷移処理
	 * ===========================================
	 */
	public void moveSimplegame()
	{
        //TimeLineActivityへ遷移
		Intent intent = new Intent(TimeLineActivity.this, SimpleGameActivity.class);
        startActivity(intent);
	}

	/**
	 *===========================================
	 * 画面更新
	 * ===========================================
	 */
	public void updateTimeLine()
	{
        //TimeLineActivityへ遷移
		Intent intent = new Intent(TimeLineActivity.this, TimeLineActivity.class);
        startActivity(intent);
	}
}
