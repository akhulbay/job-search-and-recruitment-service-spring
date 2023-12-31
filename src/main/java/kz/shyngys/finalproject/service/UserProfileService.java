package kz.shyngys.finalproject.service;

import kz.shyngys.finalproject.dto.UserProfileCreateEditAvatarDto;
import kz.shyngys.finalproject.dto.UserProfileCreateEditDto;
import kz.shyngys.finalproject.dto.UserProfileReadDto;

import java.util.List;

public interface UserProfileService {

    List<UserProfileReadDto> findAll();

    UserProfileReadDto findById(Long id);

    UserProfileReadDto findByUserId(Long userId);

    byte[] findAvatar(Long id);

    UserProfileReadDto save(UserProfileCreateEditDto user);

    UserProfileReadDto update(Long id, UserProfileCreateEditDto user);

    byte[] updateAvatar(Long id, UserProfileCreateEditAvatarDto userProfileAvatar);

    void delete(Long id);
}
