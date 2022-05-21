package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;//AdapterView 항목에 대한 layout
    private ArrayList<MovieAll> MovieList;//원본데이터리스트
    private LayoutInflater layoutInflater;//inflater 객체

    public MyAdapter(Context context, int layout, ArrayList<MovieAll> MovieList) {
        this.context = context;
        this.layout = layout;
        this.MovieList = MovieList;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return MovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return MovieList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if(convertView == null) { //재사용
            convertView = layoutInflater.inflate(layout, parent, false);
        }

        ImageView tvImage = convertView.findViewById(R.id.tvImage); //미리 찾아서 보관
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvDirector = convertView.findViewById(R.id.tvDirector);

        //tvImage.setImageResource(MovieList.get(position).get_id());
        tvTitle.setText(MovieList.get(position).getTitle());
        tvDirector.setText(MovieList.get(position).getDirector());

        if(MovieList.get(position).getTitle().equals("어바웃타임")) //이미지 값
            tvImage.setImageResource(R.drawable.mvimage1);
        if(MovieList.get(position).getTitle().equals("크루엘라")) //이미지 값
            tvImage.setImageResource(R.drawable.mvimage2);
        if(MovieList.get(position).getTitle().equals("루카")) //이미지 값
            tvImage.setImageResource(R.drawable.mvimage3);
        if(MovieList.get(position).getTitle().equals("컨저링")) //이미지 값
            tvImage.setImageResource(R.drawable.mvimage4);
        if(MovieList.get(position).getTitle().equals("소울")) //이미지 값
            tvImage.setImageResource(R.drawable.mvimage5);


        return convertView;
    }
}
