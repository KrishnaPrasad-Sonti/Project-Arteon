package com.kp.arteon.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibition", fetch = FetchType.LAZY)
    private List<ArtworkDetails> artworkDetails;

    // Default constructor
    public Exhibition() {}

    // Constructor with fields
    public Exhibition(String name, Date startDate, Date endDate, List<ArtworkDetails> artworkDetails) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artworkDetails = artworkDetails;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ArtworkDetails> getArtworkDetails() {
        return artworkDetails;
    }

    public void setArtworkDetails(List<ArtworkDetails> artworkDetails) {
        this.artworkDetails = artworkDetails;
    }

    @Override
    public String toString() {
        return "Exhibition [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + 
               ", artworkDetails=" + artworkDetails + "]";
    }
}
