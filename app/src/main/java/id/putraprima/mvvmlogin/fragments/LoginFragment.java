package id.putraprima.mvvmlogin.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.putraprima.mvvmlogin.R;
import id.putraprima.mvvmlogin.databinding.FragmentLoginBinding;
import id.putraprima.mvvmlogin.models.LoginModel;
import id.putraprima.mvvmlogin.viewmodels.LoginViewModel;
import id.putraprima.mvvmlogin.viewmodels.LoginViewModelFactory;

public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LoginViewModelFactory loginViewModelFactory = new LoginViewModelFactory(new LoginModel("b@gmail.com", "b12345"));
        LoginViewModel loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false);
        binding.setLogins(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.loggedLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (loginViewModel.loggedLiveData().getValue() == true)
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment,loginViewModel.bundleLiveData().getValue());
            }
        });
        return binding.getRoot();
    }
}