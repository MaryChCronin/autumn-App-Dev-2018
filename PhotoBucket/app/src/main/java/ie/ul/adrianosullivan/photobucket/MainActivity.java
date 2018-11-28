package ie.ul.adrianosullivan.photobucket;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // FirebaseFirestore db = FirebaseFirestore.getInstance();

        PhotoBucketAdaptor photoBucketAdaptor = new PhotoBucketAdaptor();
        recyclerView.setAdapter(photoBucketAdaptor);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added new quote to firestore", Snackbar.LENGTH_LONG)
                        .show();

                showAddDialog();
            }
        });
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.photo_bucket_dialog, null, false);
        builder.setView(view);
        builder.setTitle("Add photo");
        final TextView captionEditText = view.findViewById(R.id.dialog_caption_edittext);
        final TextView URLEditText = view.findViewById(R.id.dialog_url_edittext);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Map<String, Object> mq = new HashMap<>();

                mq.put(Constants.KEY_CAPTION, captionEditText.getText().toString());
                if (URLEditText.getText().toString().length() == 0) {
                    RandomImage randomImage = new RandomImage();
                    mq.put(Constants.KEY_URL, randomImage.randomImageUrl());
                } else {
                    mq.put(Constants.KEY_URL, URLEditText.getText().toString());
                }
                mq.put(Constants.KEY_CREATED, new Date());

                FirebaseFirestore.getInstance().collection(Constants.COLLECTION_PATH).add(mq);

            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
    }

}
