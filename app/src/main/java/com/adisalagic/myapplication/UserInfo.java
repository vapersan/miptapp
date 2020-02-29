package com.adisalagic.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

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
		final TextView SNP = findViewById(R.id.SNP_value),
				birthday = findViewById(R.id.birthday_value),
				passport = findViewById(R.id.passport_value),
				SNILS = findViewById(R.id.snils_value),
				email = findViewById(R.id.email_value),
				phone = findViewById(R.id.phone_value),
				addrDoc = findViewById(R.id.address_doc_value),
				addrReal = findViewById(R.id.address_real_value);
		TableLayout    tableWork = new TableLayout(rootView.getContext());
		final TableRow tableRow  = new TableRow(tableWork.getContext());
		final TextView stavka    = new TextView(tableRow.getContext());
		TextView       stage     = new TextView(tableRow.getContext());
		stavka.setText("Ставка: ");
		stage.setText("Должность: ");
		tableRow.addView(stage);
		final PersonalData[] data = new PersonalData[1];
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				data[0] = getData();
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
						tableRow1.addView(stageValue);
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
}
