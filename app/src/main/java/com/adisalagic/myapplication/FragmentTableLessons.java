package com.adisalagic.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTableLessons extends Fragment {
	Lesson[] lessons;
	View rootView;
	TableLayout layout;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_table, null);

		layout = rootView.findViewById(R.id.table);
		for (Lesson lesson : lessons) {
			layout.addView(lesson.toTableRow(rootView.getContext()));
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	public TableLayout getLayout() {
		return layout;
	}

	public void setLayout(TableLayout layout) {
		this.layout = layout;
	}

	public FragmentTableLessons(Lesson[] lessons) {
		this.lessons = lessons;
	}
}
