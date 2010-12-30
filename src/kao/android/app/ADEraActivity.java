package kao.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class ADEraActivity extends Activity implements OnClickListener {

	public static final int REQUEST_CODE = 0;

	RadioGroup radioGroup;
	RadioButton rdSeireki;
	RadioButton rdWareki;
	Spinner sp;
	String nengo = "";

	Button goDialogButton;
	TextView input;
	TextView result;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adera);

		goDialogButton = (Button) findViewById(R.id.goDialog1);
		goDialogButton.setOnClickListener(this);
		input = (TextView) findViewById(R.id.input2);
		result = (TextView) findViewById(R.id.result2);
	}

	@Override
	public void onClick(View v) {
		if (v == goDialogButton) {
			Intent intent = new Intent(this, SettingDialogActivity.class);
			startActivityForResult(intent, REQUEST_CODE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE) {
			if (resultCode == RESULT_OK) {				
				boolean flag = data.getBooleanExtra("AD_FLAG", true);
				int rAdYear = data.getIntExtra("AD_YEAR", -1);
				int rEraYear = data.getIntExtra("ERA_YEAR", -1);
				int rMonth = data.getIntExtra("MONTH",-1);
				int rDay = data.getIntExtra("DAY",-1);
				  //西暦
				if(flag){
					input.setText("" + rAdYear + "年" + rMonth + "月" + rDay + "日");
					Wareki wareki = new Wareki(rAdYear, rMonth, rDay);
					String nengo = wareki.getStrGengou();
					int iWareki = wareki.getIWarekiYear();
					result.setText(nengo + "" + iWareki + "年" + rMonth + "月" + rDay + "日");
				}else{
					//和暦
					String nengo = data.getCharSequenceExtra("NENGO").toString();
					input.setText(nengo + "" + rEraYear + "年" + rMonth + "月" + rDay + "日");
					Wareki wareki = new Wareki(nengo, rEraYear, rMonth, rDay);
					int seireki = wareki.getISeirekiYear();
					result.setText("" + seireki + "年" + rMonth + "月" + rDay + "日");
				}
			}
		}
	}


}