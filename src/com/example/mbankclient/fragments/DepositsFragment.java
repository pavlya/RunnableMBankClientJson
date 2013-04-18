package com.example.mbankclient.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.mbankclient.R;
import com.example.mbankclient.beans.Deposit;
import com.example.mbankclient.utils.ClientContainer;
import com.example.mbankclient.utils.ExpandableDepositAdapter;

public class DepositsFragment extends Fragment {
	
	private ExpandableDepositAdapter expandableDepositAdapter;
	private ExpandableListView expandableListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_deposits, null);

		List<Deposit> deposits = ClientContainer.getClientContainer()
				.getDeposits();
		
		expandableDepositAdapter = new ExpandableDepositAdapter(getActivity(), deposits);
		
		expandableListView = (ExpandableListView) view
				.findViewById(R.id.elv_Deposits);
		expandableListView.setAdapter(expandableDepositAdapter);

		return view;
	}

}
