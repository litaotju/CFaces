package tju.litao.cfaces;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class FacesListActivity extends Activity {
    private static final String TAG = "FacesListActivity";
    private FaceDB db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faces_list);
        Log.e(TAG, "begin");
        Toast.makeText(this, "begin", Toast.LENGTH_LONG).show();
        db = new FaceDB(this);
        db.open();
        db.close();
        Log.e(TAG, "finished");
        Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show();
    }

}
