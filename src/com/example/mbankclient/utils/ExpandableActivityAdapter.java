package com.example.mbankclient.utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mbankclient.R;
import com.example.mbankclient.beans.MbankClientActivity;

public class ExpandableActivityAdapter extends BaseExpandableListAdapter {

	private List<MbankClientActivity> activities;
	private LayoutInflater inflater;
	private NumberFormat numberFormatter;
	private Context ctx;
	private Pattern withdrawPattern;
	private Pattern depositPattern;
	private Matcher operationWithdrawMatcher;
	private Matcher operationDepositMatcher;

	public ExpandableActivityAdapter(Context context,
			List<MbankClientActivity> activities) {
		this.activities = activities;
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
			view = inflater.inflate(R.layout.layout_mbank_activity, parent, false);
		}
		
		MbankClientActivity clActivity = activities.get(groupPosition);
		
		TextView tvDateOfActivity = (TextView)view.findViewById(R.id.tv_activity_date);
		TextView tvAmount = (TextView)view.findViewById(R.id.tv_activity_amount);
		TextView tvCommission = (TextView)view.findViewById(R.id.tv_activity_commission);
		TextView tvDescription = (TextView)view.findViewById(R.id.tv_description_activity);
		
		tvDateOfActivity.setText(clActivity.getActivity_date().toString());
		tvAmount.setText(String.valueOf(clActivity.getAmount()));
		tvCommission.setText(String.valueOf(clActivity.getCommission()));
		tvDescription.setText(clActivity.getDescription());
		
		return view;
	}

	// return 1 because we wan only one member in every parent object
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return activities.get(groupPosition).getDescription();
	}

	@Override
	public int getGroupCount() {
		return activities.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			view = inflater.inflate(R.layout.mbank_activity_parent, parent, false);
		}
		
		TextView tvParentText = (TextView)view.findViewById(R.id.tv_activity_parent);
		ImageView ivParent = (ImageView)view.findViewById(R.id.iv_activity_parent);
		// "groupPosition" is the position of the parent/group in the list
		String description = activities.get(groupPosition).getDescription();
		
		// check what operation has been made to display custom icon and text
		withdrawPattern = Pattern.compile("\\bWithdrawing\\b");
		depositPattern = Pattern.compile("\\bDepositing\\b");
		operationWithdrawMatcher = withdrawPattern.matcher(description);
		operationDepositMatcher = depositPattern.matcher(description);
		
		if(operationWithdrawMatcher.find()){
			tvParentText.setText("Withdrawing");
			ivParent.setImageResource(R.drawable.red_triangle);
		} else if(operationDepositMatcher.find()){
			tvParentText.setText("Depositing");
			ivParent.setImageResource(R.drawable.green_triangle);
		} else {
			tvParentText.setText("SOmething went wrong");
			ivParent.setImageResource(R.drawable.ic_launcher);
		}
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
