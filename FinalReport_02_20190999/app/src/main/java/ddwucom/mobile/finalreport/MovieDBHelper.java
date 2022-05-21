package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MovieDBHelper extends SQLiteOpenHelper {

    final static String TAG = "MovieDBHelper";
    final static String DB_NAME = "movie.db";

    public final static String TABLE_NAME = "movie_table";
    public final static String COL_ID = "id";
    public final static String COL_TITLE = "title";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_ACTORS = "actors";
    public final static String COL_STAR = "star";
    public final static String COL_STORY = "story";
    public final static String COL_RELEASEDATE = "releaseDate";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " ( " + COL_ID + " integer primary key autoincrement, "
                + COL_TITLE + " text, " + COL_DIRECTOR + " text, " + COL_ACTORS + " text, " + COL_STAR + " integer, "
                + COL_STORY + " text, " + COL_RELEASEDATE + " date)";
        Log.d(TAG, sql);
        db.execSQL(sql);
        //임시 데이터
        db.execSQL("insert into " + TABLE_NAME + " values (null, '어바웃타임', '리차드 커디스', '도너 글리슨/레이첼 맥아담스', 4, '멜로', 2013);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '크루엘라', '크레이그', '엠마스톤', 5, '범죄', 2021);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '루카', '엔리코', '제이콥', 3, '애니', 2021);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '컨저링', '마이클', '베라', 2, '공포', 2021);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '소울', '피트', '제이미', 4, '애니', 2020);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
