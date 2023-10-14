package com.aos.domain.service.impl;

import com.aos.domain.entity.GroupInitialState;
import com.aos.domain.service.UserService;
import com.aos.infrustructure.shared.InitialStateService;
import com.aos.repo.entity.Group;
import com.aos.repo.entity.InitStateDTO;
import com.aos.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookInitialStateService implements InitialStateService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public InitStateDTO handleInitState() {
        InitStateDTO initStateDTO = new InitStateDTO();
        User user = userService.getCurrentUser();
        initStateDTO.setUser(user.getId(),user.getUsername());
        Group group = groupService.getUserDefaultGroup(user);
        GroupInitialState groupInitialState = new GroupInitialState();
        groupInitialState.setId(group.getId());
        groupInitialState.setDefaultCurrencyCode(group.getDefaultCurrencyCode());
        groupInitialState.setName(group.getName());
        initStateDTO.putOtherStates("group", groupInitialState);
        return initStateDTO;
    }
}
