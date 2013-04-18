package com.example.mbankclient.fragments;

import com.example.mbankclient.R;
import com.example.mbankclient.utils.ClientContainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClientFragment extends Fragment {
	
	private TextView tvClient;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_client, null);
		String clientString = ClientContainer.getClientContainer().getClient().toString();
		tvClient = (TextView)view.findViewById(R.id.tv_client);
		tvClient.setText(clientString);
		return view;
	}
}
