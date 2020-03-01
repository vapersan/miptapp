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
		final TextView    SNP         = findViewById(R.id.SNP_value);
		final TextView    birthday    = findViewById(R.id.birthday_value);
		final TextView    passport    = findViewById(R.id.passport_value);
		final TextView    SNILS       = findViewById(R.id.snils_value);
		final TextView    email       = findViewById(R.id.email_value);
		final TextView    phone       = findViewById(R.id.phone_value);
		final TextView    addrDoc     = findViewById(R.id.address_doc_value);
		final TextView    addrReal    = findViewById(R.id.address_real_value);
		final Bitmap[]    bitmap      = new Bitmap[1];
		final ImageView   photo       = findViewById(R.id.photo);
		final TableLayout tableWork   = findViewById(R.id.table_work);
		final TableLayout tableFamily = findViewById(R.id.table_family);
		final TableRow    tableRow    = new TableRow(tableWork.getContext());
		final TextView    stavka      = new TextView(tableRow.getContext());
		TextView          stage       = new TextView(tableRow.getContext());
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
						phone.setText(data[0].getPhone());
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
						tableWork.addView(tableRow);
						tableWork.addView(tableRow1);
						if (bitmap[0] != null) {
							photo.setImageBitmap(bitmap[0]);
						}
						TableRow mother        = new TableRow(tableFamily.getContext());
						TableRow father        = new TableRow(tableFamily.getContext());
						TableRow sister        = new TableRow(tableFamily.getContext());
						TableRow brother       = new TableRow(tableFamily.getContext());
						TextView tMother       = new TextView(tableFamily.getContext());
						TextView tFather       = new TextView(tableFamily.getContext());
						TextView tSister       = new TextView(tableFamily.getContext());
						TextView tBrother      = new TextView(tableFamily.getContext());
						TextView tMotherValue  = new TextView(tableFamily.getContext());
						TextView tFatherValue  = new TextView(tableFamily.getContext());
						TextView tSisterValue  = new TextView(tableFamily.getContext());
						TextView tBrotherValue = new TextView(tableFamily.getContext());
						tMother.setText("Мать: ");
						tFather.setText("Отец: ");
						tSister.setText("Сестра: ");
						tBrother.setText("Брат: ");
						tMotherValue.setText(data[0].getFamilyMother());
						tFatherValue.setText(data[0].getFamilyFather());
						tSisterValue.setText(data[0].getFamilySister());
						tBrotherValue.setText(data[0].getFamilyBrother());
						mother.addView(tMother);
						mother.addView(tMotherValue);
						father.addView(tFather);
						father.addView(tFatherValue);
						sister.addView(tSister);
						sister.addView(tSisterValue);
						brother.addView(tBrother);
						brother.addView(tBrotherValue);
						tableFamily.addView(father);
						tableFamily.addView(mother);
						tableFamily.addView(sister);
						tableFamily.addView(brother);
					}
				});
			}
		});


	}

	public static PersonalData getData() {
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
