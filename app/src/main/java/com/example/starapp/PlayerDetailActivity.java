package com.example.starapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.starapp.beans.Player;
import com.example.starapp.service.PlayerService;

import java.util.Calendar;
import java.util.Date;

public class PlayerDetailActivity extends AppCompatActivity {
    private static final int RESULT_PLAYER_UPDATED = 1;

    private PlayerService service;
    private Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        service = PlayerService.getInstance();

        int playerId = getIntent().getIntExtra("PLAYER_ID", -1);
        if (playerId != -1) {
            Player player = service.findById(playerId);
            if (player != null) {
                currentPlayer = player;
                displayPlayerInfo(player);
            }
        }
        setResult(RESULT_CANCELED);
    }

    private void displayPlayerInfo(Player player) {
        ImageView playerImageView = findViewById(R.id.player_image);
        TextView playerNumberView = findViewById(R.id.player_number);
        TextView nameTextView = findViewById(R.id.player_name);
        TextView positionTextView = findViewById(R.id.player_position);

        TextView nationalityLabelView = findViewById(R.id.nationality_info).findViewById(R.id.info_label);
        TextView nationalityValueView = findViewById(R.id.nationality_info).findViewById(R.id.info_value);
        TextView ageLabelView = findViewById(R.id.age_info).findViewById(R.id.info_label);
        TextView ageValueView = findViewById(R.id.age_info).findViewById(R.id.info_value);
        TextView heightLabelView = findViewById(R.id.height_info).findViewById(R.id.info_label);
        TextView heightValueView = findViewById(R.id.height_info).findViewById(R.id.info_value);
        TextView weightLabelView = findViewById(R.id.weight_info).findViewById(R.id.info_label);
        TextView weightValueView = findViewById(R.id.weight_info).findViewById(R.id.info_value);
        TextView footLabelView = findViewById(R.id.foot_info).findViewById(R.id.info_label);
        TextView footValueView = findViewById(R.id.foot_info).findViewById(R.id.info_value);

        TextView valueTextView = findViewById(R.id.player_value);
        RatingBar ratingBar = findViewById(R.id.player_rating);

        // Set player number
        playerNumberView.setText(String.valueOf(player.getNumero()));

        nameTextView.setText(player.getNom() != null ? player.getNom() : "Unknown");
        positionTextView.setText(player.getPoste() != null ? player.getPoste() : "Unknown");

        // Set labels
        nationalityLabelView.setText("Nationality");
        ageLabelView.setText("Age");
        heightLabelView.setText("Height");
        weightLabelView.setText("Weight");
        footLabelView.setText("Preferred Foot");

        // Set values
        nationalityValueView.setText(player.getNationalite() != null ? player.getNationalite() : "Unknown");

        if (player.getDateNaissance() != null) {
            int age = calculateAge(player.getDateNaissance());
            ageValueView.setText(String.format("%d years", age));
        } else {
            ageValueView.setText("Unknown");
        }

        heightValueView.setText(String.format("%.2f m", player.getTaille()));
        weightValueView.setText(String.format("%.1f kg", player.getPoids()));
        footValueView.setText(player.getPiedFort() != null ? player.getPiedFort() : "Unknown");

        valueTextView.setText(String.format("€%,.0fM", player.getValeurMarchande() / 1_000_000));
        ratingBar.setRating(player.getStar());

        Glide.with(this)
                .load(player.getImg())
                .into(playerImageView);

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if (fromUser) {
                showRatingConfirmationDialog(rating);
            }
        });
    }

    private void showRatingConfirmationDialog(float newRating) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Rating Change")
                .setMessage("Are you sure you want to change the player's rating to " + newRating + "?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    updatePlayerRating(newRating);
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Reset the rating bar to the original value
                    RatingBar ratingBar = findViewById(R.id.player_rating);
                    ratingBar.setRating(currentPlayer.getStar());
                })
                .show();
    }

    private void updatePlayerRating(float newRating) {
        currentPlayer.setStar(newRating);
        service.update(currentPlayer);

        // Set result to indicate player was updated
        Intent resultIntent = new Intent();
        resultIntent.putExtra("UPDATED_PLAYER_ID", currentPlayer.getId());
        setResult(RESULT_OK, resultIntent);
    }

    private int calculateAge(Date birthDate) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }
}