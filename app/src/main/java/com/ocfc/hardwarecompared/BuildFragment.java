package com.ocfc.hardwarecompared;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuildFragment extends Fragment {


    private int costGPU;
    private int wattsGPU;
    private int wattsCPU;
    private int costCPU;

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
        ToggleButton toggleCooler = (ToggleButton) view.findViewById(R.id.toggleCooler);
        boolean CPUBrand = false;
        boolean GPUBrand = false;
        final TextView totalWattage = (TextView) view.findViewById(R.id.textWattage);
        wattsGPU = 0;
        costGPU = 0;
        costCPU = 0;
        wattsCPU = 0;
        totalWattage.setText((wattsGPU)+"");
        final TextView chipset = (TextView) view.findViewById(R.id.textMBChipset);

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
    }
    private void GPUCalc(int progress, boolean GPUBrand, TextView textGPUN, View view) {
        final TextView totalWattage = (TextView) view.findViewById(R.id.textWattage);
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
        totalWattage.setText((wattsGPU)+"");
    }
    private void SSDCalc(int num, int size){

    }
    private void HDDCalc (int num, int size){

    }


}
