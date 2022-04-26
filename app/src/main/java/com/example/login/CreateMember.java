package com.example.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CreateMember extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_member);

        database = new Database(this, 1);
        EditText id = this.findViewById(R.id.id);
        EditText pass = this.findViewById(R.id.pass);

        this.findViewById(R.id.back).setOnClickListener(view -> finish());
        this.findViewById(R.id.save).setOnClickListener(view -> {
            String idText = getText(id);
            String passText = getText(pass);

            if (idText != null && passText != null) {
                List<Member> list = database.getResult();
                if (list.size()!=0) {
                    for (Member member : list) {
                        if (member.getId().equals(idText)) Toast.makeText(this, "아이디가 이미 존재합니다.", Toast.LENGTH_SHORT).show();
                        else {
                            database.Insert(idText, passText);
                            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                } else {
                    database.Insert(idText, passText);
                    Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
        });
    }

    private String getText(EditText text) {
        try {
            return text.getText().toString();
        } catch (Exception e) {
            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}