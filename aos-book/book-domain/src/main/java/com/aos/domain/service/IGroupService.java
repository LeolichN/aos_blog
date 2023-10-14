package com.aos.domain.service;

import com.aos.repo.entity.Group;
import com.aos.repo.entity.User;

public interface IGroupService {
    Group getCurrentUserDefaultGroup();

    Group getUserDefaultGroup(User user);
}
