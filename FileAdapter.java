package com.example.sharecode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public FileAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.file_item,parent,false);
        return new FileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewNames.setText(uploadCurrent.getName());
        Picasso.get(/*mContext*/)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNames;
        public ImageView imageView;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNames = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.file_view_upload);
        }
    }
}
