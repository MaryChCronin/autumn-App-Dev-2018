package com.example.maryc.nameadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder>{
    private List<String> mNames = new ArrayList<>();
    private Random mRandom = new Random();
    private RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    public void addNames(){
        mNames.add(0, getRandomName());
        //notifyDataSetChanged();
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mNames.size());
        mRecyclerView.scrollToPosition(0);

    }
    private String getRandomName() {
        String[] names = new String[] {
                "David Beck", "David Berry", "Ian Berry", "Niall Broderick", "Conor Clancy", "Mary Cronin",
                "Matthew Daniels", "Paul Delaney", "Debra Donovan", "Mark Egan", "Anna Hudakova",
                "Brian Hyland", "Emer Kennedy Ozdemir", "Senan O'Callaghan", "Thomas O'Connor",
                "Cahir O'Leary", "Deirdre O'Loughlin", "Adrian O'Sullivan", "Istvan Orosz", "Mark Quigley",
                "Deirdre Shanahan", "Kevin St John", "Sergejs Sushinskis"
        };
        return names[mRandom.nextInt(names.length)];
    }


    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.name_view, parent,false);
        return new NameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        holder.mNameTextView.setText(mNames.get(position));
        holder.mDescriptionTextView.setText("I'm #" +(position +1));

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    class NameViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mDescriptionTextView;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.name);
            mDescriptionTextView = itemView.findViewById(R.id.description);

        }
    }
}
