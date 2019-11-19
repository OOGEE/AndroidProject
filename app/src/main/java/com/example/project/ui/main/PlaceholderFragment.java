package com.example.project.ui.main;

import android.app.ProgressDialog;
import android.content.ContentValues;
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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;
import com.example.project.RequestHttpURLConnection;
import com.example.project.explain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    public String[]  result = new String[6];
    public String urlto = "http://toojs.asuscomm.com:8643/data/storeKindData/";
    private static final String ARG_SECTION_NUMBER = "section_number";

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
        NetworkTask networkTask = new NetworkTask(kindUrl, null,getArguments().getInt(ARG_SECTION_NUMBER)-1,root);
        networkTask.execute();
        //Log.d("try",result);
        //TextView view1 = (TextView) root.findViewById(R.id.textView2);
        //view1.setText(result[getArguments().getInt(ARG_SECTION_NUMBER)-1]);
        return root;
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        private Integer foodKind;
        private View root;
        Handler handler = new Handler();

        ProgressDialog asyncDialog = new ProgressDialog(getActivity());

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        public NetworkTask(String url, ContentValues values,Integer foodKind) {

            this.url = url;
            this.values = values;
            this.foodKind = foodKind;
        }

        public NetworkTask(String url, ContentValues values,Integer foodKind,View root) {

            this.url = url;
            this.values = values;
            this.foodKind = foodKind;
            this.root = root;
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result[foodKind] = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result[foodKind];
        }

        @Override
        protected void onPostExecute(String s) {
            asyncDialog.dismiss();
            //TextView view1 = (TextView) root.findViewById(R.id.textView2);
            //view1.setText(result[foodKind]);
            try {
                JSONArray jsonArray = new JSONArray(result[foodKind]);
                LinearLayout scrollView = (LinearLayout) root.findViewById(R.id.attach_point);
                for(int i=0;i<jsonArray.length();i++){
                        final JSONObject jsonObjects = jsonArray.getJSONObject(i);
                        Button button = new Button(getActivity());
                        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        button.setLayoutParams(lp);
                        button.setText(jsonObjects.getString("name"));
                        Log.d("image", "make point");
                        scrollView.addView(button);
                        getInternetImage(jsonObjects.getString("mainphotourl"), button);
                        button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v){
                            Intent intent = new Intent(getActivity().getApplicationContext(), explain.class);
                            try {
                                intent.putExtra("name", jsonObjects.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            startActivity(intent);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }

        public void getInternetImage(final String urlSource, final Button button){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {    // 오래 거릴 작업을 구현한다
                    // TODO Auto-generated method stub
                    try{
                        // 걍 외우는게 좋다 -_-;
                        Log.d("image","image get succes : " + urlSource);
                        URL url = new URL(urlSource);

                        InputStream is = url.openStream();

                        final Bitmap bm = BitmapFactory.decodeStream(is);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {  // 화면에 그려줄 작업
                                Drawable buttonImage = new BitmapDrawable(getResources(), bm);
                                buttonImage.setBounds(0, 0, 120, 120);
                                button.setCompoundDrawables(buttonImage, null, null, null);
                            }
                        });
                    } catch(Exception e){
                    }
                }
            });
            t.start();
        }

    }
}