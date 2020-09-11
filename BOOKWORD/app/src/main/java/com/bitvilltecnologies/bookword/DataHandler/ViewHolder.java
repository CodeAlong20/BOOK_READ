package com.bitvilltecnologies.bookword.DataHandler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bitvilltecnologies.bookword.R;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
    }



    public void setDetails(String title, String note, String image,String description,String author) {

        TextView mTitle = mView.findViewById(R.id.titlez);
        TextView mDescription = mView.findViewById(R.id.description);
        ImageView mImage = mView.findViewById(R.id.rimageview);
        TextView mAuthor = mView.findViewById(R.id.author);


        mTitle.setText(title);
        mAuthor.setText(author);
        Picasso.get().load(image).into(mImage);
        mDescription.setText(description);
    }

    public void setSearch(String title, String description, String image,String author,String note) {

        TextView mTitle = mView.findViewById(R.id.titlez2);
        TextView mDescription = mView.findViewById(R.id.description2);
        ImageView mImage = mView.findViewById(R.id.rimageview2);

        mTitle.setText(title);
        Picasso.get().load(image).into(mImage);
        mDescription.setText(description);
    }
}
