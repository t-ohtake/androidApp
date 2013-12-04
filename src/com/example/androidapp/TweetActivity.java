package com.example.androidapp;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



//コールバックされた後、アクセストークンを取得する
@SuppressLint("ShowToast")
public class TweetActivity extends Activity
{
	//トークン変数
	private String _accessToken 		= null;
	private String _accessTokenSecret 	= null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet);
        
        //前Activityからトークン取得
        Intent intent 			= getIntent();
        this._accessToken 		= intent.getStringExtra( "accessToken" );
        this._accessTokenSecret = intent.getStringExtra( "accessTokenSecret" );
        
        //つぶやくボタン押下時の動作
        Button btn = (Button)findViewById(R.id.tweetBtn);
        btn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
		        //つぶやきメソッド呼出し
		        tweetProcess();
			}
		});
    }
	
	//つぶやき処理
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
		    textview.setText("つぶやけませんでした(過去のTweetと同一内容、ネットワーク接続不備等が考えられます)");//メッセージ
		}
	}

}
