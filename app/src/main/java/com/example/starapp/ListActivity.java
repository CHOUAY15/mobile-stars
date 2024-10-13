package com.example.starapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.starapp.adapter.PlayerAdapter;
import com.example.starapp.beans.Player;
import com.example.starapp.service.PlayerService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final int REQUEST_PLAYER_DETAIL = 1;
    private static final String TAG = "ListActivity";
    private List<Player> players;
    private RecyclerView recyclerView;
    private PlayerAdapter playerAdapter;
    private PlayerService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        service = PlayerService.getInstance();
        players = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_view);

        if (service.findAll().isEmpty()) {
            init();
        }

        playerAdapter = new PlayerAdapter(this, service.findAll());
        playerAdapter.setOnItemClickListener(player -> {
            Intent intent = new Intent(ListActivity.this, PlayerDetailActivity.class);
            intent.putExtra("PLAYER_ID", player.getId());
            startActivityForResult(intent, REQUEST_PLAYER_DETAIL);
        });
        recyclerView.setAdapter(playerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init() {

        service.create(new Player(
                "Federico Valverde",
                new Date(1998 - 1900, 6, 22), // Birthdate: July 22, 1998
                "Uruguay",
                8, // Jersey number
                "Midfielder",
                1.82, // Height in meters
                78.0, // Weight in kg
                "Right",
                5, // Years in contract
                100_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250101284.png", // Replace with actual image URL
                4.7f // Star rating
        ));
        service.create(new Player(
                "Kylian Mbappé",
                new Date(1998 - 1900, 11, 20), // Birthdate: December 20, 1998
                "France",
                9, // Jersey number
                "Forward",
                1.78, // Height in meters
                73.0, // Weight in kg
                "Right",
                6, // Years in contract
                180_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250076574.png", // Replace with actual image URL
                5.0f // Star rating
        ));
        service.create(new Player(
                "Brahim Díaz",
                new Date(1999 - 1900, 7, 3), // Birthdate: August 3, 1999
                "Morocco",
                21, // Jersey number
                "Midfielder",
                1.71, // Height in meters
                68.0, // Weight in kg
                "Right",
                3, // Years in contract
                25_000_000, // Market value in euros
                "https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--8dbbf1e1-261d-43ef-84a7-5ad167d18a27/POSE__15.app.webp?preferwebp=true&width=420", // Replace with actual image URL
                4.3f // Star rating
        ));
        service.create(new Player(
                "Aurélien Tchouaméni",
                new Date(2000 - 1900, 0, 27), // Birthdate: January 27, 2000
                "France",
                18, // Jersey number
                "Midfielder",
                1.87, // Height in meters
                81.0, // Weight in kg
                "Right",
                5, // Years in contract
                90_000_000, // Market value in euros
                "https://assets.laliga.com/squad/2024/t186/p219292/2048x2048/p219292_t186_2024_1_002_000.jpg", // Replace with actual image URL
                4.9f // Star rating
        ));
        service.create(new Player(
                "Eduardo Camavinga",
                new Date(2002 - 1900, 10, 10), // Birthdate: November 10, 2002
                "France",
                12, // Jersey number
                "Midfielder",
                1.82, // Height in meters
                70.0, // Weight in kg
                "Left",
                5, // Years in contract
                85_000_000, // Market value in euros
                "https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--8bc99c66-d69a-4a2d-8d5e-148dd3d8e9ea/POSE__13.app.webp?preferwebp=true&width=420", // Replace with actual image URL
                4.8f // Star rating
        ));
        // Service to manage Player creation
        service.create(new Player(
                "Luka Modrić",
                new Date(1985 - 1900, 8, 9), // Birthdate: September 9, 1985
                "Croatia",
                10, // Jersey number
                "Midfielder",
                1.72, // Height in meters
                66.0, // Weight in kg
                "Right",
                2, // Years in contract
                10_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/74699.png", // Replace with actual image URL
                5.0f // Star rating
        ));

        service.create(new Player(
                "Vinícius Júnior",
                new Date(2000 - 1900, 6, 12), // Birthdate: July 12, 2000
                "Brazil",
                7, // Jersey number
                "Forward",
                1.76, // Height in meters
                73.0, // Weight in kg
                "Right",
                4, // Years in contract
                120_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250121533.png", // Replace with actual image URL
                4.8f // Star rating
        ));

        service.create(new Player(
                "Thibaut Courtois",
                new Date(1992 - 1900, 4, 11), // Birthdate: May 11, 1992
                "Belgium",
                1, // Jersey number
                "Goalkeeper",
                2.00, // Height in meters
                96.0, // Weight in kg
                "Right",
                5, // Years in contract
                60_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250011668.png", // Replace with actual image URL
                5.0f // Star rating
        ));



        service.create(new Player(
                "Éder Militão",
                new Date(1998 - 1900, 0, 18), // Birthdate: January 18, 1998
                "Brazil",
                3, // Jersey number
                "Defender",
                1.86, // Height in meters
                79.0, // Weight in kg
                "Right",
                4, // Years in contract
                70_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250121965.png", // Replace with actual image URL
                1.6f // Star rating
        ));

        service.create(new Player(
                "Jude Bellingham",
                new Date(2003 - 1900, 5, 29), // Birthdate: June 29, 2003
                "England",
                5, // Jersey number
                "Midfielder",
                1.86, // Height in meters
                75.0, // Weight in kg
                "Right",
                6, // Years in contract
                150_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250128377.png", // Replace with actual image URL
                5.0f // Star rating
        ));
        service.create(new Player(
                "David Alaba",
                new Date(1992 - 1900, 5, 24), // Birthdate: June 24, 1992
                "Austria",
                4, // Jersey number
                "Defender",
                1.80, // Height in meters
                78.0, // Weight in kg
                "Left",
                3, // Years in contract
                50_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/1906540.png", // Replace with actual image URL
                4.8f // Star rating
        ));

        service.create(new Player(
                "Rodrygo Goes",
                new Date(2001 - 1900, 0, 9), // Birthdate: January 9, 2001
                "Brazil",
                11, // Jersey number
                "Forward",
                1.74, // Height in meters
                64.0, // Weight in kg
                "Right",
                5, // Years in contract
                100_000_000, // Market value in euros
                "https://assets.laliga.com/squad/2024/t186/p440077/2048x2048/p440077_t186_2024_1_002_000.jpg", // Replace with actual image URL
                4.6f // Star rating
        ));

        service.create(new Player(
                "Ferland Mendy",
                new Date(1995 - 1900, 5, 8), // Birthdate: June 8, 1995
                "France",
                23, // Jersey number
                "Defender",
                1.78, // Height in meters
                73.0, // Weight in kg
                "Left",
                2, // Years in contract
                30_000_000, // Market value in euros
                "https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250112803.png", // Replace with actual image URL
                4.4f // Star rating
        ));



        service.create(new Player(
                "Arda Güler",
                new Date(2005 - 1900, 1, 25), // Birthdate: February 25, 2005
                "Turkey",
                24, // Jersey number
                "Midfielder",
                1.76, // Height in meters
                69.0, // Weight in kg
                "Left",
                6, // Years in contract
                30_000_000, // Market value in euros
                "https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--5b90804b-67b7-4038-a7df-a5345af3f1e9/POSE__17.app.webp?preferwebp=true&width=420", // Replace with actual image URL
                4.6f // Star rating
        ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (playerAdapter != null) {
                    playerAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setChooserTitle("Stars")
                    .setText("Stars")
                    .startChooser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PLAYER_DETAIL && resultCode == RESULT_OK) {
            int updatedPlayerId = data.getIntExtra("UPDATED_PLAYER_ID", -1);
            if (updatedPlayerId != -1) {
                playerAdapter.notifyDataSetChanged();
            }
        }
    }
}