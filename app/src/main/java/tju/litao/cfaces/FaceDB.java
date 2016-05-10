package tju.litao.cfaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by litao on 2016/5/9.
 */

public class FaceDB{
    private static final String TAG= "FaceDB";

    public  static  final String DATABASE_NAME = "cfaces.db";
    public  static final int DATABASE_VERSION = 2;
    public  static final String FACES_TABLE_NAME = "faces";

    public static final String KEY_ID= "_id";
    public static final String KEY_NAME= "name";
    public static final String KEY_TRAINED = "trained";
    public static final String KEY_LASTUPDATE = "lastupdate";

    private Context context;
    private FacesDBHelper DBHelper;
    private SQLiteDatabase db;

    public FaceDB(Context ctx){
        this.context = ctx;
        DBHelper = new FacesDBHelper(ctx);
    }

    class FacesDBHelper extends SQLiteOpenHelper {

        private static final String FACES_TABLE_CREATE =
                "CREATE TABLE " + FACES_TABLE_NAME + " (" +
                        KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                        KEY_NAME +  "  TEXT    NOT NULL," +
                        KEY_LASTUPDATE+ " LONG NOT NULL, " +
                        KEY_TRAINED + " BOOLEAN NOT NULL" +
                        ");";

        public FacesDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.d(TAG, "FacesDBHelper excuted");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(FACES_TABLE_CREATE);
            }catch (SQLException e){
                e.printStackTrace();
            }
            Log.d(TAG, "db created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + FACES_TABLE_NAME);
            onCreate(db);
        }
    }

    public FaceDB open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        Log.d(TAG, "db opened");
        return this;
    }

    public void close(){
        Log.d(TAG, "db closed");
        DBHelper.close();
    }

    public long insertFace(Face face){
        long id = db.insert(FACES_TABLE_NAME, null, getContentValue(face));
        face.setFaceID(id);
        return id;
    }

    public boolean deleteFace( long id){
        return db.delete(FACES_TABLE_NAME, KEY_ID +"=" +id, null) > 0;
    }

    public Cursor getAllFaces(){
        return db.query(FACES_TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_TRAINED, KEY_LASTUPDATE
                }, null, null, null, null, null);
    }

    public Cursor getFace(long id) throws  SQLException{
        Cursor cursor = db.query(FACES_TABLE_NAME, new String[]{KEY_NAME, KEY_TRAINED, KEY_LASTUPDATE},
                KEY_ID +"="+ id, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updateFace(Face face){
        return db.update(FACES_TABLE_NAME, getContentValue(face), KEY_ID +"="+face.getFaceID(), null) > 0;
    }

    private ContentValues getContentValue(Face face){
        ContentValues content = new ContentValues();
        content.put(KEY_NAME,face.getName());
        content.put(KEY_TRAINED, face.hasBeenTrained());
        content.put(KEY_LASTUPDATE, face.getLastUpdated());
        return content;
    }
}