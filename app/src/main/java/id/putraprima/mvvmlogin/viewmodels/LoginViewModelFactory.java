package id.putraprima.mvvmlogin.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.putraprima.mvvmlogin.models.LoginModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LoginModel loginModel;

    public LoginViewModelFactory(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(loginModel);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta LoginViewModel");
    }
}
