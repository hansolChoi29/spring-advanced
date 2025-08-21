package org.example.expert.domain.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @DeleteMapping("/admin/comments/{commentId}")
    public void deleteComment(@PathVariable long commentId,
                              HttpServletRequest request
    ) {
        Object roleAttr = request.getAttribute("userRole");
        String role = roleAttr != null ? roleAttr.toString() : null;
        if(!"ADMIN".equals(role)){
            throw   new RuntimeException("권한 없음");
        }

        System.out.println("[로그]"+ LocalDateTime.now()+"commentId: "+commentId);

        commentAdminService.deleteComment(commentId);
    }
}
