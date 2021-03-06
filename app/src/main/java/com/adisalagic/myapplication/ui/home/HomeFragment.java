package com.adisalagic.myapplication.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.adisalagic.myapplication.MainData;
import com.adisalagic.myapplication.R;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
	TextView avarageScore;
	TextView ratingByCourse;
	TextView ratingByFsh;
	TextView status;
	TextView levelEduValue;
	TextView course;
	TextView podr;
	TextView group;
	TextView diraction;
	TextView programme;
	TextView payForm;
	private HomeViewModel homeViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		homeViewModel =
				ViewModelProviders.of(this).get(HomeViewModel.class);
		View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
//		final TextView textView = root.findViewById(R.id.text_home);
//		homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//			@Override
//			public void onChanged(@Nullable String s) {
//				textView.setText(s);
//			}
//		});
		avarageScore = fragmentView.findViewById(R.id.avarage_score_number);
		ratingByCourse = fragmentView.findViewById(R.id.rating_by_course_number);
		ratingByFsh = fragmentView.findViewById(R.id.rating_by_fsh_number);
		status = fragmentView.findViewById(R.id.status_value);
		levelEduValue = fragmentView.findViewById(R.id.level_edu_status);
		course = fragmentView.findViewById(R.id.level_edu_status6);
		podr = fragmentView.findViewById(R.id.level_edu_status7);
		group = fragmentView.findViewById(R.id.level_edu_status8);
		diraction = fragmentView.findViewById(R.id.level_edu_status9);
		programme = fragmentView.findViewById(R.id.level_edu_status10);
		payForm = fragmentView.findViewById(R.id.level_edu_status11);
		final MainData[] mainData = {null};
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				mainData[0] = getMainData();
				if (mainData[0] == null){
					return;
				}
				Log.i("ASYNK", "Выполняюсь");
				status.post(new Runnable() {
					@Override
					public void run() {
						set(mainData[0]);
					}
				});
			}
		});
		return fragmentView;

	}

	public MainData getMainData() {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://dragonica-mercy.online/api_json_study/")
				.build();

		try {
			Response response = client.newCall(request).execute();
			response.code();
			String result = response.body().string();
			Log.i("ASYNK", result);
			return new Gson().fromJson(result, MainData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void set(MainData mainData) {
		podr.setText(mainData.getPodr());
		levelEduValue.setText(mainData.getEduLevel());
		course.setText(mainData.getCourse());
		group.setText(mainData.getGroup());
		diraction.setText(mainData.getDiraction());
		programme.setText(mainData.getProgramme());
		payForm.setText(mainData.getPayForm());
		avarageScore.setText(mainData.getAvarageScore() + "");
		ratingByCourse.setText(mainData.getRatingByCourse() + "");
		ratingByFsh.setText(mainData.getRatingByFsh() + "");
		status.setText(mainData.getStatus());
	}
}
