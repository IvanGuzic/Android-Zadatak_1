package guzic.recycleview;

import java.io.Serializable;

// POJO
public class Album implements Serializable {

    private String idLabel;  //ovo se vidi u detaljima
    private String strAlbum; //ovo neka se vidi na listi
    private String strGenre; //ovo neka se vidi na listi
    private String strAlbumThumb;

    public Album(String idLabel, String strAlbum, String strGenre) {

        this.idLabel = idLabel;
        this.strAlbum = strAlbum;
        this.strGenre = strGenre;

    }

    // getters-i i setters-i

    public String getIdLabel() {

        return idLabel;

    }

    public void setIdLabel(String idLabel) {

        this.idLabel = idLabel;

    }

    public String getStrAlbum() {

        return strAlbum;

    }

    public void setStrAlbum(String strAlbum) {

        this.strAlbum = strAlbum;

    }

    public String getStrGenre() {

        return strGenre;

    }

    public void setStrGenre(String strGenre) {

        this.strGenre = strGenre;

    }

    public String getStrAlbumThumb() {

        return strAlbumThumb;

    }

    public void setStrAlbumThumb(String strAlbumThumb) {

        this.strAlbumThumb = strAlbumThumb;

}}
