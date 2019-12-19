package be.thomasmore.ezsports.models;

public class Player {
    private int id;
    private String name;
    private String first_name;
    private String last_name;
    private String hometown;
    private String image_url;
    private String team_name;
    private String team_image_url;

    public Player() {
    }

    public Player(int id, String name, String first_name, String last_name, String hometown, String image_url, String team_name, String team_image_url) {
        this.id = id;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hometown = hometown;
        this.image_url = image_url;
        this.team_name = team_name;
        this.team_image_url = team_image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_image_url() {
        return team_image_url;
    }

    public void setTeam_image_url(String team_image_url) {
        this.team_image_url = team_image_url;
    }
}
