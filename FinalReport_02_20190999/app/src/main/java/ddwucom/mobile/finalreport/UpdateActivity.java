package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    MovieAll movie;

    EditText etUpdateTitle;
    EditText etUpdateDirector;
    EditText etUpdateActors;
    RatingBar ratingBar;
    EditText etUpdateStory;
    EditText etUpdateReleaseDate;

    ImageView UpimageView;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //전달받은 값
        movie = (MovieAll) getIntent().getSerializableExtra("movie");

        etUpdateTitle = findViewById(R.id.etUpdateTitle);
        etUpdateDirector = findViewById(R.id.etUpdateDirector);
        etUpdateActors = findViewById(R.id.etUpdateActors);
        ratingBar = findViewById(R.id.ratingBar);
        etUpdateStory = findViewById(R.id.etUpdateStory);
        etUpdateReleaseDate = findViewById(R.id.etUpdateReleaseDate);

        UpimageView = (ImageView)findViewById(R.id.UpimageView);

        etUpdateTitle.setText(movie.getTitle());
        etUpdateDirector.setText(movie.getDirector());
        etUpdateActors.setText(movie.getActors());
        ratingBar.setRating(movie.getStar());
        etUpdateStory.setText(movie.getStory());
        etUpdateReleaseDate.setText(movie.getReleaseDate());

        movieDBManager = new MovieDBManager(this);

        if(etUpdateTitle.getText().toString().equals("어바웃타임"))
            UpimageView.setImageResource(R.drawable.mvimage1);
        else if(etUpdateTitle.getText().toString().equals("크루엘라"))
            UpimageView.setImageResource(R.drawable.mvimage2);
        else if(etUpdateTitle.getText().toString().equals("루카"))
            UpimageView.setImageResource(R.drawable.mvimage3);
        else if(etUpdateTitle.getText().toString().equals("컨저링"))
            UpimageView.setImageResource(R.drawable.mvimage4);
        else if(etUpdateTitle.getText().toString().equals("소울"))
            UpimageView.setImageResource(R.drawable.mvimage5);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_update:
                movie.setTitle(etUpdateTitle.getText().toString());
                movie.setDirector(etUpdateDirector.getText().toString());
                movie.setActors(etUpdateActors.getText().toString());
                movie.setStar(ratingBar.getRating());
                movie.setStory(etUpdateStory.getText().toString());
                movie.setReleaseDate(etUpdateReleaseDate.getText().toString());

                if (movieDBManager.modifyMovie(movie)) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("movie", movie);
                    setResult(RESULT_OK, resultIntent);
                } else {
                    setResult(RESULT_CANCELED);
                }
                if(etUpdateTitle.getText().toString().equals("") || etUpdateTitle.getText().toString() == null
                        ||etUpdateDirector.getText().toString().equals("") || etUpdateDirector.getText().toString() == null
                        ||etUpdateActors.getText().toString().equals("") || etUpdateActors.getText().toString() == null
                        ||ratingBar.getRating() == 0
                        ||etUpdateStory.getText().toString().equals("") || etUpdateStory.getText().toString() == null
                        ||etUpdateReleaseDate.getText().toString().equals("") || etUpdateReleaseDate.getText().toString() == null
                )
                    Toast.makeText(this, "필수항목을 입력하세요.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
