package com.example.jolebefood.fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jolebefood.Activity.Cart;
import com.example.jolebefood.Activity.ChangePassword;
import com.example.jolebefood.DAO.RegisterDAO.OnGetRegiterListener;
import com.example.jolebefood.DAO.RegisterDAO.Register_DAO;
import com.example.jolebefood.DTO.UserDTO;
import com.example.jolebefood.Activity.Discount;
import com.example.jolebefood.Activity.EditAccount;
import com.example.jolebefood.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    TextView KM,LSMH,GH,TTMH,Setting,CSTK,NameUser;
    ViewPager vpadater;
    UserDTO userDTO;
    public AccFragment(ViewPager vpadater) {
        this.vpadater = vpadater;
    }

    public AccFragment() {
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
        KM = view.findViewById(R.id.UDCT);
        LSMH = view.findViewById(R.id.LSMH);
        GH = view.findViewById(R.id.GH);
        TTMH = view.findViewById(R.id.TTMH);
        Setting = view.findViewById(R.id.Setting);
        CSTK = view.findViewById(R.id.CSTK);
        NameUser = view.findViewById(R.id.NameUser);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userDTO = new UserDTO();
        // Lấy thông tin người dùng
        new Register_DAO().getUserObject(user.getUid(), userDTO, new OnGetRegiterListener() {
            @Override
            public void OnSentGmail() {

            }

            @Override
            public void GetUserSuccess() {
                NameUser.setText(userDTO.getName());
            }
        });
        KM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Discount.class);
                startActivity(intent);
            }
        });
        LSMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpadater.setCurrentItem(2);
            }
        });
        GH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cart.class);
                startActivity(intent);
            }
        });
        TTMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpadater.setCurrentItem(0);
            }
        });
        CSTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditAccount.class);
                startActivity(intent);
            }
        });
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        AdView adView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        return view;
    }

    private void openDiscountFragment() {
        // Tạo FragmentB mới

    }

}