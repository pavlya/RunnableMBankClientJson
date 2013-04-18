package com.example.mbankclient.utils;

import java.util.List;

import android.app.Activity;

import com.example.mbankclient.beans.Account;
import com.example.mbankclient.beans.Client;
import com.example.mbankclient.beans.Deposit;
import com.example.mbankclient.beans.MbankClientActivity;

public class ClientContainer {

	private static ClientContainer clientContainer;
	private Client client;
	private Account account;
	private List<Deposit> deposits;
	private List<MbankClientActivity> activities;
	private boolean loggedIn;

	private ClientContainer() {
		setLoggedIn(false);
	}

	public static ClientContainer getClientContainer() {
		if (clientContainer == null) {
			synchronized (ClientContainer.class) {
				if (clientContainer == null)
					clientContainer = new ClientContainer();
			}
		}
		return clientContainer;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Deposit> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}

	public List<MbankClientActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<MbankClientActivity> activities) {
		this.activities = activities;
	}

	public ClientContainer getInstance() {
		if (clientContainer == null) {
			synchronized (ClientContainer.class) {
				if (clientContainer == null)
					clientContainer = new ClientContainer();
			}
		}
		return clientContainer;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
