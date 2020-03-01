package com.adisalagic.myapplication.LoDP;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adisalagic.myapplication.PersonalData;
import com.adisalagic.myapplication.R;
import com.adisalagic.myapplication.UserInfo;

import java.util.Objects;

public class LossOrDisablePass extends Fragment {

	private LossOrDisablePassViewModel mViewModel;

	public static LossOrDisablePass newInstance() {
		return new LossOrDisablePass();
	}

	EditText SNP, birthday, email, phone;

	PersonalData personalData;

	View view;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.loss_or_disable_pass_fragment, null);
		SNP = view.findViewById(R.id.editOne);
		birthday = view.findViewById(R.id.editTwo);
		email = view.findViewById(R.id.editThird);
		phone = view.findViewById(R.id.editFour);


		final Button smrt = view.findViewById(R.id.smrt);
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				Log.i("ASYNK", "Выполняюсь");
				personalData = UserInfo.getData();
				if (personalData == null){
					return;
				}
				email.post(new Runnable() {
					@Override
					public void run() {
						Log.i("ASYNK", "HERE!");
						SNP.setText(personalData.getName());
						birthday.setText(personalData.getBirthday());
						email.setText(personalData.getEmail());
						phone.setText(personalData.getPhone());
					}
				});
			}
		});

		return inflater.inflate(R.layout.loss_or_disable_pass_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this).get(LossOrDisablePassViewModel.class);


	}


}
