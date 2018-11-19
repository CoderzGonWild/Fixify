package com.coderzgonwild.admin.fixify;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

public class ServiceProviderProfileEditorTest {

    ServiceProviderProfileEditor profile = new ServiceProviderProfileEditor();

    private EditText companyName;
    private EditText address;
    private EditText phoneNumber;
    private RadioGroup licensed;
    private String licensedAnswer;
    private RadioButton selectedLicensed;
    private EditText about;
    private Button saveProfile;
    private TextView errorMessage;

    //Instance boolean variables
    private boolean isCompanyNameFilled = false;
    private boolean isAddressFilled = false;
    private boolean isPhoneNumberFilled = false;
    private boolean isLicensedFilled = false;
    private boolean isAboutUnderCharLimit = false;

    //Field entries to String variables
    private String companyNameContent;
    private String addressContent;
    private String phoneNumberContent;
    private String licensedContent;
    private String aboutContent;

    @Test
    public void canSignUp(){
        //Set up variables
        String companyName = "Company";
        String address = "123 Redwood Drive";
        String phoneNumber = "1234567890";
        String licensed = "yes";
        String about = "I am an experienced electrician.";



    }

}
