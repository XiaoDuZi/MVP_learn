package com.example.duxinwu.retrofitstudy;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create()).build();
        //新建一个BookApi
        final BookApi bookApi=retrofit.create(BookApi.class);
        mButton= (Button) findViewById(R.id.id_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //这里对应一次网络请求
                        Call<Book> bookCall=bookApi.getBook("json","100");
                        try {
                            Response<Book> book=bookCall.execute();
                            Message message=mHandler.obtainMessage();
                            message.obj=book;
                            mHandler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Book book= (Book) msg.obj;
            Toast.makeText(MainActivity.this,book.getName(),Toast.LENGTH_LONG).show();
        }
    };
}
