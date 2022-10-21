package com.example.feed2022;

class Applications {
    //1# vamos criar as variáveis de instancia que são os atributos que nos interessam para manipular nos objetos do tipo Application
    //campos do XML que jogaremos na nossa listView.
    private String name;
    private  String artist;
    private  String releaseDate;

    //2# gerando getters & setters.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
