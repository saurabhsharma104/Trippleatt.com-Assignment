package com.example.trippleattassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view) {
        editText = findViewById(R.id.editText);
        String n = editText.getText().toString();
        editText = findViewById(R.id.editText2);
        String f = editText.getText().toString();
        editText = findViewById(R.id.editText4);
        String m = editText.getText().toString();
        editText = findViewById(R.id.editText5);
        String e = editText.getText().toString();
        editText = findViewById(R.id.editText3);
        String p = editText.getText().toString();

        if(n.isEmpty()||f.isEmpty()||m.isEmpty()||e.isEmpty()||p.isEmpty())
        {
            Toast.makeText(this, "Please Enter All Data Fields....", Toast.LENGTH_SHORT).show();
            return;
        }

        DataStore dataStore = new DataStore(this);

        boolean bo;
        try {
            SQLiteDatabase database = dataStore.getWritableDatabase();
            bo= dataStore.addDataInTable(n, f, m, e, p, database);
            database.close();

        }catch (Exception exc)
        {
            Toast.makeText(this, "Some technical issue...."+exc, Toast.LENGTH_SHORT).show();
            return;
        }
        if(bo) {
            Toast.makeText(this, "Add Data Successfully...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,HomePage.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Enter Unique Email Id or Password....", Toast.LENGTH_SHORT).show();

    }

    public void loginPage(View view) {
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }
}
