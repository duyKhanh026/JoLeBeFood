package com.example.jolebefood.fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jolebefood.R;
import com.example.jolebefood.SignIn_and_SignUp.Login_Gmail;
import com.example.jolebefood.SignIn_and_SignUp.Login_Phone;
import com.example.jolebefood.SignIn_and_SignUp.MainMenu;
import com.example.jolebefood.SignIn_and_SignUp.Register;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnDN_Phone,btnDN_Email,btnDK;

    public AccFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccFragment newInstance(String param1, String param2) {
        AccFragment fragment = new AccFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_acc, container, false);



        return view;
    }
}