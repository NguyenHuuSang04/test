package iuh.fit.entities;

import jakarta.persistence.*;

@Table(name = "busroute")
@Entity
public class Busroute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String start;
    private String end;
    private Integer price;
    private Integer priority;
    @Column(name = "stationNo")
    private Integer stationNo;

    public Busroute() {
    }

    public Busroute(Integer id, String name, String start, String end, Integer price, Integer priority, Integer stationNo) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.price = price;
        this.priority = priority;
        this.stationNo = stationNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStationNo() {
        return stationNo;
    }

    public void setStationNo(Integer stationNo) {
        this.stationNo = stationNo;
    }
}
