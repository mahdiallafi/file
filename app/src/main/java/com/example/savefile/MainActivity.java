package com.example.savefile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ///create file
    private static final String File_Name="example.txt";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    editText=findViewById(R.id.editTextTextPersonName);

    }
    public void save(View v){
        String text=editText.getText().toString();
        FileOutputStream fileOutputStream=null;
       try {
           fileOutputStream=openFileOutput(File_Name,MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            editText.getText().clear();
           Toast.makeText(this, "saved to"+getFilesDir()+"/"+File_Name, Toast.LENGTH_SHORT).show();
       }catch (FileNotFoundException e){
           e.printStackTrace();
       }catch (IOException e){
           e.printStackTrace();
       }finally {
           if(fileOutputStream != null){
               try{
                   fileOutputStream.close();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
       }

    }
    public  void load(View v){
        FileInputStream fileInputStream=null;
        try{
            fileInputStream=openFileInput(File_Name);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();
            String text;
            while ((text=bufferedReader.readLine())!=null){
                stringBuilder.append(text).append("\n");
            }
            editText.setText(stringBuilder.toString());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            {
                if(fileInputStream != null){
                    try {
                        fileInputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}