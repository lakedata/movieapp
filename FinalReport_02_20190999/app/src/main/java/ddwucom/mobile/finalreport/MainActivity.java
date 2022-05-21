// 과제명: 영화 리뷰 앱 – 영화 정보를 기록
// 분반: 02 분반
// 학번: 20190999 성명: 이지영
// 제출일: 2021년 6월 24일
package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ArrayList<MovieAll> MovieList;
    ListView listView;
    MyAdapter myAdapter;
    MovieDBManager movieDBManager; //MovieDBHelper안씀

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieList = new ArrayList<MovieAll>();

        //리스트뷰 불러오기
        listView = findViewById(R.id.customListView);
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, MovieList);
        listView.setAdapter(myAdapter);
        movieDBManager = new MovieDBManager(this);

     //클릭->수정
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override   // position 으로 몇번째 것이 선택됐는지 값을 넘겨준다
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             MovieAll movie = MovieList.get(position);
             Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
             intent.putExtra("movie", movie);
             startActivityForResult(intent, UPDATE_CODE);
         }
     });

        //롱클릭->삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_title)
                        .setMessage(MovieList.get(pos).getTitle() + "를 삭제하시겠습니까?")
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (movieDBManager.removeMovie(MovieList.get(pos).get_id())) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    MovieList.clear(); //화면갱신
                                    MovieList.addAll(movieDBManager.getAllMovie());
                                    myAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MovieList.clear();
        MovieList.addAll(movieDBManager.getAllMovie());
        myAdapter.notifyDataSetChanged();//갱신
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addMovie:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.developerIntro:
                Intent intentIntro = new Intent(this, IntroActivity.class);
                startActivity(intentIntro);
                break;
            case R.id.finish:
                new AlertDialog.Builder(this)
                        .setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
        }
        return true;
    }//optionMenu

    //음식 추가 (완료,취소)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    String title = data.getStringExtra("title");
                    Toast.makeText(this, title + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "제목 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}