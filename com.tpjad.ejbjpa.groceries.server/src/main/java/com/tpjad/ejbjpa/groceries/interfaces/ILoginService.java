package com.tpjad.ejbjpa.groceries.interfaces;

public interface ILoginService {
    void login(String username, String password);
    boolean isLoggedIn();
    void create(UserDTO userDTO);
    UserDTO findByUsername(String username);
    UserDTO getLoggedUser();
}
