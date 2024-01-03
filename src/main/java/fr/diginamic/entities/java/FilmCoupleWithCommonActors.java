package fr.diginamic.entities.java;

public class FilmCoupleWithCommonActors {

    private int idFilm1;
    private int idFilm2;

    public FilmCoupleWithCommonActors(Object[] object) {
        this.idFilm1 = (int) object[0];
        this.idFilm2 = (int) object[1];
    }

    public int getIdFilm1() {
        return idFilm1;
    }

    public void setIdFilm1(int idFilm1) {
        this.idFilm1 = idFilm1;
    }

    public int getIdFilm2() {
        return idFilm2;
    }

    public void setIdFilm2(int idFilm2) {
        this.idFilm2 = idFilm2;
    }
}
