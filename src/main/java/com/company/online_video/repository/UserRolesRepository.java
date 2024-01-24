package com.company.online_video.repository;

import com.company.online_video.entity.UserRoles;
import com.company.online_video.utils.CURKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, CURKey> {

}
