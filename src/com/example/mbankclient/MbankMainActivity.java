package com.example.mbankclient;

import com.example.mbankclient.utils.GetClientFromServlet;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MbankMainActivity extends Activity {

	//private Button btnExit; //I think you should add this in a menu
	private Button btnLogin;
	private EditText etUsername;
	private EditText etPassword;
	//private TextView tvMessage; //Add this where ever you like in XML
	// Holds URL for servlet JSON response
	private String servletURL;
	private String servletUsername;
	private String servletPassword;
	public static final String MBANK_CLIENT = "MBankClient";
	
	
	private GetClientFromServlet getClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mbank_main);
		Resources res = getResources();
		servletURL = res.getString(R.string.mbankURL);
		servletUsername = res.getString(R.string.userNameString);
		servletPassword = res.getString(R.string.userPassword);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		//tvMessage = (TextView) findViewById(R.id.tv_message);
		
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				makeLogin();
			}
		});

		//btnExit = (Button) findViewById(R.id.btn_quit);
		/*btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});*/
	}

	// Checks the client input and tries to make login
	protected void makeLogin() {
		String username = etUsername.getText().toString().trim();
		String userPassword = etPassword.getText().toString().trim();
		if (username != null && userPassword != null
				&& username.length() > 0 && userPassword.length() > 0) {
			String sendURL = createConnectionString(username,
					userPassword);
			getClient = new GetClientFromServlet(MbankMainActivity.this);
			getClient.execute(sendURL);
		} else {
			//tvMessage.setText("Empty username or password");
			Toast.makeText(getApplicationContext(), "Wrong login", Toast.LENGTH_SHORT).show();
		}
	}

	// Create connection String using user name and password
	private String createConnectionString(String username,
			String userPassword) {
		StringBuilder sendURL = new StringBuilder();
		sendURL.append(servletURL).append(servletUsername)
				.append(username).append(servletPassword)
				.append(userPassword);
		return sendURL.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mbank_main, menu);
		return true;
	}
	
	/*// For testing purpose
	protected void makeTestLogin(){
		String sendURL = createConnectionString("Lidiya",
				"12345");
		getClient = new GetClientFromServlet(MbankMainActivity.this);
		getClient.execute(sendURL);
	}*/

}
