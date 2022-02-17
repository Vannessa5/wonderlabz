package za.co.wonderbank.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.wonderbank.models.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Page<Role> findAllRole(Pageable pageable);

    List<Role> findAll();

    Role findByName(String name);

    void delete(Long id);

    void delete(Role role);
}
