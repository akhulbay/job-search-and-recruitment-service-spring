package kz.shyngys.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileCreateEditDto {

    private String aboutUser;
    private String phoneNumber;
    private String languages;
    private String location;
    private String facebookLink;
    private String telegramLink;
    private String linkedinLink;
    private String githubLink;
    private String skills;
    private String degree;
    private String university;
    private String faculty;
    private String major;
    private Integer yearOfAdmission;
    private Integer yearOfGraduation;
    private Double experienceYears;
    private String aboutExperience;
    private Long accountType;
    private Long userId;
}
