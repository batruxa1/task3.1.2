package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UsersRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class  UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, RoleService roleService) {
        this.usersRepository = usersRepository;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    @Override
    public void add(User user) {
        usersRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return usersRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void updateUser(User updateUser) {
        usersRepository.save(updateUser);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getSetOfRoles(List<String> roleId) {
        roleId.forEach(System.out::println);
        Set<Role> roleSet = new HashSet<>();
        for (String id: roleId) {
            roleSet.add(roleService.getRoleById(Long.parseLong(id)));
        }
        return roleSet;
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String name) {
        return usersRepository.findByUserName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }
}