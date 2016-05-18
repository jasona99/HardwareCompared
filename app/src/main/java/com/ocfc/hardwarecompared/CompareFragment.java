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
    }

}
