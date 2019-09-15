package com.alltrustnetworks.smartcheck;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.alltrustnetworks.smartcheck.exceptions.SmartCheckApiException;
import com.alltrustnetworks.smartcheck.models.Address;
import com.alltrustnetworks.smartcheck.models.Check;
import com.alltrustnetworks.smartcheck.models.CheckOcrRequest;
import com.alltrustnetworks.smartcheck.models.CheckOcrResponse;
import com.alltrustnetworks.smartcheck.models.Customer;
import com.alltrustnetworks.smartcheck.models.CustomerIdentification;
import com.alltrustnetworks.smartcheck.models.CustomerIdentificationOCR;
import com.alltrustnetworks.smartcheck.models.CustomerRequest;
import com.alltrustnetworks.smartcheck.models.DecisioningRequest;
import com.alltrustnetworks.smartcheck.models.DecisioningResult;
import com.alltrustnetworks.smartcheck.models.DocId;
import com.alltrustnetworks.smartcheck.models.Fee;
import com.alltrustnetworks.smartcheck.models.FrontBackImages;
import com.alltrustnetworks.smartcheck.models.IDOcrResult;
import com.alltrustnetworks.smartcheck.models.IdVerificationResponse;
import com.alltrustnetworks.smartcheck.models.Image;
import com.alltrustnetworks.smartcheck.models.InfoBusiness;
import com.alltrustnetworks.smartcheck.models.LocationsBusiness;
import com.alltrustnetworks.smartcheck.models.LocationsStore;
import com.alltrustnetworks.smartcheck.models.LoyaltyNumber;
import com.alltrustnetworks.smartcheck.models.Maker;
import com.alltrustnetworks.smartcheck.models.Micr;
import com.alltrustnetworks.smartcheck.models.ReviewStatus;
import com.alltrustnetworks.smartcheck.models.Transaction;
import com.alltrustnetworks.smartcheck.models.UpdateEmailProfileRequest;
import com.alltrustnetworks.smartcheck.models.VerifyPhoneResponse;
import com.alltrustnetworks.smartcheck.models.consumer.CreateProfileRequest;
import com.alltrustnetworks.smartcheck.models.consumer.EmailRequest;
import com.alltrustnetworks.smartcheck.models.consumer.EnrollmentResponse;
import com.alltrustnetworks.smartcheck.models.consumer.FormConsumer;
import com.alltrustnetworks.smartcheck.models.consumer.PhoneConfirmResponse;
import com.alltrustnetworks.smartcheck.models.consumer.ProfileResponse;
import com.alltrustnetworks.smartcheck.models.consumer.SignInRequest;
import com.alltrustnetworks.smartcheck.models.consumer.StatusResponse;
import com.alltrustnetworks.smartcheck.models.fees.Fees;
import com.alltrustnetworks.smartcheck.models.misnap.TransactionData;
import com.alltrustnetworks.smartcheck.util.Util;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private String locationId = "location_2dfefccb-f64d-463d-9ce0-410923888b21";
    private String businessId = "business_be8f003a-1072-4ea2-aee7-c1e0f70c8749";
    private String apiToken = "at_key-2df20a76-11e2-4924-9565-2c74372f4e91";
    private String url = "https://atws-stage.herokuapp.com";
    private String urlConsumer = "https://consumer-service-stage.herokuapp.com";
    private String misnapUrl = "https://alltrustuat.rdcselect.com/externalservices";
    private String misnapUsername = "checkuser";
    private String misnapPassword = "P@ych3ck20!8";

    private Address address = new Address("24 State Street", "", "NY", "NY", "11111");
    private LoyaltyNumber loyaltyNumber = new LoyaltyNumber("");
    private CustomerIdentification customerIdent = new CustomerIdentification("", "", "Driver's License", "312241127", "", "2022-01-01");

    private SmartCheckApi api = new SmartCheckApi(url, apiToken, businessId, locationId, urlConsumer);
    private MisnapApi misnapApi = new MisnapApi(misnapUrl, misnapUsername, misnapPassword);

    @Test
    public void testGetCustomerByIdNumber() throws Exception {
        Customer customer = api.getCustomerByIdNumber("N1111", "AL");
        assertNotNull(customer);
    }

    @Test
    public void testSearchCustomerByNumber() throws Exception {
        List<CustomerIdentification> identifications = api.searchCustomerByPhone("+17035551212");
        assertNotNull(identifications);
    }

    @Test
    public void testCheckOcr() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Check check = new Check();
        Check.frontOriginalImage = getImage(context, R.raw.front);
        Check.rearOriginalImage = getImage(context, R.raw.rear);

        com.alltrustnetworks.smartcheck.models.misnap.Transaction transaction = misnapApi.depositCheck(check);

        TransactionData transData = transaction.getTransactionData();

        int transactionId = transData.getTransactionId();
        File frontTiff = misnapApi.getCheckImage(context, transactionId, "front");
        File backTiff = misnapApi.getCheckImage(context, transactionId, "rear");

        int amount = transData.getAmount();
        check.setAmount(amount);
        Micr micr = api.parseMirc(transData.getMicr());
        micr.setAmount(amount + "");
        check.setMicr(micr);
        check.setRawMicr(micr.getRawMicr());
        check.setCheckDate("2018-02-02");
        check.setPayeeName("Ron John");
        check.setPayerName("John Brown");
        check.setPayerAddress("700 Warrent Rd, New York");
        check.setStatus("success");

        CheckOcrRequest checkOcrRequest = new CheckOcrRequest();
        checkOcrRequest.setFrontImage(Util.convertTiffToBase64String(frontTiff));
        checkOcrRequest.setBackImage(Util.convertTiffToBase64String(backTiff));
        if (check.getMicr() != null) {
            checkOcrRequest.setAbaNumber(check.getMicr().getTransitNumber() != null ? check.getMicr().getTransitNumber() : "");
            checkOcrRequest.setAccountNumber(check.getMicr().getAccountNumber() != null ? check.getMicr().getAccountNumber() : "");
            checkOcrRequest.setAmount(check.getMicr().getAmount() != null ? Integer.parseInt(check.getMicr().getAmount()) : 0);
            checkOcrRequest.setCheckNumber(check.getMicr().getCheckNumber() != null ? check.getMicr().getCheckNumber() : "");
        }

        CheckOcrResponse response = api.ocrCheck(checkOcrRequest);
        assertNotNull(response);
    }

    @Test
    public void testScanCheck() throws Exception {
        Check check = scanCheck();
        assertNotNull(check.getMicr().getAccountNumber());

        assertEquals("758661326", check.getMicr().getAccountNumber());
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DocId.frontProcessedImage = getImage(context, R.raw.dl);
        Customer customer = this.createCustomer();
        assertNotNull(customer);
    }

    @Test
    public void testGetDecisioningResultDateExceptions() throws Exception, SmartCheckApiException {
        Check check = scanCheck();
        Customer customer = createCustomer();
        check.setCheckDate("");

        DecisioningRequest decisioningRequest = api.buildDecisioningRequest(customer, check);

        try {
            DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);
        } catch (SmartCheckApiException ex) {
            assertEquals("Check date is missing", ex.getMessage());
        }

        check.setCheckDate("2015-02-02");
        decisioningRequest = api.buildDecisioningRequest(customer, check);

        try {
            DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);
        } catch (SmartCheckApiException ex) {
            assertEquals("Check date has invalid format", ex.getMessage());
        }
    }

    @Test
    public void testGetDecisioningResultAccountExceptions() throws Exception, SmartCheckApiException {
        Check check = scanCheck();
        Customer customer = createCustomer();

        check.getMicr().setTransitNumber(null);
        DecisioningRequest decisioningRequest = api.buildDecisioningRequest(customer, check);

        try {
            DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);
        } catch (SmartCheckApiException ex) {
            assertEquals("Aba number is missing", ex.getMessage());
        }
    }


    @Test
    public void testGetDecisioningResult() throws Exception {
        Check check = scanCheck();
        Customer customer = createCustomer();

        DecisioningRequest decisioningRequest = api.buildDecisioningRequest(customer, check);
        Log.i("SmartCheckTest", "decisioningRequest: " + decisioningRequest.toString());
        DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);
        Log.i("SmartCheckTest", "decisioningResult: " + decisioningResult.toString());
        assertNotNull(decisioningResult.getDecisionId());
    }


    @Test
    public void testGetFees() throws Exception {
        Check check = scanCheck();
        Fees fees = api.getFees(check);
        assertNotNull(fees.id);
        Log.i("SmartCheckTest", "fees: " + fees.toString());
    }

    @Test
    public void testGetFee() throws Exception {
        Check check = scanCheck();
        Fee fee = api.getFee(check);
        assertNotNull(fee.total_fees);
        Log.i("SmartCheckTest", "fees: " + fee.toString());
    }

    @Test
    public void testcalculateFees() throws Exception {
        Check check = scanCheck();
        Fees fees = api.getFees(check);
        int fee = api.calculateFee(fees, check);
        assertEquals(700, fee);
    }

    @Test
    public void testCheckDuplicate() throws Exception {
        Check check = scanCheck();
        boolean isDuplicate = api.checkDuplicate(check);
        assertFalse(isDuplicate);
    }

    @Test
    public void testCreateTransaction() throws Exception {
        Transaction transactionRequest = getTransactionRequest();
        Transaction transaction = api.createTransaction(transactionRequest);
        Log.i("SmartCheckTest", "transaction: " + transaction.toString());
        assertNotNull(transaction.getId());
    }

    @Test
    public void testDeclineTransaction() throws Exception {
        Transaction transactionRequest = getTransactionRequest();
        Transaction transaction = api.createTransaction(transactionRequest);
        String transactionId = api.declineTransaction(transaction);
        assertEquals(transactionId, transaction.getId());
    }

    @Test
    public void testReviewStatus() throws Exception {
        Transaction transactionRequest = getTransactionRequest();
        Transaction transaction = api.createTransaction(transactionRequest);
        ReviewStatus reviewStatus = api.getReviewStatus(transaction);

        assertEquals(true, reviewStatus.isPendingReview());
    }

    @Test
    public void testCancelTransaction() throws Exception {
        Transaction transactionRequest = getTransactionRequest();
        Transaction transaction = api.createTransaction(transactionRequest);
        String transactionId = api.cancelTransaction(transaction);
        assertEquals(transactionId, transaction.getId());
    }

    @Test
    public void approveTransaction() throws Exception {
        Transaction transactionRequest = getTransactionRequest();
        Transaction transaction = api.createTransaction(transactionRequest);
        String transactionId = api.approveTransaction(transaction);
        assertEquals(transactionId, transaction.getId());
    }

    @Test
    public void getTransactionByCustomer() throws Exception {
        // If using createCustomer () function, skip the duplicateCustomer section.
//        Customer customer = createCustomer();
        List<Transaction> listTrans = api.getTransactionByCustomer("customer_fc14ca2f-b367-430a-a384-67a74be4ef28", 10);
        assertEquals(2, listTrans.size());
    }

    @Test
    public void testMisnapAPI() throws Exception {
        Check check = new Check();
        Context context = InstrumentationRegistry.getTargetContext();
        Check.frontOriginalImage = getImage(context, R.raw.front);
        Check.rearOriginalImage = getImage(context, R.raw.rear);
        com.alltrustnetworks.smartcheck.models.misnap.Transaction transaction = misnapApi.depositCheck(check);

        TransactionData data = transaction.getTransactionData();
        int amount = data.getAmount();
        Micr micr = api.parseMirc(data.getMicr());
        micr.setAmount(amount + "");
        check.setMicr(micr);
        check.setAmount(amount);

        Log.i("SmartCheck", micr.toString());
    }

    private Bitmap getBitmap(Context context, int image) {
        InputStream ts = context.getResources().openRawResource(image);
        return BitmapFactory.decodeStream(ts);
    }

    // helpers
    private byte[] getImage(Context context, int image) {
        InputStream ts = context.getResources().openRawResource(image);
        Bitmap bitmap = BitmapFactory.decodeStream(ts);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    private Transaction getTransactionRequest() throws Exception {
        Check check = scanCheck();
        Fee fee = api.getFee(check);
        Customer customer = createCustomer();
        DecisioningRequest decisioningRequest = api.buildDecisioningRequest(customer, check);
        DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);

        return api.buildTransactionRequest(check, customer, decisioningResult, fee.fee_amount);
    }

    private Check scanCheck() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Check check = new Check();

        Check.frontOriginalImage = getImage(context, R.raw.front);
        Check.rearOriginalImage = getImage(context, R.raw.rear);
        com.alltrustnetworks.smartcheck.models.misnap.Transaction transaction = misnapApi.depositCheck(check);

        TransactionData transData = transaction.getTransactionData();

        int transactionId = transData.getTransactionId();
        File frontTiff = misnapApi.getCheckImage(context, transactionId, "front");
        File backTiff = misnapApi.getCheckImage(context, transactionId, "rear");
        Check.frontProcessedImage = Util.convertTiffToByteArray(context, frontTiff);
        Check.rearProcessedImage = Util.convertTiffToByteArray(context, backTiff);

        int amount = transData.getAmount();
        check.setAmount(amount);
        Micr micr = api.parseMirc(transData.getMicr());
        micr.setAmount(amount + "");
        check.setMicr(micr);
        check.setRawMicr(micr.getRawMicr());
        check.setCheckDate("2018-02-02");
        check.setPayeeName("Ron John");
        check.setPayerName("John Brown");
        check.setPayerAddress("700 Warrent Rd, New York");
        check.setStatus("success");
        return check;
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer cr = this.createCustomer();
        cr.middleName = "UPDATE_customer";
        Customer customer = api.updateCustomerById(cr);
        assertNotNull(customer);
    }

    @Test
    public void testGetInfoBusiness() throws Exception {
        InfoBusiness infoBusiness = api.getInfoBusiness();
        assertNotNull(infoBusiness);
        assertEquals("703-555-8332",infoBusiness.phone);
    }

    private Customer createCustomer() throws Exception {
        CustomerRequest cr = new CustomerRequest(
                "John",
                "",
                "Brown",
                "1969-01-02",
                "male",
                "",
                "",
                "test2@localhost.com",
                "114-11-9827",
                "good",
                address, loyaltyNumber, customerIdent,
                false,
                false
        );

        Customer customer = api.createCustomer(cr);
        return customer;
    }

    @Test
    public void testCreateIdOcr() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        String frontImageByteArr = Util.toBase64(getImage(context, R.raw.dl5_front));
        Image frontImage = new Image(frontImageByteArr);
        Image backImage = new Image(Util.toBase64(getImage(context, R.raw.dl5_back)));
        CustomerIdentificationOCR request = new CustomerIdentificationOCR(frontImage, backImage);
        IDOcrResult result = api.getIdOCR(request);
        assertNotNull(result);
    }

    @Test
    public void testVerifyPhoneNumber() throws Exception {
        String phoneNumber = "2625656589";
        VerifyPhoneResponse verifyPhoneResponse = api.getVerifyPhone(phoneNumber);

        assertNotNull(verifyPhoneResponse);
        assertEquals("+15712964661", verifyPhoneResponse.normalized_phone_number);
        assertTrue(verifyPhoneResponse.verified);
    }

    @Test
    public void testApiCallLocation() throws Exception {
        List<LocationsBusiness> data = api.getListInfoLocation();
        assertNotNull(data.get(0));
        Log.i("SmartCheck", data.toString());
    }

    @Test
    public void testApiCallLocationWithDistance() throws Exception {
        List<LocationsStore> data = api.getListInfoLocationByDistance(38.76228, -77.58548, 20);
        assertNotNull(data.get(0));
        Log.i("SmartCheck", data.toString());
    }

   @Test
    public void testGetImageCheck() throws Exception {
        String checkID = "check_73392ffc-6c86-4f16-9dfe-a7bbe4d6ce68";
        FrontBackImages data = api.getImageCheck(checkID);
         assertNotNull(data);
        Log.i("SmartCheck", data.toString());
    }

    // test with app profile

    @Test
    public void testConfirmPhone() throws Exception {
        PhoneConfirmResponse phoneConfirmResponse = api.confirmPhone("+17035551212");
        assertEquals(true, phoneConfirmResponse.success);
    }

    @Test
    public void testCreateProfile() throws Exception {
        CreateProfileRequest createProfileRequest = new CreateProfileRequest(
                "+84909143730",
                "iPhone se",
                "",
                "3FD557AE-BC99-49D9-A3DB-0D31BAF8EDE1");
        ProfileResponse profileResponse = api.createProfile(createProfileRequest);
        assertEquals("Existing consumer", profileResponse.msg);
    }


//    @Test
//    public void testUpdateProfile() throws Exception {
//        FormConsumer formConsumer = new FormConsumer(
//                24,
//                "+84909143730",
//                "",
//                "test 1"
//        );
//        ProfileResponse profileResponse = api.updateProfile(formConsumer);
//        assertEquals("Success update the consumer", profileResponse.msg);
//    }

    @Test
    public void testmirc() throws Exception {
        String rawMicr = "01050000001983";
        rawMicr = rawMicr
                .replaceAll("c", "U")
                .replaceAll("d", "T");

        int bStart = rawMicr.indexOf("b");
        int bEnd = rawMicr.lastIndexOf("b");
        if (bStart != -1) {//has b
            if (bEnd == -1) {
                bEnd = rawMicr.length() - 1;
            } else
                bEnd += 1;
            rawMicr = rawMicr.replace(rawMicr.substring(bStart, bEnd), "");
        }

        rawMicr = URLEncoder
                .encode(rawMicr, "UTF-8")
                .replaceAll("\\+", "%20");
        Log.i("SmartCheck", "mircline: " + rawMicr);
        assertNotNull(rawMicr);
    }

    @Test
    public void testListMaker() throws Exception {
        List<Maker> makers = api.getListMarkers("2703789845", "064107088");
        assertNotNull(makers);
    }

//    @Test
//    public void testSignUp() throws Exception {
//        SignUpRequest signUpRequest = new SignUpRequest(
//                "+84966764042",
//                "test",
//                "12345",
//                "5588d075e8b2eb7b");
//        EnrollmentResponse enrollmentResponse = api.signUp(signUpRequest);
//        assertEquals(true, enrollmentResponse.success);
//        assertEquals("Success singup", enrollmentResponse.msg);
//    }

    @Test
    public void testSignIn() throws Exception {
        SignInRequest signInRequest = new SignInRequest(
                "test",
                "12345",
                "5588d075e8b2eb7b");
        EnrollmentResponse enrollmentResponse = api.signIn(signInRequest);
        assertEquals(true, enrollmentResponse.success);
        assertEquals("Login success", enrollmentResponse.msg);
    }

    @Test
    public void testAddNewDevice() throws Exception {
        SignInRequest signInRequest = new SignInRequest(
                "lala2",
                "zxcvbn1@",
                "5588d075e8b2eb7b");
        EnrollmentResponse enrollmentResponse = api.addNewDevice(signInRequest);
        assertEquals(true, enrollmentResponse.success);
    }

    @Test
    public void testSendVerifyMail() throws Exception {
        EmailRequest emailRequest = new EmailRequest("quangdev605@gmail.com");
        StatusResponse statusResponse = api.sendVerifyToEmail(emailRequest);

        // email was activated
//        assertEquals(Constant.EMAIL_ACTIVATED,statusResponse.msg);
//        assertFalse(statusResponse.success);


        // email not found in consumer if email not sign up
//        assertEquals(Constant.EMAIL_DOES_NOT_EXIST,statusResponse.msg);
//        assertFalse(statusResponse.success);

        // send verify to email
//        assertEquals(Constant.SEND_VERIFY_TO_EMAIL,statusResponse.msg);
//        assertTrue(statusResponse.success);

        // resend verify to email
//        assertEquals(Constant.RESEND_VERIFY_TO_EMAIL,statusResponse.msg);
//        assertTrue(statusResponse.success);


    }

    @Test
    public void testCheckActiveEmail() throws Exception {
        EmailRequest emailRequest = new EmailRequest("quangdev605@gmail.com");
        StatusResponse statusResponse = api.checkEmailActive(emailRequest);

        // email was activated
//        assertEquals(Constant.EMAIL_ACTIVATED,statusResponse.msg);
//        assertFalse(statusResponse.success);

        // email wasn't activated
//        assertEquals(Constant.EMAIL_HAS_NOT_BEEN_ACTIVATED,statusResponse.msg);
//        assertFalse(statusResponse.success);


        // email not found in consumer if email not sign up
//        assertEquals(Constant.EMAIL_DOES_NOT_EXIST,statusResponse.msg);
//        assertFalse(statusResponse.success);


    }


    @Test
    public void testCancelTransactionReview() throws Exception {
        String transactionId = "checkcashing-event_84d5b4fc-e12f-4ac0-badc-43c76d88d318";
        String bodyString = api.cancelTransactionReview(transactionId);
        assertEquals(transactionId,bodyString);

    }

    @Test
    public void testGetListVerificationQuestions() throws Exception{
        IdVerificationResponse response = api.getListVerificationQuestions("customer_c76f7b57-e6cc-4b76-bab9-8c90fab35786");
        assertNotNull(response.getChallenge());
    }

    @Test
    public void testupdateEmail() throws Exception{
        UpdateEmailProfileRequest request = new UpdateEmailProfileRequest();
        request.customerId = "customer_8dfd1a31-8467-4ffb-b868-406993afa274";
        request.newUsername = "aq@yopmail.com";
        request.username = "aq1@yopmail.com";
        StatusResponse response = api.updateProfile(request);
        assertEquals(true, response.success);
    }

}
