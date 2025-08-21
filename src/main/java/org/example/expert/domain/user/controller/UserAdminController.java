package org.example.expert.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.service.UserAdminService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService userAdminService;

    @PatchMapping("/admin/users/{userId}")
    public void changeUserRole(@PathVariable long userId,
                               @RequestBody UserRoleChangeRequest userRoleChangeRequest,
                               HttpServletRequest request
    ) {
        Object roleAttr = request.getAttribute("userRole");
        String role = roleAttr != null ? roleAttr.toString() : null;
        if(!"ADMIN".equals(role)) {
            throw new RuntimeException("권한 없음");
        }
        System.out.println("[로그]"+ LocalDateTime.now()+"userId: "+userId);
        userAdminService.changeUserRole(userId, userRoleChangeRequest);
    }
}
