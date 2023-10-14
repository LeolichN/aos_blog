package com.aos.repo.repository;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.Group;
import com.aos.repo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends BaseRepository<Group> {

    int countByCreator(User creator);

    Group findOneByCreatorAndIsDefault(User creator,Boolean isDefault);
}
