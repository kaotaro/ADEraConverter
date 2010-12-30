package kao.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TopActivity extends Activity implements OnClickListener{

	ImageButton infoButton;
	ImageButton aderaButton;
	ImageButton oldButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        infoButton = (ImageButton) findViewById(R.id.info);
        infoButton.setOnClickListener(this);
        aderaButton = (ImageButton) findViewById(R.id.adera);
        aderaButton.setOnClickListener(this);
        oldButton = (ImageButton) findViewById(R.id.howold);
        oldButton.setOnClickListener(this);
    }


	@Override
	public void onClick(View v) {
		if(v == infoButton){
			Intent intent = new Intent(this, InfoActivity.class);
			startActivity(intent);
		}else if(v == aderaButton){
			Intent intent = new Intent(this, ADEraActivity.class);
			startActivity(intent);
		}else if(v == oldButton){
			Intent intent = new Intent(this, HowOldActivity.class);
			startActivity(intent);
		}
	}
}