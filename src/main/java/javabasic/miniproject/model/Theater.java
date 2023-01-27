package javabasic.miniproject.model;

public class Theater {
    private int theaterId;
    private String name;
    private String position;
    private String tel;

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean equals(Object o) {
        if (o instanceof Theater) {
            Theater b = (Theater) o;
            return theaterId == b.theaterId;
        }
        return false;
    }

    public Theater(Theater origin) {
        theaterId = origin.theaterId;
        name = origin.name;
        position = origin.position;
        tel  = origin.tel;
    }

    public Theater() {

    }

    public Theater(int id) {
        this.theaterId = id;
    }
}
