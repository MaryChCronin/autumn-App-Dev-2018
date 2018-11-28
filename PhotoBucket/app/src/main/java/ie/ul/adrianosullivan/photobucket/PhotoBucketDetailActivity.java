package ie.ul.adrianosullivan.photobucket;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.koushikdutta.ion.Ion;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PhotoBucketDetailActivity extends AppCompatActivity {
    private TextView mCaptionTextView;
    //private TextView mURLTextView;
    private ImageView mImageView;

    private DocumentReference mDocRef;
    private DocumentSnapshot mDocSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_photo_bucket_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent receivedIntent = getIntent();
        String docId = receivedIntent.getStringExtra(Constants.EXTRA_DOCUMENT_ID);
        mCaptionTextView = findViewById(R.id.detail_caption);
        //mURLTextView = findViewById(R.id.detail_url);
        mImageView = findViewById(R.id.detail_imageview);

        mDocRef = FirebaseFirestore.getInstance().collection(Constants.COLLECTION_PATH).document(docId);
        mDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed");
                    return;
                }
                if (documentSnapshot.exists()) {
                    mDocSnapshot = documentSnapshot;
                    mCaptionTextView.setText((String) documentSnapshot.get(Constants.KEY_CAPTION));
                   // mURLTextView.setText((String) documentSnapshot.get(Constants.KEY_URL));
                    Ion.with(mImageView).load((String)documentSnapshot.get(Constants.KEY_URL));
                }

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });
    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.photo_bucket_dialog, null, false);
        builder.setView(view);
        builder.setTitle("Edit Photo");
        final TextView captionEditText = view.findViewById(R.id.dialog_caption_edittext);
        final TextView urlEditText = view.findViewById(R.id.dialog_url_edittext);

        captionEditText.setText((String) mDocSnapshot.get(Constants.KEY_CAPTION));
        urlEditText.setText((String) mDocSnapshot.get(Constants.KEY_URL));

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> mq = new HashMap<>();
                mq.put(Constants.KEY_CAPTION, captionEditText.getText().toString());
                mq.put(Constants.KEY_URL, urlEditText.getText().toString());
                mq.put(Constants.KEY_CREATED, new Date());
                mDocRef.update(mq);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_delete:
                // to do delete quote and close activity
                mDocRef.delete();
                finish();
                return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
