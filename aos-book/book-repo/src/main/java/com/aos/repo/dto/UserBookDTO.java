package com.aos.repo.dto;

import com.aos.repo.entity.Group;
import com.aos.repo.entity.UserGroupRelation;
import com.aos.repo.entity.Book;

import java.util.Set;

public class UserBookDTO {

    private String username;

    private String nickName;

    private String password;

    private String telephone;

    private String email;

    private String registerIp;

    private Group defaultGroup; // 用户默认操作的组

    private Book defaultBook;
    
    private Boolean enable;
    
    private Long registerTime; // 注册时间

    private String openId;

    private String unionId;

    private String headimgurl;

    private Set<UserGroupRelation> relations;
}
