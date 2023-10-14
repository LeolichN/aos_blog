package com.aos.domain.service.impl;

import com.aos.core.utils.JWTUtils;
import com.aos.domain.service.IGroupService;
import com.aos.domain.service.UserService;
import com.aos.repo.entity.Group;
import com.aos.repo.entity.User;
import com.aos.repo.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupService implements IGroupService {
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private UserService userService;

    @Override
    public Group getCurrentUserDefaultGroup() {
        Integer userId = JWTUtils.getBizId();
        User user = userService.getUser(userId);
        return groupRepo.findOneByCreatorAndIsDefault(user, true);
    }

    @Override
    public Group getUserDefaultGroup(User user) {
        return groupRepo.findOneByCreatorAndIsDefault(user, true);
    }
}
