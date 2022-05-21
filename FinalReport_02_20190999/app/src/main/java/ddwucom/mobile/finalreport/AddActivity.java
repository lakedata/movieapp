package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText etAddTitle;
    EditText etAddDirector;
    EditText etAddActors;
    RatingBar ratingBar;
    EditText etAddStory;
    EditText etAddReleaseDate;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etAddTitle = findViewById(R.id.etAddTitle);
        etAddDirector = findViewById(R.id.etAddDirector);
        etAddActors = findViewById(R.id.etAddActors);
        ratingBar = findViewById(R.id.ratingBar);
        etAddStory = findViewById(R.id.etAddStory);
        etAddReleaseDate = findViewById(R.id.etAddReleaseDate);

        movieDBManager = new MovieDBManager(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                boolean result = movieDBManager.addNewMovie(
                        new MovieAll( R.drawable.mv, etAddTitle.getText().toString(),
                                etAddDirector.getText().toString(), etAddActors.getText().toString(),
                                ratingBar.getRating(), etAddStory.getText().toString(),
                                etAddReleaseDate.getText().toString()));

                if(etAddTitle.getText().toString().equals("") || etAddTitle.getText().toString() == null
                        ||etAddDirector.getText().toString().equals("") || etAddDirector.getText().toString() == null
                        ||etAddActors.getText().toString().equals("") || etAddActors.getText().toString() == null
                        ||ratingBar.getRating() == 0
                        ||etAddStory.getText().toString().equals("") || etAddStory.getText().toString() == null
                        ||etAddReleaseDate.getText().toString().equals("") || etAddReleaseDate.getText().toString() == null
                )
                    Toast.makeText(this, "필수항목을 입력하세요.", Toast.LENGTH_SHORT).show();


                    if (result) {    // 정상수행에 따른 처리
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("title", etAddTitle.getText().toString() );
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {        // 이상에 따른 처리
                    Toast.makeText(this, "새로운 추가 실패!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
