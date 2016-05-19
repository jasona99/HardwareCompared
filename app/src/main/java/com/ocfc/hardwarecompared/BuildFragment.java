package com.ocfc.hardwarecompared;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


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
        return view;
    }
    private void setSpinnerContent( View view )
    {
        Spinner spinnerRAM = (Spinner) view.findViewById( R.id.spinnerRAM );
        Spinner spinnerHDD = (Spinner) view.findViewById( R.id.spinnerHDD );
        Spinner spinnerSSD = (Spinner) view.findViewById( R.id.spinnerSSD );
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

}
