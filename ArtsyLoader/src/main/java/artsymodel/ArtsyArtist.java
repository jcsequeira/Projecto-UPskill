package artsymodel;

import java.time.LocalDate;

public class ArtsyArtist {
    private String name;
    private String biography;
    private LocalDate birthday;
    private LocalDate deathday;
    private String nationality;

    public ArtsyArtist(String name, String biography, LocalDate birthday, LocalDate deathday, String nationality) {
        this.name = name;
        this.biography = biography;
        this.birthday = birthday;
        this.deathday = deathday;
        this.nationality = nationality;
    }

    public ArtsyArtist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDeathday() {
        return deathday;
    }

    public void setDeathday(LocalDate deathday) {
        this.deathday = deathday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "ArtsyArtist{" +
                "name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", birthday='" + birthday + '\'' +
                ", deathday='" + deathday + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
