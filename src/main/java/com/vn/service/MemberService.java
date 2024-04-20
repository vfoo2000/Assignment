package com.vn.service;

import com.vn.DAO.MemberDAO;
import com.vn.dto.EditProfileDTO;
import com.vn.dto.RegisterDTO;
import com.vn.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {

    @Autowired
    MemberDAO memberDAO;

    public Member transferFromDTO(RegisterDTO registerDTO) {
        Member member = Member.builder()
                .createdDate(LocalDateTime.now())
                .userName(registerDTO.getUserName())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .build();
        return member;
    }

    public Member updateFromDTO(EditProfileDTO editProfileDTO, Integer idUser) {
        Member member = memberDAO.readById(idUser);

        if (!editProfileDTO.getFirstName().isBlank()) {
            member.setFirstName(editProfileDTO.getFirstName());
        }
        if (!editProfileDTO.getLastName().isBlank()) {
            member.setLastName(editProfileDTO.getLastName());
        }
        if (!editProfileDTO.getPhone().isBlank()) {
            member.setPhone(editProfileDTO.getPhone());
        }
        if (!editProfileDTO.getDescription().isBlank()) {
            member.setDescription(editProfileDTO.getDescription());
        }

        member.setUpdateTime(LocalDateTime.now());

        return member;
    }

    public boolean checkBlankEditProfileDTO(EditProfileDTO editProfileDTO) {
        return editProfileDTO.getFirstName().isBlank()
                && editProfileDTO.getLastName().isBlank()
                && editProfileDTO.getPhone().isBlank()
                && editProfileDTO.getDescription().isBlank();
    }
}
