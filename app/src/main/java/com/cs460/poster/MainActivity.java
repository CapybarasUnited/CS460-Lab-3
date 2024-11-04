package com.cs460.poster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener{

    private Button buttonAddToWatchlist;

    /**
     * On create method used to initialize the app
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);

        List<Poster> posters = new ArrayList<>();

        addPoster(R.drawable.the_matrix, "The 100", "Lana & Lilly Wachowski", "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.", 8.7f, posters);
        addPoster(R.drawable.shawshank_redemption, "The Shawshank Redemption", "Frank Darabont", "A banker convicted of uxoricide forms a friendship over a quarter century with a hardened convict, while maintaining his innocence and trying to remain hopeful through simple compassion.", 9.3f, posters);
        addPoster(R.drawable.the_godfather, "The Godfather", "Francis Ford Coppola", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", 9.2f, posters);
        addPoster(R.drawable.the_dark_knight, "The Dark Knight", "Christopher Nolan", "When a menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman, James Gordon and Harvey Dent must work together to put an end to the madness.", 9.0f, posters);
        addPoster(R.drawable.twelve_angry_men, "12 Angry Men", "Sidney Lumet", "The jury in a New York City murder trial is frustrated by a single member whose skeptical caution forces them to more carefully consider the evidence before jumping to a hasty verdict.", 9.0f, posters);
        addPoster(R.drawable.schindlers_list, "Schindler's List", "Steven Spielberg", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", 9.0f, posters);
        addPoster(R.drawable.pulp_fiction, "Pulp Fiction", "Quentin Tarantino", "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", 8.9f, posters);
        addPoster(R.drawable.forrest_gump, "Forrest Gump", "Robert Zemeckis", "The history of the United States from the 1950s to the '70s unfolds from the perspective of an Alabama man with an IQ of 75, who yearns to be reunited with his childhood sweetheart.", 8.8f, posters);
        addPoster(R.drawable.fight_club, "Fight Club", "    David Fincher", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", 8.8f, posters);
        addPoster(R.drawable.inception, "Inception", "Christopher Nolan", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.", 8.8f, posters);


        final PosterAdapter posterAdapter = new PosterAdapter(posters, this);
        postersRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPoster();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++) {
                    if(i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    }
                    else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }

                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Helper function to make adding new posters easier for me
     * @param image image number
     * @param name name of the movie
     * @param createdBy author of the movie
     * @param story brief story desc
     * @param rating float rating
     * @param posterList poster list to add the poster to
     */
    private void addPoster(int image, String name, String createdBy, String story, float rating, List<Poster> posterList) {
        posterList.add(new Poster(image, name, createdBy, story, rating));
    }

    /**
     * Method to set watchlist visibility when a poster is selected
     * @param isSelected if the poster is selected
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected) {
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        }
        else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}