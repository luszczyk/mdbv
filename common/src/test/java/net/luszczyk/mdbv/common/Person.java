package net.luszczyk.mdbv.common;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 06.02.13
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String avatar;
    private String best_movie;
    private String anthem;
    private String place_birth;

    public Person(String firstName, String lastName, String avatar, String best_movie, String anthem, String place_birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.best_movie = best_movie;
        this.anthem = anthem;
        this.place_birth = place_birth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBest_movie() {
        return best_movie;
    }

    public void setBest_movie(String best_movie) {
        this.best_movie = best_movie;
    }

    public String getAnthem() {
        return anthem;
    }

    public void setAnthem(String anthem) {
        this.anthem = anthem;
    }

    public String getPlace_birth() {
        return place_birth;
    }

    public void setPlace_birth(String place_birth) {
        this.place_birth = place_birth;
    }
}
