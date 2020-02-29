package com.adisalagic.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.adisalagic.myapplication.ui.slideshow.TableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

	private AppBarConfiguration mAppBarConfiguration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View fragmentView = getLayoutInflater().inflate(R.layout.fragment_home, null, false);
		final TextView avarageScore = fragmentView.findViewById(R.id.avarage_score_number),
				ratingByCourse = fragmentView.findViewById(R.id.rating_by_course_number),
				ratingByFsh = fragmentView.findViewById(R.id.rating_by_fsh_number),
				status = fragmentView.findViewById(R.id.status_value),
				levelEduValue = fragmentView.findViewById(R.id.level_edu_status),
				course = fragmentView.findViewById(R.id.level_edu_status6),
				podr = fragmentView.findViewById(R.id.level_edu_status7),
				group = fragmentView.findViewById(R.id.level_edu_status8),
				diraction = fragmentView.findViewById(R.id.level_edu_status9),
				programme = fragmentView.findViewById(R.id.level_edu_status10),
				payForm = fragmentView.findViewById(R.id.level_edu_status11);
		final MainData[] mainData = {null};
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				mainData[0] = getMainData();
				avarageScore.post(new Runnable() {
					@Override
					public void run() {
						podr.setText(mainData[0].getPodr());
						levelEduValue.setText(mainData[0].getEduLevel());
						course.setText(mainData[0].getCourse());
						group.setText(mainData[0].getGroup());
						diraction.setText(mainData[0].getDiraction());
						programme.setText(mainData[0].getProgramme());
						payForm.setText(mainData[0].getPayForm());
					}
				});
			}
		});
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
		DrawerLayout   drawer         = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
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
				R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_table_view,
				R.id.nav_resend_view
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



	public MainData getMainData(){
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://dragonica-mercy.online/api_json_study/")
				.build();

		try (Response response = client.newCall(request).execute()) {
			return new Gson().fromJson(response.body().string(), MainData.class);
		} catch (Exception e) {
		}
		return null;
	}
}
