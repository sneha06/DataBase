package us.jaaga.db;

import us.jaaga.db.SnehaDbAdapter.snehahelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	
	 EditText uname,password;
	 Button button;
	 SnehaDbAdapter shelper;
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
         uname = (EditText) findViewById(R.id.username);
         password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.submit);

      
        shelper = new SnehaDbAdapter(MainActivity.this);
      
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   public void submit(View view){
	   String name = uname.getText().toString();
	   String pword = password.getText().toString();
	   if(!name.isEmpty() && !pword.isEmpty()){
	  long id = shelper.insertData(name, pword);
	  if(id<0){
		  us.jaaga.db.Message.message(this, "Unsuccessfull");
	  } else {
		  us.jaaga.db.Message.message(this,"Data inserted successfully");
	  }
	   }
	   
   }
   
   public void viewDetails(View view){
	   
	   String Data = shelper.getAllData();
	   us.jaaga.db.Message.message(this,Data);
   }

}
