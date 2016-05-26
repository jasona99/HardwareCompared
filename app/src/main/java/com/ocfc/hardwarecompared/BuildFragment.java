package com.ocfc.hardwarecompared;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuildFragment extends Fragment {


    private int costGPU;
    private int wattsGPU;
    private int wattsCPU;
    private int costCPU;
    private int wattsCooler;
    private int amtRAM;
    private int costCooler;
    private int amtWatts;
    private int SSDCost;
    private int SSDnum;
    private int HDDCost;
    private int HDDnum;
    private int HDDsizes;
    private int SSDsizes;

    public BuildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_build, container, false);
        setSpinnerContent(view);
        final SeekBar seekCPU = (SeekBar) view.findViewById(R.id.seekCPU);
        final TextView textCPU = (TextView) view.findViewById(R.id.textCPUPrice);
        final TextView textGPU = (TextView) view.findViewById(R.id.textGPUPrice);
        final TextView textCPUN = (TextView) view.findViewById(R.id.textCPUName);
        final TextView textGPUN = (TextView) view.findViewById(R.id.textGPUName);
        final SeekBar seekGPU = (SeekBar) view.findViewById(R.id.seekGPU);
        final ToggleButton toggleCPU = (ToggleButton) view.findViewById(R.id.toggleCPU);
        final ToggleButton toggleGPU = (ToggleButton) view.findViewById(R.id.toggleGPU);
        final ToggleButton toggleCooler = (ToggleButton) view.findViewById(R.id.toggleCooler);
        final Spinner spinnerRAM = (Spinner) view.findViewById(R.id.spinnerRAM);
        final Spinner spinnerHDD = (Spinner) view.findViewById(R.id.spinnerHDD);
        final Spinner spinnerSSD = (Spinner) view.findViewById(R.id.spinnerSSD);
        final SeekBar seekHDD = (SeekBar) view.findViewById(R.id.seekBarHDD);
        final SeekBar seekSSD = (SeekBar) view.findViewById(R.id.seekBarSSD);
        final TextView textPrice = (TextView) view.findViewById(R.id.textPrice);
        final TextView SSDSize = (TextView) view.findViewById(R.id.textSSDSize);
        final TextView HDDSize = (TextView) view.findViewById(R.id.textHDDSize);
        final TextView totalWattage = (TextView) view.findViewById(R.id.textWattage);
        boolean CPUBrand = false;
        boolean GPUBrand = false;
        SSDnum = 0;
        SSDCost= 0;
        wattsGPU = 0;
        costGPU = 0;
        costCPU = 0;
        wattsCPU = 0;
        wattsCooler = 0;
        amtRAM = 4;
        HDDCost = 0;
        HDDnum = 0;
        HDDsizes = 0;
        SSDsizes = 0;
        setWatts(view);
        setPrice(textPrice);
        final TextView chipset = (TextView) view.findViewById(R.id.textMBChipset);
        amtWatts = 0;
        SSDSize.setText("128 GB");
        HDDSize.setText("512 GB");


        // CPU Stuff

        CPUCalc(seekCPU.getProgress(), CPUBrand, textCPUN, view);
        chipset.setText("");
        textCPU.setText("0");
        textGPUN.setText("NVIDIA");
        seekCPU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            boolean tempB;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;

                textCPU.setText(progress + "");
                if (toggleCPU.getText().equals("AMD")){
                    tempB = true;
                }
                if (toggleCPU.getText().equals("Intel")){
                    tempB = false;
                }
                CPUCalc(progress, tempB, textCPUN, view);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textCPU.setText(progress + "");

            }
        });

        //GPU Stuff


        textGPU.setText("0");

        seekGPU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            boolean tempB;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;

                textGPU.setText(progress + "");
                if (toggleGPU.getText().equals("AMD")){
                    tempB = true;
                }
                if (toggleGPU.getText().equals("NVIDIA")){
                    tempB = false;
                }
                GPUCalc(progress, tempB, textGPUN, view);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textGPU.setText(progress + "");

            }
        });

        //CPU Toggle

        toggleCPU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int progress;
                if (isChecked) {
                    progress = seekCPU.getProgress();
                    CPUCalc(progress, true, textCPUN, view);
                } else {
                    progress = seekCPU.getProgress();
                    CPUCalc(progress, false, textCPUN, view);
                }
            }
        });

        //GPU Toggle

        toggleGPU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int progress;
                if (isChecked) {
                    progress = seekGPU.getProgress();
                    GPUCalc(progress, true, textGPUN, view);
                } else {
                    progress = seekGPU.getProgress();
                    GPUCalc(progress, false, textGPUN, view);
                }
            }
        });

        //Cooler Toggle

        toggleCooler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    costCooler = 50;
                    wattsCooler = 25;
                } else {
                    costCooler = 0;
                    wattsCooler = 10;
                }
                setPrice(textPrice);
            }
        });

        //RAM Spinner

        spinnerRAM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) { //
                    amtRAM = 4;
                }
                if (pos == 1) { //
                    amtRAM = 8;
                }
                if (pos == 2) { //
                    amtRAM = 16;
                }
                if (pos == 3) { //
                    amtRAM = 32;
                }
                if (pos == 4) { //
                    amtRAM = 64;
                }
                setPrice(textPrice);
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                //Necessary... add nothing.
            }
        });

        spinnerSSD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) { //
                    SSDnum = 0;
                }
                if (pos == 1) { //
                    SSDnum = 1;
                }
                if (pos == 2) { //
                    SSDnum = 2;
                }
                if (pos == 3) { //
                    SSDnum = 3;
                }
                if (pos == 4) { //
                    SSDnum = 4;
                }
                if (pos == 5) { //
                    SSDnum = 5;
                }
                if (pos == 6) { //
                    SSDnum = 6;
                }
                if (pos == 7) { //
                    SSDnum = 7;
                }
                if (pos == 8) { //
                    SSDnum = 8;
                }
                SSDCalc(SSDsizes, SSDnum, SSDSize, textPrice, view, totalWattage);
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                //Necessary... add nothing.
            }
        });

        spinnerHDD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) { //
                    HDDnum = 0;
                }
                if (pos == 1) { //
                    HDDnum = 1;
                }
                if (pos == 2) { //
                    HDDnum = 2;
                }
                if (pos == 3) { //
                    HDDnum = 3;
                }
                if (pos == 4) { //
                    HDDnum = 4;
                }
                if (pos == 5) { //
                    HDDnum = 5;
                }
                if (pos == 6) { //
                    HDDnum = 6;
                }
                if (pos == 7) { //
                    HDDnum = 7;
                }
                if (pos == 8) { //
                    SSDnum = 8;
                }
                HDDCalc(HDDsizes, HDDnum, HDDSize, textPrice, view, totalWattage);
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                //Necessary... add nothing.
            }
        });

        seekSSD.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            final TextView sizeSSD = (TextView) view.findViewById(R.id.textSSDSize);
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                SSDsizes = progressValue;
                SSDCalc(progressValue, SSDnum, SSDSize, textPrice, view, totalWattage);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        seekHDD.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            final TextView sizeSSD = (TextView) view.findViewById(R.id.textHDDSize);
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                HDDsizes = progressValue;
                HDDCalc(HDDsizes, HDDnum, HDDSize, textPrice, view, totalWattage);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        return view;
    }



    private void setSpinnerContent(View view) {
        Spinner spinnerRAM = (Spinner) view.findViewById(R.id.spinnerRAM);
        Spinner spinnerHDD = (Spinner) view.findViewById(R.id.spinnerHDD);
        Spinner spinnerSSD = (Spinner) view.findViewById(R.id.spinnerSSD);
        ArrayAdapter<CharSequence> adapterRAM = ArrayAdapter.createFromResource(getContext(),
                R.array.ram_list, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterStorage = ArrayAdapter.createFromResource(getContext(),
                R.array.storage_list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterRAM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterStorage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHDD.setAdapter(adapterStorage);
        spinnerSSD.setAdapter(adapterStorage);
        spinnerRAM.setAdapter(adapterRAM);
    }

    private void CPUCalc(int progress, boolean CPUBrand, TextView textCPUN, View view) {
        final TextView chipset = (TextView) view.findViewById(R.id.textMBChipset);
        final TextView totalWattage = (TextView) view.findViewById(R.id.textWattage);
        final TextView textPrice = (TextView) view.findViewById(R.id.textPrice);
        if (CPUBrand ==true){
            textCPUN.setText("AMD");
            if (progress >= 90 && progress < 125){
                textCPUN.setText("FX 6300 - $90");
                costCPU = 90;
                wattsCPU = 95;
                chipset.setText("AM3");
            }
            if (progress >= 125 && progress < 150){
                textCPUN.setText("FX 8320 - $125");
                costCPU = 125;
                wattsCPU = 125;
                chipset.setText("AM3");
            }
            if (progress >= 150 && progress < 208){
                textCPUN.setText("FX 8350 - $150");
                costCPU = 150;
                wattsCPU = 125;
                chipset.setText("AM3");
            }
            if (progress >= 208 && progress <=1200){
                textCPUN.setText("FX 9590 - $208");
                costCPU = 208;
                wattsCPU = 220;
                chipset.setText("AM3 - X");
            }

        }
        if (CPUBrand ==false){
            textCPUN.setText("Intel");
            if (progress >= 70 && progress <125){
                textCPUN.setText("Pentium G3258 - $70");
                costCPU = 70;
                wattsCPU = 53;
                chipset.setText("Intel Z97");

            }
            if (progress >= 125 && progress < 245){
                textCPUN.setText("Core i3-6100 - $125");
                costCPU = 125;
                wattsCPU = 65;
                chipset.setText("Intel Z170");
            }
            if (progress >= 245 && progress < 340){
                textCPUN.setText("Core i5-6600K - $245");
                costCPU = 245;
                wattsCPU = 91;
                chipset.setText("Intel Z170");
            }
            if (progress >= 340 && progress < 370){
                textCPUN.setText("Core i7-6700K - $340");
                costCPU = 340;
                wattsCPU = 91;
                chipset.setText("Intel Z170");
            }
            if (progress >= 370 && progress < 555){
                textCPUN.setText("Core i7-5820KK - $370");
                costCPU = 370;
                wattsCPU = 140;
                chipset.setText("Intel X99");
            }
            if (progress >= 555 && progress < 1000){
                textCPUN.setText("Core i7-5930K - $555");
                costCPU = 555;
                wattsCPU = 140;
                chipset.setText("Intel X99");
            }
            if (progress >= 1000 && progress <= 1200){
                textCPUN.setText("Core i7-5960X - $1000");
                costCPU = 1000;
                wattsCPU = 140;
                chipset.setText("Intel X99");
            }
        }
        setWatts(view);
        setPrice(textPrice);
    }
    private void GPUCalc(int progress, boolean GPUBrand, TextView textGPUN, View view) {
        final TextView textPrice = (TextView) view.findViewById(R.id.textPrice);
        if (GPUBrand ==true){
            textGPUN.setText("AMD");
            if (progress >= 640&& progress<=1200) {
                textGPUN.setText("R9 Fury or R9 Nano - $640");
                costGPU = 640;
                wattsGPU = 275;
            }
            if (progress >= 570&& progress<640) {
                textGPUN.setText("R9 Fury - $570");
                costGPU = 570;
                wattsGPU = 275;
            }
            if (progress >= 380&& progress<570) {
                textGPUN.setText("R9 390X - $380");
                costGPU = 380;
                costGPU = 275;
            }
            if (progress >= 310&& progress<380) {
                textGPUN.setText("R9 390 - $310");
                costGPU = 310;
                wattsGPU = 275;
            }
            if (progress >= 230&& progress<310) {
                textGPUN.setText("R9 380X - $230");
                costGPU = 230;
                wattsGPU = 190;
            }
            if (progress >= 180 && progress<230) {
                textGPUN.setText("R9 380 - $180");
                costGPU = 180;
                wattsGPU = 190;
            }
            if (progress >= 130&& progress<180) {
                textGPUN.setText("R9 370 - $130");
                costGPU = 130;
                wattsGPU = 110;
            }
        }
        if (GPUBrand ==false){
            textGPUN.setText("NVIDIA");
            if (progress >= 1000&& progress<=1200) {
                textGPUN.setText("GTX Titan X - $1000");
                costGPU = 1000;
                wattsGPU = 250;
            }
            if (progress >= 610&& progress<1000) {
                textGPUN.setText("GTX 980 Ti - $610");
                costGPU = 610;
                wattsGPU = 250;
            }
            if (progress >= 466&& progress<610) {
                textGPUN.setText("GTX 980 - $466");
                costGPU = 466;
                wattsGPU = 165;
            }
            if (progress >= 289&& progress<466) {
                textGPUN.setText("GTX 970 - $289");
                costGPU = 289;
                wattsGPU = 145;
            }
            if (progress >= 175&& progress<289) {
                textGPUN.setText("GTX 960 - $175");
                costGPU = 175;
                wattsGPU = 120;
            }
            if (progress >= 140&& progress<175) {
                textGPUN.setText("GTX 950 - $140");
                costGPU = 140;
                wattsGPU = 90;
            }
        }
        setWatts(view);
        setPrice(textPrice);
    }
    private void SSDCalc(int progressValue, int num, TextView textSSDSize, TextView textPrice, View view, TextView totalWattage){

        if (progressValue == 0){
            textSSDSize.setText("128 GB");
            SSDCost = 60*SSDnum;
        }
        if (progressValue == 1){
            textSSDSize.setText("256 GB");
            SSDCost = 90*SSDnum;
        }
        if (progressValue == 2){
            textSSDSize.setText("512 GB");
            SSDCost = 150*SSDnum;
        }
        if (progressValue == 3){
            textSSDSize.setText("1 TB");
            SSDCost = 280*SSDnum;
        }
        if (progressValue == 4){
            textSSDSize.setText("2 TB");
            SSDCost = 480*SSDnum;
        }
        setPrice(textPrice);
        setWatts(totalWattage);
    }
    private void HDDCalc (int progressValue, int num, TextView sizeHDD, TextView textPrice, View view, TextView totalWattage){

        if (progressValue == 0){
            sizeHDD.setText("512 GB");
            HDDCost = 35*HDDnum;
        }
        if (progressValue == 1){
            sizeHDD.setText("1 TB");
            HDDCost = 45*HDDnum;
        }
        if (progressValue == 2){
            sizeHDD.setText("2 TB");
            HDDCost = 80*HDDnum;
        }
        if (progressValue == 3){
            sizeHDD.setText("3 TB");
            HDDCost = 130*HDDnum;
        }
        setPrice(textPrice);
        setWatts(totalWattage);
    }
    private void setWatts (View view) {
        final TextView totalWattage = (TextView) view.findViewById(R.id.textWattage);
        amtWatts = (int)((wattsGPU+wattsCPU+40+wattsCooler)*1.3);
        totalWattage.setText((int)((wattsGPU+wattsCPU+40+wattsCooler+(SSDnum*5)+(HDDnum*10))*1.25)+"");

    }
    private void setWatts (TextView totalWattage) {
        amtWatts = (int)((wattsGPU+wattsCPU+40+wattsCooler)*1.3);
        totalWattage.setText((int)((wattsGPU+wattsCPU+40+wattsCooler+(SSDnum*5)+(HDDnum*10))*1.25)+"");

    }
    private void setPrice (TextView textPrice){

        int calc = (costCPU+costGPU+100+costCooler+(amtRAM*20)+(amtWatts/6)+SSDCost+HDDCost);
        textPrice.setText(calc+"");
    }


}
