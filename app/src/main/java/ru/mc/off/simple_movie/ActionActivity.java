package ru.mc.off.simple_movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ActionActivity extends AppCompatActivity {
    ArrayList<String> stringArrayList;
    MyTask mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        stringArrayList = getIntent().getStringArrayListExtra("list");

        System.out.println(stringArrayList.get(0));

        mt = new MyTask();

    }

    @Override
    protected void onStart (){
        super.onStart();
        mt.execute(stringArrayList.toArray(new String[stringArrayList.size()]));

    }

    class MyTask extends AsyncTask<String, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... values) {
            try {
                for (String request : values) {
                    String[] args = request.split("\n");
                    System.out.println();
                    pauseImage(Integer.valueOf(args[0]));
                    publishProgress(args[1]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... value) {
            super.onProgressUpdate(value);
            setImage(value[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
        }


        private void pauseImage(int pause) throws InterruptedException {
            Thread.sleep(pause*1000);
        }
    }

    public void setImage(String pictureName){
        ImageView imageView = findViewById(R.id.imageView2);
        switch (pictureName){
            case ("Gentleman"):{
                imageView.setImageResource(R.drawable.gentleman);
            }
            break;
            case ("Businessman"):{
                imageView.setImageResource(R.drawable.businessman);
            }
            break;
            case ("Danger"):{
                imageView.setImageResource(R.drawable.danger);
            }
            break;
            case ("Notebook"):{
                imageView.setImageResource(R.drawable.notebook);
            }
            break;
        }
    }
}
