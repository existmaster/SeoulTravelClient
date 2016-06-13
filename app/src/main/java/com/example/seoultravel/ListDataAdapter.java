package com.example.seoultravel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 성열 on 2016-06-13.
 */
public class ListDataAdapter extends BaseAdapter {

    ArrayList<ListData> datas;
    LayoutInflater inflater;

    public ListDataAdapter(LayoutInflater inflater, ArrayList<ListData> datas) {
        // TODO Auto-generated constructor stub
        this.datas= datas;
        this.inflater= inflater;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size(); //datas의 개수를 리턴
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);//datas의 특정 인덱스 위치 객체 리턴.
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if( convertView==null){
            convertView= inflater.inflate(R.layout.list_row, null);
        }

        TextView text_name= (TextView)convertView.findViewById(R.id.text_subject);
        TextView text_nation= (TextView)convertView.findViewById(R.id.text_content);
        ImageView img_flag= (ImageView)convertView.findViewById(R.id.img_flag);

        String uri = "drawable/korea";

        text_name.setText( datas.get(position).getSubject() );
        text_nation.setText( datas.get(position).getContent() );
        img_flag.setImageResource( datas.get(position).getImgId() );

        return convertView;
    }

}
