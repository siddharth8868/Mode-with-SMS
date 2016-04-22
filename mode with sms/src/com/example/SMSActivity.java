package com.example;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMSActivity extends Activity {
	Button edit, save;
	EditText normal, silent, vibrate;
	SharedPreferences pref;
	SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		editor = pref.edit();
		edit = (Button) findViewById(R.id.edit);
		save = (Button) findViewById(R.id.save);
		normal = (EditText) findViewById(R.id.normalet);
		silent = (EditText) findViewById(R.id.silentet);
		vibrate = (EditText) findViewById(R.id.vibrateet);
		save.setVisibility(View.GONE);

		normal.setText(pref.getString("normal", "##nor*"));
		silent.setText(pref.getString("silent", "##sil*"));
		vibrate.setText(pref.getString("vibrate", "##vib*"));
	}

	public void edit(View v) {
		edit.setVisibility(View.GONE);
		save.setVisibility(View.VISIBLE);
		normal.setEnabled(true);
		silent.setEnabled(true);
		vibrate.setEnabled(true);
	}

	public void save(View v) {
		if (silent.getText().toString().equals("")) {
			editor.putString("silent", "##sil*");
		} else {
			editor.putString("silent", silent.getText().toString());
		}

		if (normal.getText().toString().equals("")) {
			editor.putString("normal", "##nor*");
		} else {
			editor.putString("normal", normal.getText().toString());
		}

		if (vibrate.getText().toString().equals("")) {
			editor.putString("vibrate", "##vib*");
		} else {
			editor.putString("vibrate", vibrate.getText().toString());
		}

		editor.commit();
		edit.setVisibility(View.VISIBLE);
		save.setVisibility(View.GONE);
		normal.setEnabled(false);
		silent.setEnabled(false);
		vibrate.setEnabled(false);
	}

}