package com.example.mbankclient.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mbankclient.R;
import com.example.mbankclient.beans.Deposit;

public class ExpandableDepositAdapter extends BaseExpandableListAdapter {

	private List<Deposit> deposits;
	private LayoutInflater inflater;
	private NumberFormat numberFormatter;
	private Context ctx;
	
	public ExpandableDepositAdapter(Context context, List<Deposit> deposits) {
		this.deposits = deposits;
		ctx = context;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	// returns the child id according to parent's position
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			view = inflater.inflate(R.layout.layout_mbank_deposit, parent, false);
		}
		
		// get the deposit value according to it's position in array
		Deposit deposit = deposits.get(groupPosition);
		
		// Preparing values
		// get current locale settings
		Locale current = ctx.getResources().getConfiguration().locale;
		// format numbers using current locale
		numberFormatter = NumberFormat.getNumberInstance(current);
		// formatting the date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		
		String balanceString = numberFormatter.format(deposit.getBalance());
		String estimatedBalanceString = String.valueOf(deposit
				.getEstimated_balance());
		String openingDate = sdf.format(deposit.getOpening_date());
		String closingDate = sdf.format(deposit.getClosing_date());

		TextView tvBalance = (TextView) view.findViewById(R.id.tv_balance);
		TextView tvEstimatedBalance = (TextView) view
				.findViewById(R.id.tv_estimated_balance);
		TextView tvOpeningDate = (TextView) view
				.findViewById(R.id.tv_opening_date);
		TextView tvClosingDate = (TextView) view
				.findViewById(R.id.tv_closing_date);

		tvBalance.setText(balanceString);
		tvEstimatedBalance.setText(estimatedBalanceString);
		tvOpeningDate.setText(openingDate);
		tvClosingDate.setText(closingDate);
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// return 1 because we wan only one member in every parent
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return deposits.get(groupPosition).getType();
	}

	@Override
	public int getGroupCount() {
		return deposits.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			view = inflater.inflate(R.layout.mbank_deposit_parent, parent, false);
		}
		
		TextView tvParentText = (TextView)view.findViewById(R.id.tv_deposit_parent);
		// "groupPosition" is the position of the parent/group in the list
		tvParentText.setText(deposits.get(groupPosition).getOpening_date().toString());
		
		// set default picture
		ImageView ivParent = (ImageView)view.findViewById(R.id.iv_deposit_parent);
		ivParent.setImageResource(R.drawable.ic_launcher);
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
