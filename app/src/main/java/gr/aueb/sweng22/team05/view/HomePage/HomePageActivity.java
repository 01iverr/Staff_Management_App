package gr.aueb.sweng22.team05.view.HomePage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.FirstPageEmployee.FirstPageEmployeeAcivity;
import gr.aueb.sweng22.team05.view.FirstPageManager.FirstPageManagerActivity;
import gr.aueb.sweng22.team05.view.search_worker_by_afm.SearchWorkerByAfmActivity;
import gr.aueb.sweng22.team05.view.search_worker_by_afm.SearchWorkerByAfmPresenter;

public class HomePageActivity extends AppCompatActivity implements HomePageView {
    private EditText username;
    private EditText password;
    private Button loginButton;
    final HomePagePresenter hppresenter = new HomePagePresenter(this, new WorkerDAOMemory());
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        username=findViewById(R.id.editTextTextPersonName);
        password=findViewById(R.id.passinput);
        loginButton=findViewById(R.id.button_sing_in);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workerType = hppresenter.checkingCredentials();
                if (workerType.equals("MANAGER")){
                    Intent intent =new Intent( HomePageActivity.this, FirstPageManagerActivity.class);
                    if (hppresenter.partofHr()){ intent.putExtra("partofHR",true);}
                    else{intent.putExtra("partofHR",false);}
                    intent.putExtra("idOfWorker",  hppresenter.thisworkerhasid());
                    startActivity(intent);
                }
                else if (workerType.equals("EMPLOYEE")){
                    Intent intent =new Intent( HomePageActivity.this, FirstPageEmployeeAcivity.class);
                    if (hppresenter.partofHr()){ intent.putExtra("partofHR",true);}
                    else{intent.putExtra("partofHR",false);}
                    intent.putExtra("idOfWorker",  hppresenter.thisworkerhasid());
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(HomePageActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
    @Override
    public String getthepasswd(){
        return password.getText().toString().trim();
    }
    @Override
    public String gettheinputUsername(){
        return username.getText().toString().trim();
    }
}



