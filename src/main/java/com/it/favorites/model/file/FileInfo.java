package com.it.favorites.model.file;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "fav_file")
public class FileInfo {
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotBlank
    private String name;
    private LocalDateTime uploadTime;
    @NotBlank
    private String url;
    private String des;
    private Integer top;
    private String type;
}
