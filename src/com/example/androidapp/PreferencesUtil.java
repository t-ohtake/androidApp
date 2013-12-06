package com.example.androidapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressLint("CommitPrefEdits")
public class PreferencesUtil
{
	private SharedPreferences pref = null;

	/**
	 * 指定されたプリファレンスをオブジェクトに格納して使えるようにする
	 * @param p
	 */
	PreferencesUtil(SharedPreferences p)
	{
		this.pref 	= p;
	}
	
	/**
	 * プリファレンスにStringの値をセット(キー/設定する値)
	 * 設定後にコミットされる
	 * @param name
	 * @param val
	 */
	public void putString(String name,String val)
	{
        Editor editor = this.pref.edit();
        editor.putString(name, val);
        editor.commit();
	}
	
	/**
	 * プリファレンスからStringの値をゲット(キー/取れなかった時のデフォルト値)
	 * @param name
	 * @param deft
	 * @return
	 */
	public String getString(String name,String deft)
	{
		String val = pref.getString(name, deft);
		return val;
	}
	
}
