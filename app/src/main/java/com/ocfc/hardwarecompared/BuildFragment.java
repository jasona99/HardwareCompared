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


    public BuildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_build, container, false);
        setSpinnerContent(view);
        final SeekBar seekCPU = (SeekBar) view.findViewById(R.id.seekCPU);
        final TextView textCPU = (TextView) view.findViewById(R.id.textCPUPrice);
        final TextView textGPU = (TextView) view.findViewById(R.id.textGPUPrice);
        final TextView textCPUN = (TextView) view.findViewById(R.id.textCPUName);
        final TextView textGPUN = (TextView) view.findViewById(R.id.textGPUName);
        SeekBar seekGPU = (SeekBar) view.findViewById(R.id.seekGPU);
        final ToggleButton toggleCPU = (ToggleButton) view.findViewById(R.id.toggleCPU);
        ToggleButton toggleGPU = (ToggleButton) view.findViewById(R.id.toggleGPU);
        ToggleButton toggleCooler = (ToggleButton) view.findViewById(R.id.toggleCooler);
        final int CPUprice;
        final int GPUprice;
        boolean CPUBrand = false;
        final boolean GPUBrand;
        final int CPUCostSlide;


        // CPU Stuff


        textCPU.setText("0");
        textGPUN.setText("NVIDIA");
        textCPU.setText("0");
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
                CPUCalc(progress, tempB, textCPUN);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textCPU.setText(progress + "");

            }
        });
        int intCPU = Integer.parseInt((String) textCPU.getText());
        CPUCalc(intCPU, CPUBrand, textCPU);

        //GPU Stuff


        textGPU.setText("0");

        seekGPU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                textGPU.setText(progress + "");


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
                    CPUCalc(progress, true, textCPUN);
                } else {
                    progress = seekCPU.getProgress();
                    CPUCalc(progress, false, textCPUN);
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

    private void CPUCalc(int progress, boolean CPUBrand, TextView textCPUN) {
        if (CPUBrand ==true){
            textCPUN.setText("AMD");
        }
        if (CPUBrand ==false){
            textCPUN.setText("Intel");
        }
    }
    private void GPUCalc(int progress, boolean CPUBrand, TextView textGPUN) {
        if (CPUBrand ==true){
            textGPUN.setText("AMD");
        }
        if (CPUBrand ==false){
            textGPUN.setText("NVIDIA");
        }
    }
    private void SSDCalc(int num, int size){

    }
    private void HDDCalc (int num, int size){

    }

}
