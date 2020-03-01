package com.adisalagic.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
		final TextView  SNP       = findViewById(R.id.SNP_value);
		final TextView  birthday  = findViewById(R.id.birthday_value);
		final TextView  passport  = findViewById(R.id.passport_value);
		final TextView  SNILS     = findViewById(R.id.snils_value);
		final TextView  email     = findViewById(R.id.email_value);
		final TextView  phone     = findViewById(R.id.phone_value);
		final TextView  addrDoc   = findViewById(R.id.address_doc_value);
		final TextView  addrReal  = findViewById(R.id.address_real_value);
		final Bitmap[]  bitmap    = new Bitmap[1];
		final ImageView photo     = findViewById(R.id.photo);
		TableLayout     tableWork = new TableLayout(rootView.getContext());
		final TableRow  tableRow  = new TableRow(tableWork.getContext());
		final TextView  stavka    = new TextView(tableRow.getContext());
		TextView        stage     = new TextView(tableRow.getContext());
		stavka.setText("Ставка: ");
		stage.setText("Должность: ");
		tableRow.addView(stage);
		final PersonalData[] data = new PersonalData[1];
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				data[0] = getData();
				bitmap[0] = getBitmapFromURL(data[0].getImageUrl());
				SNP.post(new Runnable() {
					@Override
					public void run() {
						SNP.setText(data[0].getName());
						birthday.setText(data[0].getBirthday());
						passport.setText(data[0].getPassport());
						SNILS.setText(data[0].getSnils());
						email.setText(data[0].getEmail());
						addrDoc.setText(data[0].getAddressDoc());
						addrReal.setText(data[0].getAddressReal());
						TextView stageValue = new TextView(tableRow.getContext());
						stageValue.setText(data[0].getWorkPosition());
						tableRow.addView(stageValue);
						TextView stavka_value = new TextView(tableRow.getContext());
						stavka_value.setText(data[0].getWorkDevidents());
						TableRow tableRow1 = new TableRow(tableRow.getContext());
						tableRow1.addView(stavka);
						tableRow1.addView(stavka_value);


						if (bitmap[0] != null) {
							photo.setImageBitmap(bitmap[0]);
						}

					}
				});
			}
		});


	}

	public PersonalData getData() {
		MediaType    JSON   = MediaType.get("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://dragonica-mercy.online/api_json_personalInf/")
				.build();

		try (Response response = client.newCall(request).execute()) {
			return new Gson().fromJson(response.body().string(), PersonalData.class);
		} catch (Exception e) {
		}
		return null;
	}


	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL               url        = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			return BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			// Log exception
			return null;
		}
	}
}
