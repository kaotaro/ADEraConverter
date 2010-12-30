package kao.android.app;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingDialogActivity extends Activity {

	static final int maxSeirekiYear = 2999;
	static final int minSeirekiYear = 1868;// 明治１年
	static final int minWarekiYear = 1;
	static final int maxMeijiYear = 45;
	static final int maxTaisyoYear = 15;
	static final int maxSyouwaYear = 64;
	static final int maxHeiseiYear = 100;

	static final int minMonth = 1;
	static final int maxMonth = 12;
	static final int minDay = 1;
	static final int maxDay1 = 31;
	static final int maxDay2 = 30;
	static final int maxDay3 = 28;
	static final int maxDay4 = 29;

	boolean seirekiFlag;
	int seirekiYear;
	String nengo;
	int warekiYear;
	int month;
	int day;

	RadioGroup radioGroup;
	Spinner sp;
	EditText yearEdit;
	ImageButton plusYearButton;
	ImageButton minusYearButton;

	EditText monthEdit;
	ImageButton plusMonthButton;
	ImageButton minusMonthButton;

	EditText dayEdit;
	ImageButton plusDayButton;
	ImageButton minusDayButton;

	OnClickListener buttonListener;
	
	OnTouchListener buttonTouchListener;
	Button setButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gdialog);
		
		createDialog();
	}

	private void createDialog() {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add(getResources().getText(R.string.heisei).toString());
		adapter.add(getResources().getText(R.string.syouwa).toString());
		adapter.add(getResources().getText(R.string.taisyo).toString());
		adapter.add(getResources().getText(R.string.meiji).toString());
		sp = (Spinner) findViewById(R.id.Spinner01);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				nengo = (String) spinner.getSelectedItem();

				// 選択した年号によって年のmax-1の値をセットしといたほうがいいかも
				if (nengo.equals(getResources().getText(R.string.heisei).toString())) {
					yearEdit.setText("" + warekiYear);
				}else if (nengo.equals(getResources().getText(R.string.syouwa).toString())){					
					yearEdit.setText("" + maxSyouwaYear);
				}else if (nengo.equals(getResources().getText(R.string.taisyo).toString())){
					yearEdit.setText("" + maxTaisyoYear);
				}else if (nengo.equals(getResources().getText(R.string.meiji).toString())){
					yearEdit.setText("" + maxMeijiYear);				
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		radioGroup = (RadioGroup) findViewById(R.id.type1);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.seireki) {
					sp.setVisibility(View.GONE);
					seirekiFlag = true;
					yearEdit.setText("" + seirekiYear);
				} else if (checkedId == R.id.wareki) {
					sp.setVisibility(View.VISIBLE);
					seirekiFlag = false;
					nengo = (String) sp.getSelectedItem();
					yearEdit.setText("" + warekiYear);
				}
			}
		});
		
		
		
//		buttonListener = new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (v == plusYearButton) {
//					int year = (Integer.valueOf(yearEdit.getText().toString())) + 1;
//					if (seirekiFlag) {
//						if (year == maxSeirekiYear + 1) {
//							year = minSeirekiYear;
//						}
//					} else {
//						if (nengo.equals((getResources().getText(
//								R.string.heisei).toString()))) {
//							if (year == maxHeiseiYear + 1) {
//								year = minWarekiYear;
//							}
//						} else if (nengo.equals((getResources().getText(
//								R.string.syouwa).toString()))) {
//							if (year == maxSyouwaYear + 1) {
//								year = minWarekiYear;
//							}
//						} else if (nengo.equals((getResources().getText(
//								R.string.taisyo).toString()))) {
//							if (year == maxTaisyoYear + 1) {
//								year = minWarekiYear;
//							}
//						} else if (nengo.equals((getResources().getText(
//								R.string.meiji).toString()))) {
//							if (year == maxMeijiYear + 1) {
//								year = minWarekiYear;
//							}
//						}
//					}
//					yearEdit.setText("" + year);
//				} else if (v == minusYearButton) {
//					int year = (Integer.valueOf(yearEdit.getText().toString())) - 1;
//					if (seirekiFlag) {
//						if (year == minSeirekiYear - 1) {
//							year = maxSeirekiYear;
//						}
//					} else {
//						if (nengo.equals((getResources().getText(
//								R.string.heisei).toString()))) {
//							if (year == minWarekiYear - 1) {
//								year = maxHeiseiYear;
//							}
//						} else if (nengo.equals((getResources().getText(
//								R.string.syouwa).toString()))) {
//							if (year == minWarekiYear - 1) {
//								year = maxSyouwaYear;
//							}
//						} else if (nengo.equals((getResources().getText(
//								R.string.taisyo).toString()))) {
//							if (year == minWarekiYear - 1) {
//								year = maxTaisyoYear;
//							}
//						} else if (nengo.equals((getResources().getText(
//								R.string.meiji).toString()))) {
//							if (year == minWarekiYear - 1) {
//								year = maxMeijiYear;
//							}
//						}
//					}
//					yearEdit.setText("" + year);
//				} else if (v == plusMonthButton) {
//					int month = (Integer
//							.valueOf(monthEdit.getText().toString())) + 1;
//					if (month == maxMonth + 1) {
//						month = minMonth;
//					}
//					monthEdit.setText("" + month);
//				} else if (v == minusMonthButton) {
//					int month = (Integer
//							.valueOf(monthEdit.getText().toString())) - 1;
//					if (month == minMonth - 1) {
//						month = maxMonth;
//					}
//					monthEdit.setText("" + month);
//				} else if (v == plusDayButton) {
//					int day = (Integer.valueOf(dayEdit.getText().toString())) + 1;
//					if (day == maxDay1 + 1) {
//						day = minDay;
//					}
//					dayEdit.setText("" + day);
//				} else if (v == minusDayButton) {
//					int day = (Integer.valueOf(dayEdit.getText().toString())) - 1;
//					if (day == minDay - 1) {
//						day = maxDay1;
//					}
//					dayEdit.setText("" + day);
//				}
//			}
//		};

		
		//TODO ボタンごとにタイマーが必要よ！！
		
		final Timer timer = new Timer(true);
		buttonTouchListener = new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					final View vv = v;
					final android.os.Handler handler = new android.os.Handler();
					  timer.schedule(
					   new TimerTask() {
					    @Override
					    public void run() {
					     handler.post( new Runnable(){
					      public void run(){
					      //タスクを記述
					    		if (vv == plusYearButton) {
									int year = (Integer.valueOf(yearEdit.getText().toString())) + 1;
									if (seirekiFlag) {
										if (year == maxSeirekiYear + 1) {
											year = minSeirekiYear;
										}
									} else {
										if (nengo.equals((getResources().getText(
												R.string.heisei).toString()))) {
											if (year == maxHeiseiYear + 1) {
												year = minWarekiYear;
											}
										} else if (nengo.equals((getResources().getText(
												R.string.syouwa).toString()))) {
											if (year == maxSyouwaYear + 1) {
												year = minWarekiYear;
											}
										} else if (nengo.equals((getResources().getText(
												R.string.taisyo).toString()))) {
											if (year == maxTaisyoYear + 1) {
												year = minWarekiYear;
											}
										} else if (nengo.equals((getResources().getText(
												R.string.meiji).toString()))) {
											if (year == maxMeijiYear + 1) {
												year = minWarekiYear;
											}
										}
									}
									yearEdit.setText("" + year);
								} else if (vv == minusYearButton) {
									int year = (Integer.valueOf(yearEdit.getText().toString())) - 1;
									if (seirekiFlag) {
										if (year == minSeirekiYear - 1) {
											year = maxSeirekiYear;
										}
									} else {
										if (nengo.equals((getResources().getText(
												R.string.heisei).toString()))) {
											if (year == minWarekiYear - 1) {
												year = maxHeiseiYear;
											}
										} else if (nengo.equals((getResources().getText(
												R.string.syouwa).toString()))) {
											if (year == minWarekiYear - 1) {
												year = maxSyouwaYear;
											}
										} else if (nengo.equals((getResources().getText(
												R.string.taisyo).toString()))) {
											if (year == minWarekiYear - 1) {
												year = maxTaisyoYear;
											}
										} else if (nengo.equals((getResources().getText(
												R.string.meiji).toString()))) {
											if (year == minWarekiYear - 1) {
												year = maxMeijiYear;
											}
										}
									}
									yearEdit.setText("" + year);
								} else if (vv == plusMonthButton) {
									int month = (Integer
											.valueOf(monthEdit.getText().toString())) + 1;
									if (month == maxMonth + 1) {
										month = minMonth;
									}
									monthEdit.setText("" + month);
								} else if (vv == minusMonthButton) {
									int month = (Integer
											.valueOf(monthEdit.getText().toString())) - 1;
									if (month == minMonth - 1) {
										month = maxMonth;
									}
									monthEdit.setText("" + month);
								} else if (vv == plusDayButton) {
									int day = (Integer.valueOf(dayEdit.getText().toString())) + 1;
									if (day == maxDay1 + 1) {
										day = minDay;
									}
									dayEdit.setText("" + day);
								} else if (vv == minusDayButton) {
									int day = (Integer.valueOf(dayEdit.getText().toString())) - 1;
									if (day == minDay - 1) {
										day = maxDay1;
									}
									dayEdit.setText("" + day);
								}	
					    		//タスクここまで
					      }
					     });
					    }
					   }, 0, 1000);  //初回起動の遅延と周期指定。単位はms	
				}
				
				if(event.getAction() == MotionEvent.ACTION_UP){
					if(timer != null){
						timer.cancel();
					}
				}
				return true;
			}
		};
		
		
		
		
		
		yearEdit = (EditText) findViewById(R.id.year);
		plusYearButton = (ImageButton) findViewById(R.id.plus_year);
		plusYearButton.setOnTouchListener(buttonTouchListener);
		
		minusYearButton = (ImageButton) findViewById(R.id.minus_year);
		minusYearButton.setOnTouchListener(buttonTouchListener);
		
		monthEdit = (EditText) findViewById(R.id.month);
		plusMonthButton = (ImageButton) findViewById(R.id.plus_month);
		plusMonthButton.setOnTouchListener(buttonTouchListener);
		minusMonthButton = (ImageButton) findViewById(R.id.minus_month);
		minusMonthButton.setOnTouchListener(buttonTouchListener);

		dayEdit = (EditText) findViewById(R.id.day);
		plusDayButton = (ImageButton) findViewById(R.id.plus_day);
		plusDayButton.setOnTouchListener(buttonTouchListener);
		minusDayButton = (ImageButton) findViewById(R.id.minus_day);
		minusDayButton.setOnTouchListener(buttonTouchListener);

		setButton = (Button) findViewById(R.id.set);
		setButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View button) {
				if (button == setButton) {
					String syear = ((EditText) findViewById(R.id.year))
							.getText().toString();
					String smonth = ((EditText) findViewById(R.id.month))
							.getText().toString();
					String sday = ((EditText) findViewById(R.id.day)).getText()
							.toString();

					if (seirekiFlag) {
						seirekiYear = Integer.valueOf(syear);
					} else {
						warekiYear = Integer.valueOf(syear);
					}
					month = Integer.valueOf(smonth);
					day = Integer.valueOf(sday);
					nengo = sp.getSelectedItem().toString();
					
					//パラメータチェック
					boolean result = checkParameter(seirekiFlag, seirekiYear, warekiYear, month,
							day, nengo);
					if(!result){
						return;
					}

					Intent intent = new Intent();
					intent.putExtra("AD_FLAG", seirekiFlag);
					if (!seirekiFlag) {
						intent.putExtra("NENGO", nengo);
						intent.putExtra("AD_YEAR", -1);
						intent.putExtra("ERA_YEAR", warekiYear);
					} else {
						intent.putExtra("NENGO", "-");
						intent.putExtra("AD_YEAR", seirekiYear);
						intent.putExtra("ERA_YEAR", -1);
					}
					intent.putExtra("AD_YEAR", seirekiYear);
					intent.putExtra("MONTH", month);
					intent.putExtra("DAY", day);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});

		// 現在時刻でフォームの初期化
		initForm();
	}

	private void initForm() {
		// 現在時刻でフォームの初期化
		seirekiFlag = true;
		Calendar today = Calendar.getInstance();
		seirekiYear = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		day = today.get(Calendar.DAY_OF_MONTH);
		Wareki wareki = new Wareki(seirekiYear, month, day);
		nengo = wareki.getStrGengou();
		warekiYear = wareki.getIWarekiYear();

		yearEdit.setText("" + seirekiYear);
		monthEdit.setText("" + month);
		dayEdit.setText("" + day);
	}

	public boolean checkParameter(boolean seirekiFlag, int seirekiYear,
			int warekiYear, int month, int day, String nengo) {
		// 西暦チェック
		if (seirekiFlag) {
			if (seirekiYear < minSeirekiYear || seirekiYear > maxSeirekiYear) {
				Toast.makeText(getApplication(), "入力した年の値が不正です",
						Toast.LENGTH_SHORT).show();
				return false;
			}
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			try {
				cal.set(seirekiYear, month - 1, day);
				cal.getTime();
			} catch (Exception e) {
				Toast.makeText(getApplication(), "入力した月日の値が不正です",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		} else {
			if (nengo.equals(getResources().getText(R.string.heisei).toString())) {
				if (warekiYear < minWarekiYear || warekiYear > maxHeiseiYear) {
					Toast.makeText(getApplication(), "入力した年の値が不正です",
							Toast.LENGTH_SHORT).show();
					return false;
				}
			}else if (nengo.equals(getResources().getText(R.string.syouwa).toString())){
				if (warekiYear < minWarekiYear || warekiYear > maxSyouwaYear) {
					Toast.makeText(getApplication(), "入力した年の値が不正です",
							Toast.LENGTH_SHORT).show();
					return false;
				}
			}else if (nengo.equals(getResources().getText(R.string.taisyo).toString())){
				if (warekiYear < minWarekiYear || warekiYear > maxTaisyoYear) {
					Toast.makeText(getApplication(), "入力した年の値が不正です",
							Toast.LENGTH_SHORT).show();
					return false;
				}
			}else if (nengo.equals(getResources().getText(R.string.meiji).toString())){
				if (warekiYear < minWarekiYear || warekiYear > maxMeijiYear) {
					Toast.makeText(getApplication(), "入力した年の値が不正です",
							Toast.LENGTH_SHORT).show();
					return false;
				}
			}
			//西暦の値を取得
			Wareki wareki = new Wareki(nengo, warekiYear, 1, 1);
			int seireki = wareki.getISeirekiYear();
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			try {
				cal.set(seireki, month - 1, day);
				cal.getTime();
			} catch (Exception e) {
				Toast.makeText(getApplication(), "入力した月日の値が不正です",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return true;
	}

}
