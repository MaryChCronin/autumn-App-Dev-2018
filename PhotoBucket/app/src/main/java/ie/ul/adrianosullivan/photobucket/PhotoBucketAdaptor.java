package ie.ul.adrianosullivan.photobucket;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PhotoBucketAdaptor extends RecyclerView.Adapter<PhotoBucketAdaptor.PhotoBucketViewHolder> {
    private List<DocumentSnapshot> mPhotoBucketSnapshots = new ArrayList<>();


    public PhotoBucketAdaptor() {
        CollectionReference photoBucketCollectionRef = FirebaseFirestore.getInstance().collection(Constants.COLLECTION_PATH);
        photoBucketCollectionRef
                .orderBy(Constants.KEY_CREATED, Query.Direction.DESCENDING)
                .limit(50)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(Constants.TAG, "Listening Failed");
                            return;
                        }
                        mPhotoBucketSnapshots = queryDocumentSnapshots.getDocuments();
                        notifyDataSetChanged();
                    }
                });
    }

    @NonNull
    @Override
    public PhotoBucketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_bucket_itemview, parent, false);
        return new PhotoBucketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoBucketViewHolder photoBucketViewHolder, int i) {
        DocumentSnapshot ds = mPhotoBucketSnapshots.get(i);
        String caption = (String) ds.get(Constants.KEY_CAPTION);
        String url = (String) ds.get(Constants.KEY_URL);
        photoBucketViewHolder.mCaptionTextView.setText(caption);
       // photoBucketViewHolder.mURLTextView.setText(url);
    }

    @Override
    public int getItemCount() {
        return mPhotoBucketSnapshots.size();
    }
    class PhotoBucketViewHolder extends RecyclerView.ViewHolder {
        private TextView mCaptionTextView;
       // private TextView mURLTextView;

        public PhotoBucketViewHolder(final View itemView) {
            super(itemView);
            mCaptionTextView = itemView.findViewById(R.id.itemview_caption);
           // mURLTextView = itemView.findViewById(R.id.itemview_url);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocumentSnapshot ds = mPhotoBucketSnapshots.get(getAdapterPosition());
                    Context c = itemView.getContext();
                    Intent intent = new Intent(c,PhotoBucketDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_DOCUMENT_ID, ds.getId());
                    c.startActivity(intent);
                }
            });

        }

    }
}
