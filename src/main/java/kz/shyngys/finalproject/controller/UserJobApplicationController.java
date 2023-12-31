package kz.shyngys.finalproject.controller;

import kz.shyngys.finalproject.dto.*;
import kz.shyngys.finalproject.dto.records.UserFilter;
import kz.shyngys.finalproject.dto.records.UserJobApplicationFilter;
import kz.shyngys.finalproject.dto.records.UserProfileFilter;
import kz.shyngys.finalproject.service.UserJobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-job-applications")
@RequiredArgsConstructor
public class UserJobApplicationController {

    private final UserJobApplicationService userJobAppService;

    @GetMapping
    public PageResponse<UserJobApplicationReadDto> findAll(UserJobApplicationFilter userJobAppFilter,
                                                           UserFilter userFilter,
                                                           UserProfileFilter userProfileFilter,
                                                           Pageable pageable) {
        Page<UserJobApplicationReadDto> userJobApps = userJobAppService.findAll(userJobAppFilter,
                userFilter, userProfileFilter, pageable);
        return PageResponse.of(userJobApps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserJobApplicationReadDto> findById(@PathVariable("id") Long id) {
        UserJobApplicationReadDto userJobApp = userJobAppService.findById(id);
        return ResponseEntity.ok(userJobApp);
    }

    @GetMapping("/count/{id}")
    public Integer countByJobId(@PathVariable("id") Long id) {
        return userJobAppService.countByJobId(id);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Integer> findStatus(@PathVariable("id") Long id) {
        Integer status = userJobAppService.findStatus(id);
        return ResponseEntity.ok(status);
    }

    @PostMapping
    public ResponseEntity<UserJobApplicationReadDto> create(@RequestBody UserJobApplicationCreateEditDto userJobApp) {
        UserJobApplicationReadDto newUserJobApp = userJobAppService.create(userJobApp);
        return new ResponseEntity<>(newUserJobApp, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserJobApplicationReadDto> update(@PathVariable("id") Long id,
                                                            @RequestBody UserJobApplicationCreateEditDto userJobApp) {
        UserJobApplicationReadDto newUserJobApp = userJobAppService.update(id, userJobApp);
        return ResponseEntity.ok(newUserJobApp);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UserJobApplicationReadDto> updateStatus(@PathVariable("id") Long id,
                                                                  @RequestBody UserJobApplicationEditStatusDto
                                                                          userJobAppStatus) {
        UserJobApplicationReadDto newUserJobApp = userJobAppService.updateStatus(id, userJobAppStatus);
        return ResponseEntity.ok(newUserJobApp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userJobAppService.delete(id);
    }
}
