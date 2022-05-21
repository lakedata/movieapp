package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btn1 = (Button) findViewById(R.id.btnHello);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "안녕하세요, 저는 이지영입니다. 만나서 반갑습니다.";
                Toast.makeText(IntroActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onMyExitBtnClick (View v) {
        finish();
    }
}
