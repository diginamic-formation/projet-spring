package fr.diginamic.dto;

public class SimplePersonGenreDto extends BasicPersonDto {

    private long nbFilms ;
    public SimplePersonGenreDto(Object[] object) {
        super((int) object[0], (String) object[1], (String) object[2]);
        this.nbFilms = (long) object[3];
    }

    public long getNbFilms() {
        return nbFilms;
    }

    public void setNbFilms(long nbFilms) {
        this.nbFilms = nbFilms;
    }
}
