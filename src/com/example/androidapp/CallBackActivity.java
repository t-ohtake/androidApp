package com.example.androidapp;


import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

//コールバック専用クラス
public class CallBackActivity extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callback);
 /*
        AccessToken token = null;
 
        //Twitterの認証画面から発行されるIntentからUriを取得
        Uri uri = getIntent().getData();
        //Uri uri = getIntent().getData();
 
        if(uri != null && uri.toString().startsWith("Callback://CallBackActivity"))
        {
            //oauth_verifierを取得する
            String verifier = uri.getQueryParameter("oauth_verifier");
            try
            {
                //AccessTokenオブジェクトを取得
                token = TwitterOauth._oauth.getOAuthAccessToken(TwitterOauth._req, verifier);
            }
            catch (TwitterException e)
            {
                e.printStackTrace();
            }
        }

        TextView tv = (TextView)findViewById(R.id.textView1);//取得したトークン表示（テスト用なのであとで消しちゃう）
        CharSequence cs = "token：" + token.getToken() + "\r\n" + "token secret：" + token.getTokenSecret();
        tv.setText(cs);
        */
    }

}
