package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.nextKey;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;

public class ServiceProviderProfileEditor extends AppCompatActivity{

    //Instance variables
    private EditText companyName;
    private EditText address;
    private EditText phoneNumber;
    private RadioGroup licensedGroup;
    private RadioButton selectedLicensedRadioButton;
    private String licensedAnswer;
    private EditText about;
    private Button saveProfile;
    private TextView errorMessage;
    private Button cancel;

    //Instance boolean variables
    private boolean isCompanyNameFilled = false;
    private boolean isAddressFilled = false;
    private boolean isPhoneNumberFilled = false;
    private boolean isLicensedFilled = false;

    //Field entries to String variables
    private String companyNameContent;
    private String addressContent;
    private String phoneNumberContent;
    private String licensedContent;
    private String aboutContent;


    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void init(){

        //Obtain previous intent
        Intent prevIntent = getIntent();
        final String firstTime = prevIntent.getStringExtra("firstTime");

        /**if statement to check if it is the user's first time registering. This prevents null pointer exceptions.
         if(!firstTime.equals("yes")) {
         //Field entries to String variables
         companyNameContent = prevIntent.getStringExtra("currentCompanyName");
         addressContent = prevIntent.getStringExtra("currentAddress");
         phoneNumberContent = prevIntent.getStringExtra("currentPhoneNumber");
         licensedContent = prevIntent.getStringExtra("currentLicense");
         aboutContent = prevIntent.getStringExtra("currentAbout");
         }
         **/

        //Associate variables to corresponding widgets
        companyName = (EditText)findViewById(R.id.companyNameField);
        address = (EditText)findViewById(R.id.addressField);
        phoneNumber = (EditText)findViewById(R.id.phoneNumberField);
        licensedGroup = (RadioGroup)findViewById(R.id.licensed);
        about = (EditText)findViewById(R.id.aboutField);
        saveProfile = (Button)findViewById(R.id.saveProfileBtn);
        errorMessage = (TextView)findViewById(R.id.errorMessage);
        cancel = (Button)findViewById(R.id.editProfileCancel);

        //Preset fields to current profile field values to edit

        //onClick method for saving profile
        saveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //Set fields to strings
                companyNameContent = companyName.getText().toString();
                addressContent = address.getText().toString();
                phoneNumberContent = phoneNumber.getText().toString();
                aboutContent = about.getText().toString();

                //Turn boolean variables to true if the fields are filled
                if(companyNameContent.length() > 0){
                    isCompanyNameFilled = true;
                }
                if(addressContent.length() > 0){
                    isAddressFilled = true;
                }
                if(phoneNumberContent.length() > 0){
                    isPhoneNumberFilled = true;
                }
                int selectedLicensedOption = licensedGroup.getCheckedRadioButtonId(); //Obtain int id of selection
                if(selectedLicensedOption != -1){
                    isLicensedFilled = true;
                    selectedLicensedRadioButton = (RadioButton)findViewById(selectedLicensedOption);
                    licensedContent = selectedLicensedRadioButton.getText().toString();
                }
                canSaveProfile();
                if(canSaveProfile() == false){
                    errorMessage.setText("Cannot save profile. Please make sure that your required fields are not empty.");
                }
                else{
                    int key = preferences.getInt(loggedInUser, -1);

                    ServiceProvider pro = (ServiceProvider) accountList.get(key);

                    pro.setAddressContent(addressContent);
                    pro.setCompanyNameContent(companyNameContent);
                    pro.setPhoneNumberContent(phoneNumberContent);
                    pro.setLicensedContent(licensedContent);
                    pro.setAboutContent(aboutContent);

                    accountList.put(key, pro);

                    Intent intent = new Intent(ServiceProviderProfileEditor.this, ServiceProviderProfile.class);
                    startActivity(intent);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(ServiceProviderProfileEditor.this,ServiceProviderMenu.class);
                startActivity(cancel);
            }
        });
    }

    //Method to verify that profile can be saved
    public boolean canSaveProfile(){
        return isCompanyNameFilled && isAddressFilled && isPhoneNumberFilled
                && isLicensedFilled;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile_editor);

        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();

        init();
    }

}
