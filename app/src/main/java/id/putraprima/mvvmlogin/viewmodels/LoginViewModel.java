package id.putraprima.mvvmlogin.viewmodels;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import id.putraprima.mvvmlogin.R;
import id.putraprima.mvvmlogin.fragments.HomeFragment;
import id.putraprima.mvvmlogin.models.LoginModel;

public class LoginViewModel extends ViewModel {
    private String email = "b@gmail.com";
    private String password = "b12345";

    private MutableLiveData<LoginModel> loginModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loggedMutableLive = new MutableLiveData<>();
    private MutableLiveData<String> errorEmail = new MutableLiveData<>();
    private MutableLiveData<String> errorPassword = new MutableLiveData<>();
    private MutableLiveData<Bundle> bundleMutableLiveData = new MutableLiveData<>();
    public LoginModel loginModel;

    public LoginViewModel(LoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginModelMutableLiveData.setValue(this.loginModel);
    }

    public LiveData<LoginModel> getLogin(){
        return this.loginModelMutableLiveData;
    }

    public LiveData<Boolean> loggedLiveData() {
        return this.loggedMutableLive;
    }

    public LiveData<String> getErrorUsername(){
        return this.errorEmail;
    }

    public LiveData<String> getErrorPass(){
        return this.errorPassword;
    }

    public LiveData<Bundle> bundleLiveData(){
        return this.bundleMutableLiveData;
    }

    public void doLogin(){
        Log.d("Email",loginModel.email.toString());
        Log.d("Pass",loginModel.password.toString());
        loggedMutableLive.setValue(false);

        if (loginModel.email.equals(email) && loginModel.password.equals(password)){
            loginModelMutableLiveData.setValue(loginModel);
            loggedMutableLive.setValue(true);
            Bundle b = new Bundle();
            b.putString("email", loginModel.email);
            bundleMutableLiveData.setValue(b);
            return;
        } else if(loginModel.isEmailValid()){
            errorEmail.setValue("Masukkan alamat email dengan benar"); // set pesan error
            return;
        } else if (loginModel.email.isEmpty() && loginModel.password.isEmpty()){
            errorEmail.setValue("Masukkan alamat email Anda"); // set pesan
            errorPassword.setValue("Masukkan password Anda"); // set pesan
            return;
        } else if (loginModel.email.equals(email) && loginModel.password.isEmpty() || loginModel.password == null){
            errorPassword.setValue("Masukkan password Anda");
            return;
        } else if (loginModel.email.isEmpty() || loginModel.email == null && loginModel.password.equals(password)){
            errorEmail.setValue("Masukkan alamat email Anda");
            return;
        } else if (!loginModel.email.equals(email) && !loginModel.password.equals(password)){
            errorEmail.setValue("Masukkan alamat email Anda dengan benar");
            errorPassword.setValue("Masukkan password Anda dengan benar");
            return;
        }

    }
}
