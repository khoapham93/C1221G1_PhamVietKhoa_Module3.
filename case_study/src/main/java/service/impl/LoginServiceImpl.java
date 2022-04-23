package service.impl;

import models.Account;
import repository.ILoginRepository;
import repository.impl.LoginRepositoryImpl;
import service.ILoginService;

public class LoginServiceImpl implements ILoginService {
    private ILoginRepository iLoginRepository = new LoginRepositoryImpl();
    @Override
    public Account checkLogin(String username, String password) {
        return iLoginRepository.chekLogin(username,password);
    }
}
