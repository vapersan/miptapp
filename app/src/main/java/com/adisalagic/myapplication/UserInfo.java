package com.adisalagic.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class UserInfo extends Activity {
	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = getLayoutInflater().inflate(R.layout.user_info, null);
		setContentView(rootView);
	}
}
