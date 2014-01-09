package com.noor.login;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	public static  ArrayList<String> DList=new ArrayList<String>();
	Button create;
	EditText fname,lname,uname,pwd;
	public Cursor c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        uname=(EditText)findViewById(R.id.uname);
        pwd=(EditText)findViewById(R.id.pwd);
        create=(Button)findViewById(R.id.create);
        create.setOnClickListener(this);
         
    }
	
	public void onClick(View arg0)
	{
	     	// TODO Auto-generated method stub
		  //Toast.makeText(this, "Your Account created", 3000).show();
		  Database1 db=new Database1(this);
		  db.open();
		  long id;
		  id = db.InsertTitles(fname.getText().toString(),        		
				  lname.getText().toString(),uname.getText().toString(),pwd.getText().toString());
		  System.out.println(id);
		  c=db.getAllTitles();
		  if(c.moveToFirst())
		  
		  {
		do{	  
			  
				  DList.add(c.getString(0));
	              DList.add( c.getString(1));
	             DList.add( c.getString(2));
	              DList.add( c.getString(3));
	    	
	              //Toast.makeText(this, "Your Account created"+DList, 3000).show();
		}
			  while(c.moveToNext());
		
			 Intent i1=new Intent(this,Login.class);
			  startActivity(i1);
		  
		 }
	}

}
