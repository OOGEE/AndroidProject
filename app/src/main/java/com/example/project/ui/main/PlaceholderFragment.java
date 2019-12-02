package com.example.project.ui.main;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.NetworkTask;
import com.example.project.R;
import com.example.project.RequestHttpURLConnection;
import com.example.project.explain;
import com.example.project.foodStoreListData;
import com.example.project.foodStroeLIstAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    public String[]  result = new String[6];
    public String urlto = "http://toojs.asuscomm.com:8643/data/storeKindData/";
    private static final String ARG_SECTION_NUMBER = "section_number";
    public ArrayList<foodStoreListData[]> foodStoreListData = new ArrayList<foodStoreListData[]>();

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
        index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
    @NonNull LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        String kindUrl = urlto + (getArguments().getInt(ARG_SECTION_NUMBER)-1);
        NetworkTask networkTask = new NetworkTask(kindUrl, null,getArguments().getInt(ARG_SECTION_NUMBER)-1,root,getActivity());
        networkTask.execute();
        return root;
    }
}