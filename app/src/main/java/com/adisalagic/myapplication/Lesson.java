package com.adisalagic.myapplication;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson {
	int id = 0;
	String name = "";
	TimeService start;
	TimeService end;
	String teacher = "";
	String cabinet = "";


	public Lesson(int id, String name, TimeService start, TimeService end, String teacher, String cabinet){
		setId(id);
		setName(name);
		setStart(start);
		setEnd(end);
		setTeacher(teacher);
		setCabinet(cabinet);
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCabinet() {
		return cabinet;
	}

	public void setCabinet(String cabinet) {
		this.cabinet = cabinet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TimeService getStart() {
		return start;
	}

	public void setStart(TimeService start) {
		this.start = start;
	}

	public TimeService getEnd() {
		return end;
	}

	public void setEnd(TimeService end) {
		this.end = end;
	}

	public TableRow toTableRow(Context v){
		TableRow tableRow = new TableRow(v);
		TextView textView = new TextView(tableRow.getContext());
		textView.setText(id);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMarginStart(10);
		textView.setTextSize(15);
		textView.setText(String.valueOf(id));
		textView.setLayoutParams(layoutParams);
		tableRow.addView(textView);

		textView = new TextView(tableRow.getContext());
		layoutParams.setMarginStart(20);
		textView.setTextSize(15);
		textView.setLayoutParams(layoutParams);
		textView.setText(name + "\n" + start.toString(true) + "-" + end.toString(true));
		tableRow.addView(textView);

		textView = new TextView(tableRow.getContext());
		layoutParams.setMarginStart(50);
		textView.setLayoutParams(layoutParams);
		textView.setText(teacher + "\n" + cabinet);
		tableRow.addView(textView);

		return tableRow;
	}

	public static Lesson[] createLessons(Lesson... args){
		ArrayList<Lesson> lessons = new ArrayList<>(Arrays.asList(args));
		return (Lesson[]) lessons.toArray();
	}
}
