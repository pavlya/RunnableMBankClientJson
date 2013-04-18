package com.example.mbankclient;

import java.util.List;

import com.example.mbankclient.beans.Deposit;
import com.example.mbankclient.beans.MbankClientActivity;
import com.example.mbankclient.fragments.ActivitiesFragment;
import com.example.mbankclient.fragments.ClientFragment;
import com.example.mbankclient.fragments.DepositsFragment;
import com.example.mbankclient.utils.ClientContainer;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends FragmentActivity implements OnClickListener {

	private Button btnClient;
	private Button btnDeposit;
	private Button btnActivities;
	private Fragment clientFragment;
	private Fragment depositsFragment;
	private Fragment activitiesFragment;
	private FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);

		btnClient = (Button) findViewById(R.id.btn_client);
		btnClient.setOnClickListener(this);
		btnDeposit = (Button) findViewById(R.id.btn_deposits);
		btnDeposit.setOnClickListener(this);
		btnActivities = (Button) findViewById(R.id.btn_activities);
		btnActivities.setOnClickListener(this);

		clientFragment = new ClientFragment();
		depositsFragment = new DepositsFragment();
		activitiesFragment = new ActivitiesFragment();

		fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.fragment_container, clientFragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

//		switch (v.getId()) {
//		case R.id.btn_client:
//			fragmentTransaction = getSupportFragmentManager()
//					.beginTransaction();
//			fragmentTransaction
//					.replace(R.id.fragment_container, clientFragment);
//			break;
//		case R.id.btn_activities:
//			fragmentTransaction = getSupportFragmentManager()
//					.beginTransaction();
//			fragmentTransaction.replace(R.id.fragment_container,
//					activitiesFragment);
//			break;
//		case R.id.btn_deposits:
//			fragmentTransaction = getSupportFragmentManager()
//					.beginTransaction();
//			fragmentTransaction.replace(R.id.fragment_container,
//					depositsFragment);
//
//			break;
//
//		default:
//			break;
//		}

		if (v.getId() == R.id.btn_client) {
			fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction
					.replace(R.id.fragment_container, clientFragment);
		} else if (v.getId() == R.id.btn_activities) {
			fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_container,
					activitiesFragment);

		} else if (v.getId() == R.id.btn_deposits) {
			fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_container,
					depositsFragment);
		}

		fragmentTransaction.commit();
	}

}
