package kz.shyngys.finalproject.controller;

import kz.shyngys.finalproject.dto.UserProfileCreateEditDto;
import kz.shyngys.finalproject.dto.UserProfileReadDto;
import kz.shyngys.finalproject.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public List<UserProfileReadDto> findAll() {
        return userProfileService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileReadDto> findById(@PathVariable("id") Long id) {
        UserProfileReadDto user = userProfileService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileReadDto> findByUserId(@PathVariable("id") Long userId) {
        UserProfileReadDto user = userProfileService.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserProfileReadDto> create(@RequestBody UserProfileCreateEditDto user) {
        UserProfileReadDto newUser = userProfileService.save(user);
        if (newUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileReadDto> update(@PathVariable("id") Long id,
                                                     @RequestBody UserProfileCreateEditDto user) {
        UserProfileReadDto newUser = userProfileService.update(id, user);
        if (newUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newUser);
    }
}
