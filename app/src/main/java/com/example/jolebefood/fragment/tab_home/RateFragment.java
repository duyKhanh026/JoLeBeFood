package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jolebefood.MainActivity;
import com.example.jolebefood.R;
import com.example.jolebefood.Users;
import com.example.jolebefood.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RateFragment extends Fragment {
    String firstName, lastName, age,userName;
    FirebaseDatabase db;
    DatabaseReference reference;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RateFragment newInstance(String param1, String param2) {
        RateFragment fragment = new RateFragment();
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
        view = inflater.inflate(R.layout.fragment_rate, container, false);

        EditText firstName_text = view.findViewById(R.id.firstName_text);
        EditText lastName_text = view.findViewById(R.id.lastName_text);
        EditText age_text = view.findViewById(R.id.age_text);
        EditText userName_text = view.findViewById(R.id.userName_text);
        Button nhapBtn = view.findViewById(R.id.nhapBtn);

        nhapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = firstName_text.getText().toString();
                lastName = lastName_text.getText().toString();
                age = age_text.getText().toString();
                userName = userName_text.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){
                    Users users = new Users(firstName, lastName, age, userName);

                    db = FirebaseDatabase.getInstance();

                    reference = db.getReference("User");
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firstName_text.setText("");
                            lastName_text.setText("");
                            age_text.setText("");
                            userName_text.setText("");
                            Toast.makeText(getContext(),"Successfuly updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        return view;
    }
}