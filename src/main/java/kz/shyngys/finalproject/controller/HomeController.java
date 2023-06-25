package kz.shyngys.finalproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/employer-profile")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYER')")
    public String employerProfilePage() {
        return "employer-profile";
    }

    @GetMapping("/company-profile")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYER')")
    public String companyProfilePage() {
        return "company-profile";
    }

    @GetMapping("/post-job")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYER')")
    public String postJobPage() {
        return "post-job";
    }
}
