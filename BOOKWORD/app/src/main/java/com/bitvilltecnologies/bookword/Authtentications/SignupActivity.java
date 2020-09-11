package com.bitvilltecnologies.bookword.Authtentications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthException;

public class SignupActivity extends AppCompatActivity {
    EditText signupemail, signuppassword, signupcpassword;
    Button signupbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupemail = (EditText) findViewById(R.id.signupemaileditext);
        signupcpassword = (EditText) findViewById(R.id.confirmpasswordeditext);
        signuppassword = (EditText) findViewById(R.id.signupeditextpassword);
        signupbtn = (Button) findViewById(R.id.signupAbtn);
        mAuth = FirebaseAuth.getInstance();
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = signupemail.getText().toString().trim();
        String password = signuppassword.getText().toString().trim();
        String password1 = signupcpassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupemail.setHint("Please Type in a Valid Email");
            signupemail.requestFocus();
            return;
        }

        if (password.isEmpty() || password1.isEmpty()){
            signuppassword.setHint("Password cant be empty ");
            signuppassword.requestFocus();
            signupcpassword.setHint("Password cant be empty ");
            signupcpassword.requestFocus();
        }

            if (password.matches(password1)) {
mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()) {

           // Toast.makeText(SignupActivity.this,"WELCOME PROGRAMMER ",Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(SignupActivity.this, Library.class);
                startActivity(intent);


                Toast.makeText(getApplicationContext(),"Welcome Scribe",Toast.LENGTH_SHORT).show();

            finish();

            }else {

                if (task.getException()instanceof FirebaseAuthException){

                    Toast.makeText(getApplicationContext(),"You have an account Already",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
    }
});

            } else {

                Toast.makeText(getApplicationContext(), "PASSWORD DON'T MATCH", Toast.LENGTH_LONG).show();
            }
        }
    }


