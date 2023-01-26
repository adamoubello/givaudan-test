package com.adamoubello.givaudantest.service;


import com.adamoubello.givaudantest.model.Role;
import com.adamoubello.givaudantest.model.User;
import com.adamoubello.givaudantest.model.UserDto;
import com.adamoubello.givaudantest.repository.RoleDao;
import com.adamoubello.givaudantest.repository.UserDao;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bcryptEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //User user = userDao.findByUsername(username);
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
        // , getAuthority(user));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword()
                , getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority("USER"));
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) { userDao.delete(id); }
    //public void delete(long id) { userDao.deleteById(id); }

    @Override
    public User findByEmail(String email) {
        return  userDao.findByEmail(email);
    }

    @Override
    public User findById(Long id) { return userDao.findOne(id); }
    //public Optional<User> findById(Long id) { return userDao.findById(id); }

    @Override
    public User save(UserDto user) {
        User newUser = new User();

        newUser.setDatenaissance(user.getDatenaissance());
        newUser.setNom(user.getNom());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setPrenom(user.getPrenom());
        newUser.setProfession(user.getProfession());
        //newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());

        /*Role newRoleUser = new Role();
        newRoleUser.setId(5);
        newUser.setUsername(user.getUsername());
        newRoleUser.setDescription("User role");
        newRoleUser.setName("USER");*/

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.findById(1));
        newUser.setRoles(roleSet);

        return userDao.save(newUser);
    }

    public User update (User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.findById(1));
        user.setRoles(roleSet);

        return userDao.save(user);
    }
}
