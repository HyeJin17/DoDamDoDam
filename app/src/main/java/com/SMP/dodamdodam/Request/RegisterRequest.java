package com.SMP.dodamdodam.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest  {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://ec2-52-79-44-86.ap-northeast-2.compute.amazonaws.com/Register.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RegisterRequest(String UserEmail, String UserPwd,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("UserEmail", UserEmail);
        map.put("UserPwd", UserPwd);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}