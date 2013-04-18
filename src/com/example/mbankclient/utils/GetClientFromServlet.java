package com.example.mbankclient.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.DESedeKeySpec;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.mbankclient.MbankMainActivity;
import com.example.mbankclient.R;
import com.example.mbankclient.ResultActivity;
import com.example.mbankclient.R.id;
import com.example.mbankclient.R.string;
import com.example.mbankclient.beans.Client;
import com.example.mbankclient.beans.Deposit;
import com.example.mbankclient.beans.MbankClientActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GetClientFromServlet extends
		AsyncTask<String, Integer, JSONObject> {

	private static final String CLIENT_ACTIVTIES_INFO_JSON = "clientActivitiesInfo";
	private static final String CLIENT_DEPOSITS_INFO_JSON = "clientDepositsInfo";
	private static final String CLIENT_INFO_JSON = "clientInfo";
	private static final String JSON_SUCCESS_PROPERTY = "success";
	private Activity parentActivity;
	private ProgressDialog progressDialog;
	private Resources res;
	private TextView messageView;
	private String errorString;

	public GetClientFromServlet(Activity activity) {
		parentActivity = activity;
		res = activity.getResources();
		progressDialog = new ProgressDialog(parentActivity);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		if (result != null) {
			try {
				deserializeJSONandAddToSingletone(result);
				Intent intent = new Intent(parentActivity, ResultActivity.class);
				parentActivity.startActivity(intent);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			messageView.setText(errorString);
		}
		// Close the progress dialog
		progressDialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		//messageView = (TextView) parentActivity.findViewById(R.id.tv_message);
		//messageView.setText(null);
		// reset the progress bar
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setCancelable(true);
		progressDialog.setMessage("Loading...");
		progressDialog.setProgress(0);
	}

	@Override
	protected JSONObject doInBackground(String... urlString) {
		String jsonResponse = getResonseString(urlString[0]);
		JSONObject servletResponse = getJsonObjectFromString(jsonResponse);
		return servletResponse;
	}

	// Create Json Object according to received String
	private JSONObject getJsonObjectFromString(String jsonResponse) {
		JSONObject responseObject = null;
		Log.d(MbankMainActivity.MBANK_CLIENT,
				"GetClientFromServlet.getJsonObjectFromString Response: "
						+ jsonResponse);
		// create JSON object from String
		if (jsonResponse != null && jsonResponse.length() > 0) {
			try {
				responseObject = new JSONObject(jsonResponse.toString());
				boolean success = responseObject
						.getBoolean(JSON_SUCCESS_PROPERTY);
				if (!success) {
					errorString = res
							.getString(R.string.wrong_username_password);
					return null;
				}
			} catch (JSONException e) {
				Log.e(MbankMainActivity.MBANK_CLIENT,
						"GetClientFromServlet.getJsonObjectFromString JSONException: "
								+ e.getMessage());
			}
		}
		return responseObject;
	}

	// Deserializes JSON response and pass values to singletone
	private void deserializeJSONandAddToSingletone(JSONObject responseObject)
			throws JSONException {
		ClientContainer container = ClientContainer.getClientContainer();
		// Set format that matches servlet response format
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		// Get client Object
		String clientString = responseObject.getString(CLIENT_INFO_JSON);
		Client client = gson.fromJson(clientString, Client.class);
		Log.d(MbankMainActivity.MBANK_CLIENT,
				"GetClientFromServlet.getJsonObjectFromString client: "
						+ client.toString());
		container.setClient(client);

		// Get Deposits List
		String depositsString = responseObject
				.getString(CLIENT_DEPOSITS_INFO_JSON);
		List<Deposit> clientDeposits = new ArrayList<Deposit>();

		Log.d(MbankMainActivity.MBANK_CLIENT,
				"GetClientFromServlet.getJsonObjectFromString depositsString: "
						+ depositsString);

		// Deserialize collection
		Type depositType = new TypeToken<ArrayList<Deposit>>() {
		}.getType();
		clientDeposits = gson.fromJson(depositsString, depositType);
		for (Deposit deposit : clientDeposits) {
			Log.d(MbankMainActivity.MBANK_CLIENT,
					"GetClientFromServlet.getJsonObjectFromString deposit: "
							+ deposit);
		}
		// Assign to container
		container.setDeposits(clientDeposits);

		// Get Activities List
		String activitiesString = responseObject
				.getString(CLIENT_ACTIVTIES_INFO_JSON);
		Log.d(MbankMainActivity.MBANK_CLIENT,
				"GetClientFromServlet.getJsonObjectFromString activitiesString: "
						+ activitiesString);

		List<MbankClientActivity> clientActivities = new ArrayList<MbankClientActivity>();

		// Deserialize collection
		Type activityType = new TypeToken<ArrayList<MbankClientActivity>>() {
		}.getType();
		clientActivities = gson.fromJson(activitiesString, activityType);
		for (MbankClientActivity activity : clientActivities) {
			Log.d(MbankMainActivity.MBANK_CLIENT,
					"GetClientFromServlet.getJsonObjectFromString activity: "
							+ activity);
		}
		// Assign to container
		container.setActivities(clientActivities);
	}

	private String getResonseString(String urlString) {
		URL url;
		BufferedReader input = null;
		HttpURLConnection httpConnection = null;
		try {
			// String query = URLEncoder.encode(urlString, "utf-8");
			Log.d(MbankMainActivity.MBANK_CLIENT, "Connection string: "
					+ urlString);
			url = new URL(urlString);
			httpConnection = (HttpURLConnection) url.openConnection();
			StringBuilder response = new StringBuilder();

			// Check status of the connection
			if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				Log.e(MbankMainActivity.MBANK_CLIENT, "can't connect to server");
				errorString = res.getString(R.string.no_connection);
				return null;
			} else {
				Log.d(MbankMainActivity.MBANK_CLIENT,
						"GetClientFromServlet.getResonseString successfully connected");
				int fileLength = httpConnection.getContentLength();
				Log.d(MbankMainActivity.MBANK_CLIENT,
						"GetClientFromServlet.getResonseString fileLength: "
								+ fileLength);
				progressDialog.setMax(fileLength);

				// Use BufferedReader to the read data into a string
				input = new BufferedReader(new InputStreamReader(
						httpConnection.getInputStream()));

				String line;
				while ((line = input.readLine()) != null) {
					response.append(line);
				}

				Log.d(MbankMainActivity.MBANK_CLIENT,
						"GetClientFromServlet.getResonseString line: "
								+ response.toString());
				return response.toString();
			}
		} catch (Exception e) {
			errorString = res.getString(R.string.no_connection);
			Log.e(MbankMainActivity.MBANK_CLIENT,
					"GetClientFromServlet.getResonseString error: "
							+ e.getMessage());

		}
		// Close stream and connection
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e2) {
					Log.e(MbankMainActivity.MBANK_CLIENT,
							"GetClientFromServlet.getResonseString error: "
									+ e2.getMessage());
				}
				if (httpConnection != null) {
					httpConnection.disconnect();
				}
			}
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		progressDialog.show();
		progressDialog.setProgress(values[0]);
	}

}
