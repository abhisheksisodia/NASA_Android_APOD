package com.abhi.nasaapodfetcher.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.nasaapodfetcher.R;
import com.abhi.nasaapodfetcher.models.AstroPicture;
import com.abhi.nasaapodfetcher.rest.ApiClient;
import com.abhi.nasaapodfetcher.rest.ApiInterface;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "bZQuHkfWvBcjP9jVAEUuL4XeplcNASvimy6tytga";
    private AstroPicture astroPic;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.imageTitle)
    TextView titleView;

    @BindView(R.id.fullscreenImageView)
    ImageView fullscreenImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showProgressBar();

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.invalid_api_key_msg), Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AstroPicture> call = apiService.getAPOD(API_KEY);

        call.enqueue(new Callback<AstroPicture>() {
            @Override
            public void onResponse(Call<AstroPicture> call, Response<AstroPicture> response) {
                astroPic = response.body();

                if (astroPic != null && response.isSuccessful()) {
                    displayImageWithTitle();
                } else {
                    displayBadResponseErrorMessage(response);
                }
            }

            @Override
            public void onFailure(Call<AstroPicture> call, Throwable t) {
                displayBadNetworkErrorMessage();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFullscreenImage();
            }
        });
    }

    private void displayImageWithTitle() {
        hideProgressBar();
        Glide.with(this)
                .load(astroPic.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);
        titleView.setText(astroPic.getTitle());
    }

    private void displayFullscreenImage() {
        Glide.with(this)
                .load(astroPic.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(fullscreenImageView);
        fullscreenImageView.setVisibility(View.VISIBLE);
    }

    private void displayBadResponseErrorMessage(Response<AstroPicture> response) {
        hideProgressBar();
        titleView.setText(getResources().getText(R.string.bad_response_error_msg));
        Log.d("Bad Response", response.message());
    }

    private void displayBadNetworkErrorMessage() {
        hideProgressBar();
        titleView.setText(getResources().getText(R.string.bad_network_error_msg));
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (fullscreenImageView.getVisibility() == View.VISIBLE) {
            fullscreenImageView.setImageDrawable(null);
            fullscreenImageView.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
