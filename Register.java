package com.example.sharecode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    //problem connecting to firebase, check later, https://www.youtube.com/watch?v=kMEkP6f9_kE
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText userName = findViewById(R.id.userName);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText checkPassword = findViewById(R.id.checkPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginBtn = findViewById(R.id.loginBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get data from EditText into string variables
                final String userNameTxt = userName.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String checkPasswordTxt = checkPassword.getText().toString();

                //check if user fill all the field
                if(userNameTxt.isEmpty() ||emailTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else if (!passwordTxt.equals(checkPasswordTxt))
                    Toast.makeText(Register.this,"Passwords aren't matching",Toast.LENGTH_SHORT).show();
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(emailTxt)){
                                Toast.makeText(Register.this,"Email is already registered",Toast.LENGTH_SHORT).show();}
                            else {
                                databaseReference.child("users").child(emailTxt).child("fullName").setValue(userNameTxt);
                                databaseReference.child("users").child(emailTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register.this,"User registered successfully!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}