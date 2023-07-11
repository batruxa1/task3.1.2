package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UsersRepository;

import java.util.List;

@Service
public class  UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void add(User user) {
        usersRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public void updateUser(User updateUser) {
        usersRepository.save(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String name) {
        return usersRepository.findByUserName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }
}