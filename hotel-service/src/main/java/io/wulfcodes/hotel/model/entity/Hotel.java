package io.wulfcodes.hotel.model.entity;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotels")
@Data @NoArgsConstructor @AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "h_id")
    private UUID id;

    @Column(name = "h_name")
    private String name;

    @Column(name = "h_location")
    private String location;

    @Column(name = "h_desc")
    private String description;
}
