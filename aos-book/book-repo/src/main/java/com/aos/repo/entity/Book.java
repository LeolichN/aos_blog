package com.aos.repo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  @NotNull
  private Group group;

  @Column(nullable = false)
  private Boolean enable = true;

  @Column(length = 64, nullable = false, unique = true)
  private String name;

  @Column(length = 4096)
  private String notes;

  @Column(length = 256, name = "preview_url")
  private String previewUrl;

  @Column(nullable = false)
  private Boolean visible;

  @OneToMany(
      mappedBy = "book",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<Category> categories;

  @OneToMany(
      mappedBy = "book",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<Payee> payees;

  @OneToMany(
      mappedBy = "book",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<Tag> tags;
}
