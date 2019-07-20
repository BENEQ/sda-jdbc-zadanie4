public class Ksiazka {

    private Integer id;
    private String tytul;

    public Ksiazka() {
    }

    public Ksiazka(Integer id, String tytul) {
        this.id = id;
        this.tytul = tytul;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                '}';
    }
}
