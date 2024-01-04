package fr.diginamic.entities.java;

public class ActorCoupleWithCommonFilms {

    private int idPerson1;
    private int idPerson2;

    public ActorCoupleWithCommonFilms(Object[] object) {
        this.idPerson1 = (int) object[0];
        this.idPerson2 = (int) object[1];
    }

    public int getIdPerson1() {
        return idPerson1;
    }

    public void setIdPerson1(int idPerson1) {
        this.idPerson1 = idPerson1;
    }

    public int getIdPerson2() {
        return idPerson2;
    }

    public void setIdPerson2(int idPerson2) {
        this.idPerson2 = idPerson2;
    }
}
