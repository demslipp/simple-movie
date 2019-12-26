package ru.mc.off.simple_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapterList;

    EditText timerText;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setTitle(R.string.app_name);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                //String item = (String)parent.getItemAtPosition(position);
                System.out.println(position);
                setImage(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        timerText = findViewById(R.id.timerText);


        spinner.setOnItemSelectedListener(itemSelectedListener);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        ListView listView = findViewById(R.id.list);

        adapterList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);

        listView.setAdapter(adapterList);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.button): {
            Toast.makeText(getApplicationContext(), timerText.getText() + " " + spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            addItem();
            }
            break;
        }
        switch (view.getId()){
            case (R.id.button2): {
                Intent intent = new Intent(this, ActionActivity.class);
                intent.putExtra("list", listItems);
                //System.out.println("Pederau mdataBases.get(position)" + mdataBases.get(position));
                startActivity(intent);
            }
            break;
        }
    }

    public void setImage(int position){
        ImageView imageView = findViewById(R.id.imageView);
        switch (position){
            case (0):{
                imageView.setImageResource(R.drawable.gentleman);
            }
            break;
            case (1):{
                imageView.setImageResource(R.drawable.businessman);
            }
            break;
            case (2):{
                imageView.setImageResource(R.drawable.notebook);
            }
            break;
            default:{
                imageView.setImageResource(R.drawable.danger);
            }
            break;
        }
    }

    public void addItem() {
        listItems.add(timerText.getText() + "\n" + spinner.getSelectedItem().toString());
        adapterList.notifyDataSetChanged();
    }

    public void deleteLast() {
        listItems.remove(listItems.size()-1);
        adapterList.notifyDataSetChanged();
    }

    public void clearAll() {
        listItems.clear();
        adapterList.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem  item) {
        switch (item.getItemId()) {
            case R.id.delete_last:
                deleteLast();
                return true;
            case R.id.clear:
                clearAll();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
