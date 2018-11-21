package com.example.maryc.moviequotes;

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


public class MovieQuoteAdaptor extends RecyclerView.Adapter<MovieQuoteAdaptor.MovieQuoteViewHolder> {
    private List<DocumentSnapshot> mMovieQuotesSnapshots = new ArrayList<>();

    public MovieQuoteAdaptor() {
        CollectionReference movieQuotesCollectionRef = FirebaseFirestore.getInstance().collection(constants.collection);
        movieQuotesCollectionRef
                .orderBy(constants.created,Query.Direction.DESCENDING)
                .limit(50)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(constants.TAG, "Listening Failed");
                            return;
                        }
                        mMovieQuotesSnapshots = queryDocumentSnapshots.getDocuments();
                        notifyDataSetChanged();
                    }
                });
    }

    @NonNull
    @Override
    public MovieQuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_quote_itemview, parent, false);
        return new MovieQuoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieQuoteViewHolder movieQuoteViewHolder, int i) {
        DocumentSnapshot ds = mMovieQuotesSnapshots.get(i);
        String movie = (String) ds.get(constants.movie);
        String quote = (String) ds.get(constants.quote);
        movieQuoteViewHolder.mMovieTextView.setText(movie);
        movieQuoteViewHolder.mQuoteTextView.setText(quote);
    }

    @Override
    public int getItemCount() {
        return mMovieQuotesSnapshots.size();
    }

    class MovieQuoteViewHolder extends RecyclerView.ViewHolder {
        private TextView mQuoteTextView;
        private TextView mMovieTextView;

        public MovieQuoteViewHolder(final View itemView) {
            super(itemView);
            mQuoteTextView = itemView.findViewById(R.id.itemview_quote);
            mMovieTextView = itemView.findViewById(R.id.itemview_movie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocumentSnapshot ds = mMovieQuotesSnapshots.get(getAdapterPosition());
                    Context c = itemView.getContext();
                    Intent intent = new Intent(c,MovieQuoteDetailActivity.class);
                    intent.putExtra(constants.docID, ds.getId());
                    c.startActivity(intent);
                }
            });

        }

    }
}