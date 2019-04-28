package com.s.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private EditText txtfname,txtlname,txtusername,txtpassword,txtrepassword;
    private Button btnlogin,btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtfname=findViewById(R.id.txtfname);
        txtlname=findViewById(R.id.txtlname);
        txtusername=findViewById(R.id.txtusername);
        txtpassword=findViewById(R.id.txtpassword);
        txtrepassword=findViewById(R.id.txtrepassword);

        btnlogin=findViewById(R.id.btnlogin);
        btnregister=findViewById(R.id.btnregister);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if(validate()){
                 signup();
             }
            }
        });
    }

    private void signup(){
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("firstname",txtfname.getText().toString());
        editor.putString("lastname",txtlname.getText().toString());
        editor.putString("username",txtusername.getText().toString());
        editor.putString("password",txtpassword.getText().toString());
        editor.commit();

        Toast.makeText(this,"Successfully Registered",Toast.LENGTH_LONG).show();
    }
    private boolean validate(){
        boolean checkvalidate=true;
        if(TextUtils.isEmpty(txtfname.getText().toString())){
            txtfname.setError("First Name is required");
            txtfname.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtlname.getText().toString())){
            txtlname.setError("Last Name is required");
            txtlname.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtusername.getText().toString())){
            txtusername.setError("Username is required");
            txtusername.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtpassword.getText().toString())){
            txtpassword.setError("Password is required");
            txtpassword.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtrepassword.getText().toString())){
            txtrepassword.setError("Password is required");
            txtrepassword.requestFocus();
            checkvalidate=false;
        }
        String pwd=txtpassword.getText().toString();
        String repwd=txtrepassword.getText().toString();
        if(pwd.equals(repwd)){
            checkvalidate=true;
        }else{
            txtpassword.setError("Invalid Match");
            txtrepassword.setError("Invalid Match");
            txtpassword.requestFocus();
            checkvalidate=false;
        }
        return  checkvalidate;

    }
}
