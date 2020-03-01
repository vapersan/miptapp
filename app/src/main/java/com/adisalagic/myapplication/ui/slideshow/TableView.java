package com.adisalagic.myapplication.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.adisalagic.myapplication.MainActivity;
import com.adisalagic.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class TableView extends AppCompatActivity {
	View     view;
	TextView date;

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		final Locale locale = new Locale("ru", "RU");

		Date today = new Date();

		final Date finalToday = today;
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", locale);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Calendar calendar = Calendar.getInstance(locale);

					date.setText("");
					date.setText(simpleDateFormat.format(calendar.getTime()));
					Log.i("TIMER", "GEt");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Log.d("TIMER", Objects.requireNonNull(e.getMessage()));
					}
				}
			}
		}).start();

		assert getSupportActionBar() != null;   //null check
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Расписание");
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		view = getLayoutInflater().inflate(R.layout.activity_table, null);
		date = findViewById(R.id.today);
//		FragmentTransaction transaction    = getSupportFragmentManager().beginTransaction();
//		FragmentTableLessons lessons = new FragmentTableLessons(Lesson.createLessons(
//				new Lesson(1, "Русский язык", new TimeService(8, 45), new TimeService(10, 30)
//						, "Какой-то препод", ""),
//				new Lesson(2, "Английский язык", new TimeService(12, 0), new TimeService(12, 40),
//						"Любимый перпод", "321 к2")));
//		transaction.add(view.getId(), lessons, "Tag").commit();
//		Не работает, исправьте
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent();
		intent.setClass(view.getContext(), MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return super.onSupportNavigateUp();
	}
}
