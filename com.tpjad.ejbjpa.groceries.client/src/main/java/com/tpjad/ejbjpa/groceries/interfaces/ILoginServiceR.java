package com.tpjad.ejbjpa.groceries.interfaces;

public interface ILoginServiceR {
    void login(String username, String password);
    boolean isLoggedIn();
    void create(UserDTO userDTO);
    UserDTO findByUsername(String username);
    UserDTO getLoggedUser();
}
