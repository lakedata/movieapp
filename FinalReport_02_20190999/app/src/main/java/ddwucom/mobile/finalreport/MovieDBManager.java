package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManager {
    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManager(Context context) {
        movieDBHelper = new MovieDBHelper(context);
    }

    //DB의 모든 movie를 반환
    public ArrayList<MovieAll> getAllMovie() {
        ArrayList MovieList = new ArrayList();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID)); //인덱스로
            String title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String acotrs = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTORS));
            Float star = cursor.getFloat(cursor.getColumnIndex(MovieDBHelper.COL_STAR));
            String stroy = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_STORY));
            String releaseDate = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASEDATE));
            MovieList.add ( new MovieAll (id, title, director, acotrs, star, stroy, releaseDate) ); //ArrayList 복제본
        }

        cursor.close();
        movieDBHelper.close();

        return MovieList;
    }

    //DB에 새로운 movie 추가
    public boolean addNewMovie(MovieAll newMovie) {
        SQLiteDatabase myDB = movieDBHelper.getWritableDatabase();//읽기, 쓰기 전용
        ContentValues row = new ContentValues();

        //row.put(MovieDBHelper.COL_ID, newMovie.get_id());
        row.put(MovieDBHelper.COL_TITLE, newMovie.getTitle());
        row.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        row.put(MovieDBHelper.COL_ACTORS, newMovie.getActors());
        row.put(MovieDBHelper.COL_STAR, newMovie.getStar());
        row.put(MovieDBHelper.COL_STORY, newMovie.getStory());
        row.put(MovieDBHelper.COL_RELEASEDATE, newMovie.getReleaseDate());

        //                insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
        long count = myDB.insert(MovieDBHelper.TABLE_NAME, null, row);
        movieDBHelper.close();
        if(count > 0) return true;
        return false;
    }
    //    _id 를 기준으로 변경
    public boolean modifyMovie(MovieAll movie) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_TITLE, movie.getTitle());
        row.put(MovieDBHelper.COL_DIRECTOR, movie.getDirector());
        row.put(MovieDBHelper.COL_ACTORS, movie.getActors());
        row.put(MovieDBHelper.COL_STAR, movie.getStar());
        row.put(MovieDBHelper.COL_STORY, movie.getStory());
        row.put(MovieDBHelper.COL_RELEASEDATE, movie.getReleaseDate());

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(movie.get_id()) };
        int result = sqLiteDatabase.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        movieDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    //    _id 를 기준으로 DB에서 food 삭제
    public boolean removeMovie(long id) {
        SQLiteDatabase sqLiteDatabase =  movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(MovieDBHelper.TABLE_NAME, whereClause,whereArgs);
        movieDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    //    제목 이름으로 DB 검색
    public ArrayList<MovieAll> getMovieByTitle(String movieTitle) {
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String[] columns = {"title"};
        String selection = "title=?";
        String[] selectArgs = new String[] {movieTitle};
        Cursor cursor = db.query(MovieDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);

        cursor.close();
        db.close();
        return null;
       /* ArrayList<MovieAll> list = new ArrayList<>();

        String query
                = "SELECT title FROM "+ MovieDBHelper.TABLE_NAME + " WHERE title =" + "'"
                + movieTitle+ "'";

        Cursor cursor = db.rawQuery(query,null);

        //mCursor.moveToFirst();

        while(cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
        }

        list.add ( new MovieAll(title));
        cursor.close();
        movieDBHelper.close();
        return list;
        //return null;*/
    }

    //    id 로 DB 검색
    public MovieAll getMovieById(long id) {

        return  null;
    }

    //    close 수행
    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
