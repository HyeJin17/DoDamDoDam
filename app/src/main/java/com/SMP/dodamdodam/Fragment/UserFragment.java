package com.SMP.dodamdodam.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SMP.dodamdodam.Activity.AchieveActivity;
import com.SMP.dodamdodam.Activity.findParkActivity;
import com.SMP.dodamdodam.Activity.loginActivity;
import com.SMP.dodamdodam.R;
import com.SMP.dodamdodam.SharedPreferenceBean;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class UserFragment extends Fragment {
    TextView getid;
    ImageButton btn_map;
    ImageButton btn_logout;
    private View view;
    ImageButton btn_ach;

    @Override
    public void onStart() {
        super.onStart();
        getid = getView().findViewById(R.id.getid);
        btn_logout = getView().findViewById(R.id.btn_logout);

        String UserEmail = SharedPreferenceBean.getAttribute(getActivity().getApplication(),"UserEmail");
        String UserPlatform = SharedPreferenceBean.getAttribute(getActivity().getApplication(),"UserPlatform");
        String UserName = SharedPreferenceBean.getAttribute(getActivity().getApplication(),"UserName");
        getid.setText(UserName);
        btn_map = getView().findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), findParkActivity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserPlatform.equals("kakao")) {
                    UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onCompleteLogout() {
                            SharedPreferenceBean.removeAllAttribute(getActivity().getApplicationContext());
                            Intent intent = new Intent(getActivity(), loginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }

                    });
                } else if (UserPlatform.equals("register")) {
                    SharedPreferenceBean.removeAllAttribute(getActivity().getApplicationContext());
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
        btn_ach = view.findViewById(R.id.btn_ach);
        btn_ach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AchieveActivity.class);
                startActivity(intent);
            }
        });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        return view;


    }
}
