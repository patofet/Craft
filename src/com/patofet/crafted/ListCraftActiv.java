package com.patofet.crafted;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ListCraftActiv extends Activity {
	TextView princ;
	Button b;
	llista l;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		l=null;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_craft_layo);
		princ=(TextView)findViewById(R.id.princ);
		//b=(Button)findViewById(R.id.princ);
		//b.setO
		try{
		l=getIntent().getParcelableExtra("llista");
		if(l==null)//{
			princ.setText("nop\n\n");
		else
			princ.setText("get extrassss\n\n\n");
			
			l=llegirFicher.llegir(getAssets().open("list.txt"));}catch(Exception e){ princ.setText(princ.getText()+"\n\n Err:"+e.toString()); }
		//}else{
			
		//}
		
		/*princ.setText(princ.getText()+l.getlinea()+"\n"+l.getActual().getNom());
		for(int i=0;i<l.getActual().totalCraft();i++)
			princ.setText(princ.getText()+"\n"+l.getActual().actualSeguentCraft().getObjecte(0).getItemComplet());
	    l.seguent();
	    princ.setText(princ.getText()+"\n\n"+l.getlinea()+"\n"+l.getActual().getNom());
	    
	    for(int i=0;i<l.getActual().totalCraft();i++)
			princ.setText(princ.getText()+"\n"+l.getActual().actualSeguentCraft().getObjecte(0).getItemComplet());*/
	    
	   princ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ListCraftActiv.this, ListCraftActiv.class);
			    myIntent.putExtra("llista", l);
		        startActivity(myIntent);
				
			}
		});
	}
	public void text(View button){
		Intent myIntent = new Intent(this, ListCraftActiv.class);
	    myIntent.putExtra("llista", l);
        startActivity(myIntent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_craft, menu);
		return true;
	}

}


