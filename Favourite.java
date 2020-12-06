package com.example.contactapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Favourite.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Favourite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favourite extends Fragment {

    static View VIEW = null;
    static private String USERNAME = null;
    public Favourite(String USERNAME){
        this.USERNAME = USERNAME;
    }
    static ListView myListView;
    static java.util.List<UserInfo> myUserList = new ArrayList<UserInfo>();
    String[] myMovies = {"A", "B", "C", "D", "A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2", "A3", "B3", "C3", "D3", "A", "B", "C", "D", "A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2", "A3", "B3", "C3", "D3"};

    public static void insert_fav(UserInfo fav){
        MainActivity.realm.beginTransaction();
        List_Model list_model = MainActivity.realm.createObject(List_Model.class);
        list_model.setName(fav.name);
        list_model.setDesc(fav.desc);
        list_model.setImageID(fav.imageID);
        list_model.setFavourite_of(USERNAME);
        MainActivity.realm.commitTransaction();
        myUserList.add(new UserInfo(fav.name, fav.desc, fav.imageID));
    }

    public static void updateList(){
        myUserList.clear();
        loadFavouriteList();
        MyCustomAdapter myAdapter = new MyCustomAdapter(VIEW.getContext(), myUserList);
        myListView.setAdapter(myAdapter);
    }

    public static void loadFavouriteList(){
        RealmResults<List_Model> results = MainActivity.realm.where(List_Model.class).findAll();
        for (List_Model i : results) {
            if(i != null && i.getFavourite_of().equalsIgnoreCase(USERNAME)){
                myUserList.add(new UserInfo(i.getName(), i.getDesc(), i.getImage()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        VIEW = view;
        myListView = view.findViewById(R.id.list);

//        myUserList.add(new UserInfo("Jason Statham", 19213453, R.drawable.jason));
//        myUserList.add(new UserInfo("Vin Deisel", 12782793, R.drawable.vin));
//        myUserList.add(new UserInfo("Tom Cruis", 41273842, R.drawable.tom));

        updateList();
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("Display Index "+ i);
                String valueUserClicked = myMovies[i];
                System.out.println("Value from User  "+ valueUserClicked);

//                Intent newScreen = new Intent(getApplicationContext(), ListViewDetails.class);
//
//                newScreen.putExtra("value", valueUserClicked);
//
//                startActivity(newScreen);
            }
        });
        return view;
    }
}
