package com.devansh.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContactInformation {

    private String email;
    private String mobile;
    private String twitter;
    private String instagram;

}
