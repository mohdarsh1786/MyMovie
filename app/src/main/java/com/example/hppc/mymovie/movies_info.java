package com.example.hppc.mymovie;



public class movies_info {
    private String name;
    private String Actress;
    private String MovieType;
    private String ActorName;
private  String city;
    //constructer


    public movies_info(String name,String Actress, String MovieType, String ActorName, String city) {
        this.name = name;
        this.Actress = Actress;
        this.MovieType = MovieType;
        this.ActorName = ActorName;
        this.city=city;
    }
    //setter getter


    public String getActorName() {
        return ActorName;
    }

    public void setActorName(String actorName) {
        ActorName = actorName;
    }

    public String getActress() {
        return Actress;
    }

    public void setActress(String actress) {
        Actress = actress;
    }

    public String getMovieType() {
        return MovieType;
    }

    public void setMovieType(String movieType) {
        MovieType = movieType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }*/

}
