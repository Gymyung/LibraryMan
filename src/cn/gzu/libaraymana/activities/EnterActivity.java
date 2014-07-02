package cn.gzu.libaraymana.activities;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;
import cn.gzu.libaraymana.R;

	public class EnterActivity extends Activity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.enter_activity);
			TextView textView = (TextView) findViewById(R.id.tv_wjmm);
			textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

		}
	}


