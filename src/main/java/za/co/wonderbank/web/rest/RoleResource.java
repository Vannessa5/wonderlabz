package za.co.wonderbank.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wonderbank.models.Role;
import za.co.wonderbank.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // cross origin will only allow for specific entry
public class RoleResource {

    private final static Logger log = LoggerFactory.getLogger(RoleResource.class);

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Role> save(@RequestBody Role role) throws Exception {
        log.info("Request To save role", role);
        if (role.getId() != null) {
            throw new Exception("Can't create new role with id: " + role.getId());
        }
        Role result = roleService.save(role);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/role")
    public ResponseEntity<Role> update(@RequestBody Role role) throws Exception {
        log.info("Request To save role", role);
        if (role.getId() == null) {
            throw new Exception("Can't update role with id.null");
        }
        Role result = roleService.save(role);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> allRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }
}
