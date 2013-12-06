package com.example.androidapp;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
 * つぶやき処理を行うクラス
 * @author t-ohtake
 *
 */
@SuppressLint("ShowToast")
public class TweetActivity extends Activity
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
        setContentView(R.layout.tweet);

        //プリファレンスからトークンを取得
        PreferencesUtil pref 	= new PreferencesUtil(this.getSharedPreferences("private_data", Context.MODE_PRIVATE));
        this._accessToken 		= pref.getString("accessToken", "");
        this._accessTokenSecret = pref.getString("accessTokenSecret", "");
        
        //【つぶやくボタン押下時の動作】
        Button tweetBtn = (Button)findViewById(R.id.tweetBtn);
        tweetBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
		        //つぶやきメソッド呼出し
		        tweetProcess();
			}
		});
        
        //【つぶやくボタン押下時の動作】
        Button moveTimelineBtn = (Button)findViewById(R.id.moveTimelineBtn);
        moveTimelineBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				//TimeLineActivityへ遷移メソッド呼出し
				moveTimeline();
			}
		});
    }
	
	/**
	 * ===========================================
	 * つぶやき処理
	 * ===========================================
	 */
	public void tweetProcess()
	{
		//Consumer-keyとConsumer-key-secretの設定
		TwitterKeys key 		= new TwitterKeys();
		Twitter tw = new TwitterFactory().getInstance();
		tw.setOAuthConsumer(key.getConsumerkey(), key.getConsumersecret());
		
		//AccessTokenオブジェクトの設定
		AccessToken at = new AccessToken(_accessToken, _accessTokenSecret);
		tw.setOAuthAccessToken(at);
		
		//つぶやきテキストビューから値取得
		EditText edittext = (EditText)findViewById(R.id.tweetText);
		SpannableStringBuilder sb = (SpannableStringBuilder)edittext.getText();
		String tweetStr = sb.toString();
		//つぶやき後メッセージ表示準備
		TextView textview = (TextView)findViewById(R.id.addTweetMsg);
		
		try
		{
			//つぶやきテキストビューに入力がある場合にのみつぶやきを実行
		    if(!tweetStr.equals(""))
		    {
				//つぶやき実行(つぶやきテキストの文字)
			    tw.updateStatus(tweetStr);
			    
			    //画面に値をセット
			    edittext.setText("");//つぶやきテキストビュー
			    textview.setText("\"" + tweetStr + "\"" + "をつぶやきました");//メッセージ
		    }
		    else
		    {
			    //画面に値をセット
			    textview.setText("Tweetが入力されていません");//メッセージ
		    }		    	
		}
		catch (TwitterException e)
		{
			String LOG_TAG = "ERROR:";
		    e.printStackTrace();
		    
		    Log.i(LOG_TAG, "つぶやき失敗", e);
		    if(e.isCausedByNetworkIssue())
		    {
		         Toast.makeText(this, "ネットーワークの問題です", Toast.LENGTH_LONG);
		    }
		    
		  //画面に値をセット
		    textview.setText("つぶやけませんでした(過去のTweetと同一内容、ネットワーク接続不可等が考えられます)");//メッセージ
		}
	}
	
	
	/**
	 *===========================================
	 * TimeLineActivityへ遷移処理
	 * ===========================================
	 */
	public void moveTimeline()
	{
        //TimeLineActivityへ遷移
		Intent intent = new Intent(TweetActivity.this, TimeLineActivity.class);
        startActivity(intent);
	}

}
