package adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.utsmobileprograming1_adzibilalmh_22552011164.NewsActivity;
import com.example.utsmobileprograming1_adzibilalmh_22552011164.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import model.Berita;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
    private List<Berita> beritaList;
    private Context context;

    public BeritaAdapter(Context context, List<Berita> beritaList) {
        this.context = context;
        this.beritaList = beritaList;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    private String formatPubDate(String pubDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Set time zone ke UTC

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault());
        outputFormat.setTimeZone(TimeZone.getDefault()); // Set time zone ke time zone perangkat

        try {
            Date date = inputFormat.parse(pubDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return pubDate; // Jika gagal memformat, kembalikan tanggal asli
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        Berita berita = beritaList.get(position);
        holder.textViewTitle.setText(berita.getTitle());
        holder.textViewPubDate.setText(formatPubDate(berita.getPubDate()));
        holder.textViewDescription.setText(berita.getDescription());

        // Load image using Glide with error handling
        Glide.with(context)
                .load(berita.getThumbnail())
                .placeholder(R.mipmap.ic_launcher_utb)
                .error(R.mipmap.ic_launcher_utb) // Placeholder for error
                .into(holder.imageViewThumbnail);

        holder.textViewViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("url", berita.getLink());
                context.startActivity(intent);
                Log.d("View More Clicked", berita.getLink());
            }
        });
    }


    @Override
    public int getItemCount() {
        return beritaList.size();
    }

    public static class BeritaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView imageViewThumbnail;
        TextView textViewPubDate;
        TextView textViewDescription;
        TextView textViewViewMore;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            textViewPubDate = itemView.findViewById(R.id.textViewPubDate);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewViewMore = itemView.findViewById(R.id.textViewViewMore);
        }
    }

}
