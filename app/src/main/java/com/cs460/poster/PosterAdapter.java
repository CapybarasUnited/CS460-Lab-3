package com.cs460.poster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private List<Poster> posterList;
    private PostersListener postersListener;

    public List<Poster> getSelectedPoster() {
        List<Poster> selectedPosters = new ArrayList<>();
        for(Poster poster : this.posterList) {
            if(poster.isSelected) {
                selectedPosters.add(poster);
            }
        }

        return selectedPosters;
    }


    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreatedBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        void bindPoster(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            if(poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }

            else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = false;
                        if(getSelectedPoster().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    }

                    else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.GONE);
                        //TODO
                    }
                }
            });

        }
    }
}
