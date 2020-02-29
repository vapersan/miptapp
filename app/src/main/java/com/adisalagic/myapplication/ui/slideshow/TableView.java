package com.adisalagic.myapplication.ui.slideshow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.adisalagic.myapplication.FragmentTableLessons;
import com.adisalagic.myapplication.Lesson;
import com.adisalagic.myapplication.R;
import com.adisalagic.myapplication.TimeService;


public class TableView extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		FragmentTableLessons lessons = new FragmentTableLessons(Lesson.createLessons(
				new Lesson(1, "Русский язык", new TimeService(8, 45), new TimeService(10, 30)
				, "Какой-то препод", ""),
				new Lesson(2, "Английский язык", new TimeService(12, 0), new TimeService(12, 40),
						"Любимый перпод", "321 к2")));
		transaction.add(lessons, "Tag");
		transaction.commit();
	}
}
