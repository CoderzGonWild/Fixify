package com.coderzgonwild.admin.fixify;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceProviderProfileEditorTest {

    ServiceProviderProfileEditor profile = new ServiceProviderProfileEditor();

    @Test
    public void canSaveProfileTestDefault(){
        //Set up variables
        //this should return false for any new object because user hasn't
        //entered any information so none of the conditions are true
        boolean expected = false;
        assertEquals(profile.canSaveProfile(), expected);
    }
    @Test
    public void canSaveProfileTestNotAllTrue(){
        //Set up variables
        //this should return false for  because user hasn't
        //entered all required information so not all of the conditions are true
        profile.SetisLicenseFilled(true);
        profile.SetisAddressFilled(true);
        boolean expected = false;
        assertEquals(profile.canSaveProfile(), expected);
    }
    @Test
    public void canSaveProfileTestAllTrue(){
        //Set up variables
        //this should return true because user has entered all required information
        // so all of the conditions are true
        profile.SetisLicenseFilled(true);
        profile.SetisAddressFilled(true);
        profile.SetisCompanyNameFilled(true);
        profile.SetisPhoneNumberFilled(true);
        boolean expected = true;
        assertEquals(profile.canSaveProfile(), expected);
    }

}
