package com.bitvilltecnologies.bookword.Authtentications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitvilltecnologies.bookword.Library;
import com.bitvilltecnologies.bookword.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthlistener;
Button loginBtn;
EditText emailText,passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn=(Button)findViewById(R.id.loginbtn);
        emailText=(EditText)findViewById(R.id.emaileditext);
        passwordText=(EditText)findViewById(R.id.passwordeditext);
        mAuth=FirebaseAuth.getInstance();
        final  String TAG ="ViewDatabase";

        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Log.d(TAG,"onAuthStateChanged:signed_in:"+user.getUid());
                    toastmessage("LOADING.....");
                }else {

                }
            }
        };

       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               userLogin();
           }
       });


    }

    private void userLogin(){
        String semail = emailText.getText().toString().trim();
        String spassword = passwordText.getText().toString().trim();

        if(semail.isEmpty() && spassword.isEmpty()){
            emailText.setHint("Email can not be Empty ");
            emailText.requestFocus();
            passwordText.setHint("Password cant be Empty ");
            passwordText.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
            emailText.setHint("Please Type a Valid Email");
            emailText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                   // progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this,Library.class);
                    Toast.makeText(getApplicationContext(),"WELCOME",Toast.LENGTH_LONG).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    startActivity(intent);



                }else {
                   // progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
        if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,Library.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthlistener);
    }

    private void toastmessage(String message){
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
        startActivity(intent);
    }



}
