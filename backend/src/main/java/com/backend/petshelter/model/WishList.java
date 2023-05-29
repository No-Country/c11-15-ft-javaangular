package com.backend.petshelter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "WISHLIST")
public class WishList {
    @Id
    @Column(name = "wishlist_uuid")
    private String uuidWishList;

    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}