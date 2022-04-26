package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this, 1);

        EditText id = this.findViewById(R.id.id);
        EditText pass = this.findViewById(R.id.pass);

        this.findViewById(R.id.login).setOnClickListener(view -> {
            List<Member> list = database.getResult();
            for (Member member : list) {
                String idText = getText(id);
                String passText = getText(pass);
                if (member.getId().equals(idText) && !member.getPassword().equals(passText))
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                if (member.getId().equals(idText) && member.getPassword().equals(passText)) {
                    Toast.makeText(this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Content.class));
                }
            } Toast.makeText(this, "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        });
    }

    private String getText(EditText text) {
        try {
            return text.getText().toString();
        } catch (Exception e) {
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void go(View view) {
        startActivity(new Intent(this, CreateMember.class));
    }
}