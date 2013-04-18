package com.example.mbankclient.beans;

import java.sql.Date;

public class Deposit {

	private long deposit_id;
	private long client_id;
	private double balance;
	private String type;
	private double estimated_balance;

	private Date opening_date;
	private Date closing_date;

	public Deposit() {
	}

	public Deposit(long deposit_id, long client_id, double balacne,
			String type, long estimated_balance, Date opening_date,
			Date closing_date) {
		super();
		this.deposit_id = deposit_id;
		this.client_id = client_id;
		this.balance = balacne;
		this.type = type;
		this.estimated_balance = estimated_balance;
		this.opening_date = opening_date;
		this.closing_date = closing_date;
	}

	public long getDeposit_id() {
		return deposit_id;
	}

	public void setDeposit_id(long deposit_id) {
		this.deposit_id = deposit_id;
	}

	public long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balacne) {
		this.balance = balacne;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getEstimated_balance() {
		return estimated_balance;
	}

	public void setEstimated_balance(double d) {
		this.estimated_balance = d;
	}

	public Date getOpening_date() {
		return opening_date;
	}

	public void setOpening_date(Date date) {
		this.opening_date = date;
	}

	public Date getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(Date date) {
		this.closing_date = date;
	}

	@Override
	public String toString() {
		return "Deposit [deposit_id=" + deposit_id + ", client_id=" + client_id
				+ ", balance=" + balance + ", type=" + type
				+ ", estimated_balance=" + estimated_balance
				+ ", opening_date=" + opening_date + ", closing_date="
				+ closing_date + "]";
	}

}
