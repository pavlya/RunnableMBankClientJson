package com.example.mbankclient.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mbankclient.R;
import com.example.mbankclient.beans.MbankClientActivity;
import com.example.mbankclient.utils.ClientContainer;
import com.example.mbankclient.utils.ExpandableActivityAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class ActivitiesFragment extends Fragment {

	private ExpandableActivityAdapter expandableActivityAdapter;
	private ExpandableListView expandableListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_activities, null);

		List<MbankClientActivity> activities = ClientContainer
				.getClientContainer().getActivities();
		
		expandableActivityAdapter = new ExpandableActivityAdapter(getActivity(), activities);

		expandableListView = (ExpandableListView) view
				.findViewById(R.id.elv_Activities);
		
		expandableListView.setAdapter(expandableActivityAdapter);

		return view;
	}
}
