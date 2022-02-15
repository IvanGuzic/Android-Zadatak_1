package guzic.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {

    private List<Album> albums;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;

    public AdapterListe(Context context) {

        layoutInflater = LayoutInflater.from(context);

    }

    public void setAlbums(List<Album> albums) {

        this.albums = albums;

    }

    @NonNull
    @Override
    public Red onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.red_liste, parent,false);
        return new Red(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Red holder, int position) {

        Album album = albums.get(position);
        holder.strAlbum.setText(album.getStrAlbum());
        holder.strGenre.setText(album.getStrGenre());

    }

    @Override
    public int getItemCount() {
        
/*        if(albums==null) {
            return 0;
        }
        else {
            return albums.size();
        }*/

        return albums == null ? 0 : albums.size();
        
    }

    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView strAlbum;
        private TextView strGenre;

        public Red(@NonNull View itemView) {

            super(itemView);
            strAlbum = itemView.findViewById(R.id.strAlbum);
            strGenre = itemView.findViewById(R.id.strGenre);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(itemClickInterface==null) {
                return;
            }

            itemClickInterface.onItemClick(albums.get(getAdapterPosition()));

    }}

    public interface ItemClickInterface {

        void onItemClick(Album album);

    }

    public void setItemClickInterface(ItemClickInterface itemClickInterface) {

        this.itemClickInterface = itemClickInterface;

}}