package ru.mc.off.simple_movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
    }

    class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                int cnt = 0;
                for (String url : urls) {
                    downloadFile(url);
                    publishProgress(++cnt);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Success";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvInfo.setText("Downloaded " + values[0] + " files");
        }

        @Override
        protected void onPostExecute(String result) {
            try  {
                super.onPostExecute(result);
                tvInfo.setText("End, result = " + result);
                String getResult = mt.get();
                Toast.makeText(getApplicationContext(), "get returns " + getResult, Toast.LENGTH_LONG).show();
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            catch (ExecutionException ex)
            {
                ex.printStackTrace();
            }
        }


        private void downloadFile(String url) throws InterruptedException {
            Thread.sleep(2000);
        }
    }
}
