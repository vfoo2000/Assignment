package com.vn.service;

import com.vn.DAO.ContentDAO;
import com.vn.DAO.MemberDAO;
import com.vn.dto.AddContentDTO;
import com.vn.dto.EditProfileDTO;
import com.vn.dto.RegisterDTO;
import com.vn.entities.Content;
import com.vn.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContentService {

    @Autowired
    ContentDAO contentDAO;

    public Content transferFromDTO(AddContentDTO addContentDTO,Integer UserId) {
        Content content = Content.builder()
                .id(UserId)
                .createDate(LocalDateTime.now())
                .title(addContentDTO.getTitle())
                .brief(addContentDTO.getBrief())
                .content(addContentDTO.getContent())
                .build();
        return content;
    }

    public Content updateFromDTO(AddContentDTO addContentDTO,Integer UserId) {
        Content content = contentDAO.getContentTitle(addContentDTO.getTitle());

        content.setUpdateTime(LocalDateTime.now());
        content.setAuthorId(UserId);

        if (!addContentDTO.getTitle().isBlank()) {
            content.setTitle(addContentDTO.getTitle());
        }
        if (!addContentDTO.getBrief().isBlank()) {
            content.setBrief(addContentDTO.getBrief());
        }
        if (!addContentDTO.getTitle().isBlank()) {
            content.setContent(addContentDTO.getContent());
        }
        return content;
    }

    public boolean checkBlankAddContenteDTO(AddContentDTO addContentDTO) {
        return addContentDTO.getTitle().isBlank()
                && addContentDTO.getBrief().isBlank()
                && addContentDTO.getContent().isBlank();
    }

}
