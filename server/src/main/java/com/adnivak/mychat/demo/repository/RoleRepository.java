package com.adnivak.mychat.demo.repository;

import com.adnivak.mychat.demo.model.Role;
import com.adnivak.mychat.demo.model.RoleName;
import com.adnivak.mychat.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
