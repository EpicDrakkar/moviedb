package com.example.fabian.mm.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fabian.mm.R;
import com.google.android.gms.common.SignInButton;

/**
 * Created by fabian on 7/29/17.
 */

public class LoginActivity extends BaseAuthActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initializeViews();
        initializateGoogleLogIn();
    }

    private void initializeViews() {
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            default:
                break;

        }

    }
}
