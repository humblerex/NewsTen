package news.newsten.newsten;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class Sports extends Fragment{

    GridView gridView;

    public Sports() {
        // Required empty public constructor
    }


    public static String[] gridViewStrings = {
            "BBC Sports",
            "ESPN",
            "Sky Sports News"

    };
    public static int[] gridViewImages = {
            R.mipmap.bbc_sport,
            R.mipmap.espn,
            R.mipmap.skysportsnews

    };





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


       View view = inflater.inflate(R.layout.content_sports, container, false);
        super.onCreate(savedInstanceState);


        gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new CustomGridAdapter (getActivity(), gridViewStrings, gridViewImages));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) {
                    Intent i = new Intent(getActivity(), Headlines.class);
                    i.putExtra("pos",0);
                    i.putExtra("cat",1);
                    getActivity().startActivity(i);
                }
                if(position==1) {
                    Intent i = new Intent(getActivity(), Headlines.class);
                    i.putExtra("pos",1);
                    i.putExtra("cat",1);
                    getActivity().startActivity(i);
                }

                if(position==2) {
                    Intent i = new Intent(getActivity(), Headlines.class);
                    i.putExtra("pos",2);
                    i.putExtra("cat",1);
                    getActivity().startActivity(i);
                }


            }
        });


        return view;
    }

}