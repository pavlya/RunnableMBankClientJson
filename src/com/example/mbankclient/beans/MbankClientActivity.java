package com.example.mbankclient.beans;

import java.sql.Date;

public class MbankClientActivity{
	private long activity_id;
	private long client_id;
	private double amount;
	private Date activity_date;
	private double commission;
	private String description;
	
	public MbankClientActivity() {
	}

	public long getActivity_Id() {
		return activity_id;
	}

	public void setActivity_Id(long id) {
		this.activity_id = id;
	}

	public long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(Date activity_date) {
		this.activity_date = activity_date;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Activity [id=" + activity_id + ", client_id=" + client_id + ", amount="
				+ amount + ", activity_date=" + activity_date + ", commission="
				+ commission + ", description=" + description + "]";
	}

	public MbankClientActivity(long activity_id, long client_id, double amount,
			Date activity_date, double commission, String description) {
		super();
		this.activity_id = activity_id;
		this.client_id = client_id;
		this.amount = amount;
		this.activity_date = activity_date;
		this.commission = commission;
		this.description = description;
	}
	
	
	
}
