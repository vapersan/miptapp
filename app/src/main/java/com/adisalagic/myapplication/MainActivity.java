package com.adisalagic.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adisalagic.myapplication.ui.slideshow.TableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

	private             AppBarConfiguration mAppBarConfiguration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setBackgroundResource(R.color.colorPrimary);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
				View                dView   = getLayoutInflater().inflate(R.layout.dialog_feedback, null, false);
				builder.setView(dView);
				final Button   button   = dView.findViewById(R.id.sender);
				final EditText editText = dView.findViewById(R.id.text);
				final Dialog   dialog   = builder.create();
				final int[]    code     = {-1};
				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(final View v) {
						AsyncTask.execute(new Runnable() {
							@Override
							public void run() {
								OkHttpClient client = new OkHttpClient().newBuilder()
										.build();
								MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
								RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
										.addFormDataPart("text", editText.getText().toString())
										.addFormDataPart("rate", "0")
										.addFormDataPart("status", "0")
										.build();
								Request request = new Request.Builder()
										.url("https://dragonica-mercy.online/api/feedback/")
										.method("POST", body)
										.addHeader("Content-Type", "application/x-www-form-urlencoded")
										.build();
								try {
									Response response = client.newCall(request).execute();
									code[0] = response.code();
									dialog.dismiss();
								} catch (Exception e) {
									Log.i("POST", e.getMessage());
								}
								button.post(new Runnable() {
									@Override
									public void run() {
										if (code[0] == 200 || code[0] == 201) {
											Toast.makeText(v.getContext(), "Успешно", Toast.LENGTH_LONG).show();
										} else {
											Toast.makeText(v.getContext(), "Неудачно!", Toast.LENGTH_LONG).show();
										}
									}
								});
							}
						});

					}
				});
				dialog.show();
			}
		});
		DrawerLayout         drawer         = findViewById(R.id.drawer_layout);
		final NavigationView navigationView = findViewById(R.id.nav_view);
		final PersonalData[] personalData   = new PersonalData[1];
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				Bitmap bitmap = null;
				personalData[0] = UserInfo.getData();
				if (personalData[0] != null) {
					bitmap = UserInfo.getBitmapFromURL(personalData[0].getImageUrl());
					bitmap = Bitmap.createBitmap(bitmap, 0, 0, 150, 150);
				}else {
					return;
				}
				final Bitmap finalBitmap = bitmap;
				navigationView.getHeaderView(0).post(new Runnable() {
					@Override
					public void run() {
						ImageView image = navigationView.findViewById(R.id.imageView);
						image.setImageBitmap(finalBitmap);
						TextView textView = navigationView.findViewById(R.id.name);
						textView.setText(personalData[0].getName());
						TextView email = navigationView.findViewById(R.id.email);
						email.setText(personalData[0].getEmail());
					}
				});
			}
		});
		navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), UserInfo.class);
				startActivity(intent);
			}
		});
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home,
//				R.id.nav_gallery,
//				R.id.nav_slideshow,
				R.id.nav_table_view,
				R.id.nav_resend_view,
				R.id.nav_loss_or_disable_pass
		)
				.setDrawerLayout(drawer)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
				|| super.onSupportNavigateUp();
	}

	public void onTableClick(View v) {
		Intent intent = new Intent();
		intent.setClass(v.getContext(), TableView.class);
		finish();
		startActivity(intent);
	}

	public void onButtonClicked(View view) {
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
		builder.setMessage("Вы действительно хотите продолжить?");
		builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("Да", null);
		builder.create().show();
	}



}
