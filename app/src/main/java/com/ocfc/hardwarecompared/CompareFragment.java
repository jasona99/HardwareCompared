package com.ocfc.hardwarecompared;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends Fragment {


    public CompareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compare, container, false);
        setSpinnerContent(view);

        return view;
    }
    private void setSpinnerContent( View view )
    {
        Spinner spinnerL = (Spinner) view.findViewById( R.id.spinnerL );
        Spinner spinnerR = (Spinner) view.findViewById( R.id.spinnerR );
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gpu_list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerL.setAdapter(adapter);
        spinnerR.setAdapter(adapter);
        spinnerL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0){ //NVIDIA
                    setText1("-", "-", "-", "-", "-");
                }
                if (pos == 1){ //Titan X
                    setText1("328 fps", "284 fps", "187 fps", "$1000", "250W");
                }
                if (pos == 2){ //980 Ti
                    setText1("337 fps", "294 fps", "198 fps", "$610", "250W");
                }
                if (pos == 3){ //980
                    setText1("242 fps", "222 fps", "168 fps", "$466", "165W");
                }
                if (pos == 4){ //970
                    setText1("200 fps", "187 fps", "153 fps", "$289", "145W");
                }
                if (pos == 5){ //960
                    setText1("128 fps", "122 fps", "82.4 fps", "$175", "120W");
                }
                if (pos == 6){
                    setText1("98.7 fps", "96.5 fps", "76.9 fps", "$140", "90W");
                }
                if (pos == 7){ //AMD
                    setText1("-", "-", "-", "-", "-");
                }
                if (pos == 8){ //Fury X
                    setText1("358 fps", "253 fps", "120 fps", "$640", "275W");
                }
                if (pos == 9){ //Fury
                    setText1("341 fps", "236 fps", "118 fps", "$570", "275W");
                }
                if (pos == 10){ //Nano
                    setText1("358 fps", "253 fps", "120 fps", "$640", "175W");
                }
                if (pos == 11){ //390X
                    setText1("278 fps", "183 fps", "132 fps", "$380", "275W");
                }
                if (pos == 12){ //390
                    setText1("254 fps", "166 fps", "128 fps", "$310", "275W");
                }
                if (pos == 13){ //380X
                    setText1("198 fps", "140 fps", "108 fps", "$230", "190W");
                }
                if (pos == 14){ //380
                    setText1("175 fps", "119 fps", "104 fps", "$180", "190W");
                }
                if (pos == 15){ ///370X
                    setText1("-", "-", "-", "-", "180W");
                }
                if (pos == 16){ //370
                    setText1("114 fps", "73.3 fps", "60.8 fps", "$130", "110W");
                }
                if (pos >=0 && pos < 7) {
                    ImageView imgL = (ImageView)(getView().findViewById(R.id.imageViewL));
                    imgL.setBackgroundResource(R.drawable.nvidia);

                }
                if (!(pos >=0 && pos < 7)) {
                    ImageView imgL = (ImageView)(getView().findViewById(R.id.imageViewL));
                    imgL.setBackgroundResource(R.drawable.amd);

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        spinnerR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0){ //NVIDIA
                    setText2("-", "-", "-", "-", "-");
                }
                if (pos == 1){ //Titan X
                    setText2("328 fps", "284 fps", "187 fps", "$1000", "250W");
                }
                if (pos == 2){ //980 Ti
                    setText2("337 fps", "294 fps", "198 fps", "$610", "250W");
                }
                if (pos == 3){ //980
                    setText2("242 fps", "222 fps", "168 fps", "$466", "165W");
                }
                if (pos == 4){ //970
                    setText2("200 fps", "187 fps", "153 fps", "$289", "145W");
                }
                if (pos == 5){ //960
                    setText2("128 fps", "122 fps", "82.4 fps", "$175", "120W");
                }
                if (pos == 6){
                    setText2("98.7 fps", "96.5 fps", "76.9 fps", "$140", "90W");
                }
                if (pos == 7){ //AMD
                    setText2("-", "-", "-", "-", "-");
                }
                if (pos == 8){ //Fury X
                    setText2("358 fps", "253 fps", "120 fps", "$640", "275W");
                }
                if (pos == 9){ //Fury
                    setText2("341 fps", "236 fps", "118 fps", "$570", "275W");
                }
                if (pos == 10){ //Nano
                    setText2("358 fps", "253 fps", "120 fps", "$640", "175W");
                }
                if (pos == 11){ //390X
                    setText2("278 fps", "183 fps", "132 fps", "$380", "275W");
                }
                if (pos == 12){ //390
                    setText2("254 fps", "166 fps", "128 fps", "$310", "275W");
                }
                if (pos == 13){ //380X
                    setText2("198 fps", "140 fps", "108 fps", "$230", "190W");
                }
                if (pos == 14){ //380
                    setText2("175 fps", "119 fps", "104 fps", "$180", "190W");
                }
                if (pos == 15){ ///370X
                    setText2("-", "-", "-", "-", "180W");
                }
                if (pos == 16){ //370
                    setText2("114 fps", "73.3 fps", "60.8 fps", "$130", "110W");
                }
                if (pos >=0 && pos < 7) {
                    ImageView imgR = (ImageView)(getView().findViewById(R.id.imageViewR));
                    imgR.setBackgroundResource(R.drawable.nvidia);

                }
                if (!(pos >=0 && pos < 7)) {
                    ImageView imgR = (ImageView)(getView().findViewById(R.id.imageViewR));
                    imgR.setBackgroundResource(R.drawable.amd);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }
    public void setText1(String text1, String text2, String text3, String text4, String text5){
        TextView textView1 = (TextView) getView().findViewById(R.id.textT1T1);
        textView1.setText(text1);
        TextView textView2 = (TextView) getView().findViewById(R.id.textT1T2);
        textView2.setText(text2);
        TextView textView3 = (TextView) getView().findViewById(R.id.textT1T3);
        textView3.setText(text3);
        TextView textView4 = (TextView) getView().findViewById(R.id.textT1T4);
        textView4.setText(text4);
        TextView textView5 = (TextView) getView().findViewById(R.id.textT1T5);
        textView5.setText(text5);
    }
    public void setText2(String text1, String text2, String text3, String text4, String text5){
        TextView textView1 = (TextView) getView().findViewById(R.id.textT2T1);
        textView1.setText(text1);
        TextView textView2 = (TextView) getView().findViewById(R.id.textT2T2);
        textView2.setText(text2);
        TextView textView3 = (TextView) getView().findViewById(R.id.textT2T3);
        textView3.setText(text3);
        TextView textView4 = (TextView) getView().findViewById(R.id.textT2T4);
        textView4.setText(text4);
        TextView textView5 = (TextView) getView().findViewById(R.id.textT2T5);
        textView5.setText(text5);
    }



}
