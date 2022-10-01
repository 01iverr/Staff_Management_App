package gr.aueb.sweng22.team05.view.search_worker_by_afm;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.EditWorker.EditWorkerActivity;
import gr.aueb.sweng22.team05.view.addWorker.AddWorkerActivity;
public class SearchWorkerByAfmActivity extends AppCompatActivity implements SearchWorkerByAfmView {
    private EditText AFM;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_worker_by_afm);
        Button continueButton = findViewById(R.id.swbafmContButton);
        AFM = findViewById(R.id.swbafm_afm);
        final SearchWorkerByAfmPresenter swbafmpresenter = new SearchWorkerByAfmPresenter(this, new WorkerDAOMemory());
        continueButton.setOnClickListener(v -> {
            if (swbafmpresenter.SearchingWorker()) {
                Intent intent = new Intent(SearchWorkerByAfmActivity.this, EditWorkerActivity.class);
                intent.putExtra("afm", gettheafm());
                startActivity(intent);
            } else {
                Intent intent = new Intent(SearchWorkerByAfmActivity.this, AddWorkerActivity.class);
                intent.putExtra("afm", gettheafm());
                startActivity(intent);
            }
        });
        Button backfromswbyafm = findViewById(R.id.buttongobackfromswbafm);
        backfromswbyafm.setOnClickListener(v -> finish());
    }
    @Override
    public String gettheafm() {
        return AFM.getText().toString().trim();
    }
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(SearchWorkerByAfmActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
}