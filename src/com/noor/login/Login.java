package com.noor.login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener 
{
    /** Called when the activity is first created. */
	Button login,signup;
	EditText uname,pwd;
	public Cursor c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
      }
	
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		uname=(EditText)findViewById(R.id.uname);
		pwd=(EditText)findViewById(R.id.pwd);
		Database1 db= new Database1(getApplicationContext());
		db.open();
		c=db.getAllTitles();
		String s1=uname.getText().toString();
		String s2=pwd.getText().toString();
		int row=0;
		l1:
		if(v.getId()==R.id.login)
		{
			
			while(c.moveToPosition(row))
			{
				if (s1.equals(c.getString(2))&&s2.equals(c.getString(3)))
				{
					Toast.makeText(this, "Success", 3000).show();
				    break l1;
				}
				else if (s1.equals(c.getString(2)))
				{
					Toast.makeText(this, "Pleas check the PASSWORD", 3000).show();
					break l1;
				}
				else if (s2.equals(c.getString(3)))
					{
					    Toast.makeText(this, "Pleas check the USERNAME", 3000).show();
						break l1;   
					}
				row++;
				}
							
			Toast.makeText(this, "PLEASE CHECK THE USERNAME & PASSWORD ", 3000).show();
			  }
		else if(v.getId()==R.id.signup)
		{
			Intent i1=new Intent(this,Signup.class);
			startActivity(i1);
		}	
		}	
}
	
	
