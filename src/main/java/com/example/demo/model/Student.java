package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @Column(name="roll_no")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID rollNumber;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Min(value = 0, message = "Marks must be at least 0")
    @Max(value = 100, message = "Marks must be at most 100")
    private int marks;
    @NotBlank(message = "Branch cannot be blank")
    @Pattern(regexp = "^(CSE|IT|MECH|EC|CIVIL)$", message = "This branch value is not allowed")
    private String branch;
}
