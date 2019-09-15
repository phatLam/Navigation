package com.alltrustnetworks.smartcheck;

import android.util.Log;

import com.alltrustnetworks.smartcheck.exceptions.SmartCheckApiException;
import com.alltrustnetworks.smartcheck.models.Check;
import com.alltrustnetworks.smartcheck.models.CheckDuplicateRequest;
import com.alltrustnetworks.smartcheck.models.CheckDuplicateResponse;
import com.alltrustnetworks.smartcheck.models.CheckOcrRequest;
import com.alltrustnetworks.smartcheck.models.CheckOcrResponse;
import com.alltrustnetworks.smartcheck.models.CheckType;
import com.alltrustnetworks.smartcheck.models.Customer;
import com.alltrustnetworks.smartcheck.models.CustomerError;
import com.alltrustnetworks.smartcheck.models.CustomerIdentification;
import com.alltrustnetworks.smartcheck.models.CustomerIdentificationOCR;
import com.alltrustnetworks.smartcheck.models.CustomerRequest;
import com.alltrustnetworks.smartcheck.models.DecisioningResult;
import com.alltrustnetworks.smartcheck.models.DecisioningRequest;
import com.alltrustnetworks.smartcheck.models.DocId;
import com.alltrustnetworks.smartcheck.models.Fee;
import com.alltrustnetworks.smartcheck.models.FrontBackImages;
import com.alltrustnetworks.smartcheck.models.IDOcrResult;
import com.alltrustnetworks.smartcheck.models.IdVerificationChallenge;
import com.alltrustnetworks.smartcheck.models.IdVerificationResponse;
import com.alltrustnetworks.smartcheck.models.InfoBusiness;
import com.alltrustnetworks.smartcheck.models.LocationsBusiness;
import com.alltrustnetworks.smartcheck.models.LocationsStore;
import com.alltrustnetworks.smartcheck.models.Maker;
import com.alltrustnetworks.smartcheck.models.Micr;
import com.alltrustnetworks.smartcheck.models.Image;
import com.alltrustnetworks.smartcheck.models.ReviewStatus;
import com.alltrustnetworks.smartcheck.models.Transaction;
import com.alltrustnetworks.smartcheck.models.UpdateEmailProfileRequest;
import com.alltrustnetworks.smartcheck.models.VerifyPhoneResponse;
import com.alltrustnetworks.smartcheck.models.consumer.CreateProfileRequest;
import com.alltrustnetworks.smartcheck.models.consumer.EmailRequest;
import com.alltrustnetworks.smartcheck.models.consumer.EnrollmentResponse;
import com.alltrustnetworks.smartcheck.models.consumer.ForgotPasswordRequest;
import com.alltrustnetworks.smartcheck.models.consumer.ForgotPasswordResponse;
import com.alltrustnetworks.smartcheck.models.consumer.FormConsumer;
import com.alltrustnetworks.smartcheck.models.consumer.PhoneConfirm;
import com.alltrustnetworks.smartcheck.models.consumer.PhoneConfirmResponse;
import com.alltrustnetworks.smartcheck.models.consumer.PhoneRequest;
import com.alltrustnetworks.smartcheck.models.consumer.PhoneResponse;
import com.alltrustnetworks.smartcheck.models.consumer.ProfileResponse;
import com.alltrustnetworks.smartcheck.models.consumer.SignInRequest;
import com.alltrustnetworks.smartcheck.models.consumer.SignUpRequest;
import com.alltrustnetworks.smartcheck.models.consumer.SigninWithTemporaryPasswordRequest;
import com.alltrustnetworks.smartcheck.models.consumer.StatusResponse;
import com.alltrustnetworks.smartcheck.models.consumer.TemporaryRequest;
import com.alltrustnetworks.smartcheck.models.consumer.TemporaryResponse;
import com.alltrustnetworks.smartcheck.models.fees.BetweenRange;
import com.alltrustnetworks.smartcheck.models.fees.Fees;
import com.alltrustnetworks.smartcheck.models.fees.LowHighRange;
import com.alltrustnetworks.smartcheck.models.fees.Ranges;
import com.alltrustnetworks.smartcheck.util.Constant;
import com.alltrustnetworks.smartcheck.util.Util;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * SmartCheck API
 *
 * @author
 * @version 1.0
 */
public class SmartCheckApi {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String url;
    private String urlConsumer;
    private String apiToken;

    public String getBusinessId() {
        return businessId;
    }

    private String businessId;

    public String getLocationId() {
        return locationId;
    }

    private String locationId;
    private static SmartCheckApi instance = null;
    private OkHttpClient httpClient;
    private OkHttpClient httpClientConsumer;
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Customer> customerAdapter = moshi.adapter(Customer.class);
    private final JsonAdapter<VerifyPhoneResponse> verifyPhoneAdapter = moshi.adapter(VerifyPhoneResponse.class);
    private final JsonAdapter<DecisioningResult> recommendationAdapter = moshi.adapter(DecisioningResult.class);
    private final JsonAdapter<Micr> micrAdapter = moshi.adapter(Micr.class);
    private final JsonAdapter<Transaction> transactionAdapter = moshi.adapter(Transaction.class);
    private final JsonAdapter<Fees> feesAdapter = moshi.adapter(Fees.class);
    private final JsonAdapter<Fee> feeAdapter = moshi.adapter(Fee.class);
    private final JsonAdapter<Maker> makerAdapter = moshi.adapter(Maker.class);
    private final JsonAdapter<ReviewStatus> reviewStatusAdapter = moshi.adapter(ReviewStatus.class);
    private final JsonAdapter<CustomerRequest> customerRequestAdapter = moshi.adapter(CustomerRequest.class);
    private final JsonAdapter<DecisioningRequest> scoreRequestAdapter = moshi.adapter(DecisioningRequest.class);
    private final JsonAdapter<CheckOcrRequest> checkOcrAdapter = moshi.adapter(CheckOcrRequest.class);
    private final JsonAdapter<CustomerIdentificationOCR> customerOCRJsonAdapter = moshi.adapter(CustomerIdentificationOCR.class);
    private final JsonAdapter<IDOcrResult> idOcrResultAdapter = moshi.adapter(IDOcrResult.class);
    private final JsonAdapter<CheckOcrResponse> checkOcrResponseAdapter = moshi.adapter(CheckOcrResponse.class);

    private final JsonAdapter<InfoBusiness> infoBusinessJsonAdapter = moshi.adapter(InfoBusiness.class);

    private final JsonAdapter<PhoneConfirm> phoneConfirmJsonAdapter = moshi.adapter(PhoneConfirm.class);
    private final JsonAdapter<PhoneConfirmResponse> phoneConfirmResponseJsonAdapter = moshi.adapter(PhoneConfirmResponse.class);

    private final JsonAdapter<PhoneRequest> phoneRequestJsonAdapter = moshi.adapter(PhoneRequest.class);
    private final JsonAdapter<PhoneResponse> phoneResponseJsonAdapter = moshi.adapter(PhoneResponse.class);

    private final JsonAdapter<CreateProfileRequest> createProfileRequestJsonAdapter = moshi.adapter(CreateProfileRequest.class);
    private final JsonAdapter<FormConsumer> formConsumerJsonAdapter = moshi.adapter(FormConsumer.class);
    private final JsonAdapter<ProfileResponse> profileResponseJsonAdapter = moshi.adapter(ProfileResponse.class);

    private final JsonAdapter<CheckDuplicateResponse> checkDuplicateResponseJsonAdapter = moshi.adapter(CheckDuplicateResponse.class);
    private final JsonAdapter<CheckDuplicateRequest> checkDuplicateRequestJsonAdapter = moshi.adapter(CheckDuplicateRequest.class);

    private Type locationType = Types.newParameterizedType(List.class, LocationsBusiness.class);
    private final JsonAdapter<List<LocationsBusiness>> locationBusinessAdapter = moshi.adapter(locationType);

    private Type locationStoreType = Types.newParameterizedType(List.class, LocationsStore.class);
    private final JsonAdapter<List<LocationsStore>> locationStoreAdapter = moshi.adapter(locationStoreType);


    private Type identificationType = Types.newParameterizedType(List.class, CustomerIdentification.class);
    private final JsonAdapter<List<CustomerIdentification>> identificationAdapter = moshi.adapter(identificationType);

    private Type transactionType = Types.newParameterizedType(List.class, Transaction.class);
    private final JsonAdapter<List<Transaction>> listTransactionAdapter = moshi.adapter(transactionType);

    private Type makerType = Types.newParameterizedType(List.class, Maker.class);
    private final JsonAdapter<List<Maker>> listMakersAdapter = moshi.adapter(makerType);

    private final JsonAdapter<SignInRequest> signInRequestJsonAdapter = moshi.adapter(SignInRequest.class);
    private final JsonAdapter<IdVerificationChallenge> idVerificationChallenge = moshi.adapter(IdVerificationChallenge.class);
    private final JsonAdapter<SignUpRequest> signUpRequestJsonAdapter = moshi.adapter(SignUpRequest.class);
    private final JsonAdapter<EnrollmentResponse> enrollmentResponseJsonAdapter = moshi.adapter(EnrollmentResponse.class);
    private final JsonAdapter<Image> imageJsonAdapter = moshi.adapter(Image.class);

    private final JsonAdapter<TemporaryRequest> temporaryRequestJsonAdapter = moshi.adapter(TemporaryRequest.class);
    private final JsonAdapter<SigninWithTemporaryPasswordRequest> signinWithTemporaryPasswordRequestJsonAdapter = moshi.adapter(SigninWithTemporaryPasswordRequest.class);
    private final JsonAdapter<TemporaryResponse> temporaryResponseJsonAdapter = moshi.adapter(TemporaryResponse.class);
    private final JsonAdapter<ForgotPasswordRequest> forgotPasswordRequestJsonAdapter = moshi.adapter(ForgotPasswordRequest.class);
    private final JsonAdapter<ForgotPasswordResponse> forgotPasswordResponseJsonAdapter = moshi.adapter(ForgotPasswordResponse.class);

    private final JsonAdapter<EmailRequest> emailRequestJsonAdapter = moshi.adapter(EmailRequest.class);
    private final JsonAdapter<StatusResponse> statusResponseResponseJsonAdapter = moshi.adapter(StatusResponse.class);

    private final JsonAdapter<FrontBackImages> frontBackImagesResponseJsonAdapter = moshi.adapter(FrontBackImages.class);


    public static SmartCheckApi getInstance() {
        return instance;
    }

    public SmartCheckApi(String url, String apiToken, String businessId, String locationId, String urlConsumer) {
        this.url = url;
        this.apiToken = apiToken;
        this.businessId = businessId;
        this.locationId = locationId;
        SmartCheckApi.instance = this;
        httpClient = createAuthenticatedClient(this.apiToken);

        this.urlConsumer = urlConsumer;
        httpClientConsumer = createAuthenticatedClient();
    }

    ///////////////////Customer/////////////////////////////
    public Customer createCustomer(CustomerRequest customerRequest) throws IOException, SmartCheckApiException {

        if (DocId.frontProcessedImage != null &&
                customerRequest.getCustomerIdentification() != null) {
            Image image = new Image(Util.toBase64(DocId.frontProcessedImage));
            customerRequest.getCustomerIdentification().setFrontImage(image);
        }

        if (DocId.rearProcessedImage != null &&
                customerRequest.getCustomerIdentification() != null) {
            Image image = new Image(Util.toBase64(DocId.rearProcessedImage));
            customerRequest.getCustomerIdentification().setBackImage(image);
        }


        String json = customerRequestAdapter.toJson(customerRequest);

        Log.i("SmartCheck", "Customer request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.url + "/v1/customers")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        Customer customer;

        if (response.isSuccessful()) {
            customer = customerAdapter.fromJson(res);
            Log.i("SmartCheck", "Customer created : " + res);
        } else {
            Log.i("SmartCheck", "Customer error : " + res);
            if (res.contains("messages")) {
                throw new IOException(res);
            } else {
                res = res.replaceAll("\\\\n", "")
                        .replaceAll("\\\\", "")
                        .replaceFirst("\"\\{", "{")
                        .replaceFirst("\\}\"", "}");
                if (res.contains("duplicate_reason") && res.contains("duplicate_customer_id")) {
                    throw new SmartCheckApiException(res);
                } else throw new IOException(res);
            }
        }

        return customer;
    }

    public VerifyPhoneResponse getVerifyPhone(String phoneNumber) throws IOException {

        String phoneFormat = phoneNumber.replaceAll("\\D+", "");
        Request request = new Request.Builder()
                .url(this.url + "/v1/customers/phoneverification/" + phoneFormat)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        return verifyPhoneAdapter.fromJson(response.body().string());
    }

    public Customer getCustomerByCustomerId(String customerId) throws IOException {
        Request request = new Request.Builder()
                .url(this.url + "/v1/customers/" + businessId + "/" + customerId)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        Customer customer = customerAdapter.fromJson(response.body().string());

        return customer;
    }

    public InfoBusiness getInfoBusiness() throws IOException {
        Request request = new Request.Builder()
                .url(this.url + "/v1/businesses/" + businessId)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        InfoBusiness infoBusiness = infoBusinessJsonAdapter.fromJson(response.body().string());

        return infoBusiness;
    }

    public Customer updateCustomerById(Customer customerRequest) throws IOException {

        String json = customerAdapter.toJson(customerRequest);
        Log.i("SmartCheck", "Customer request: " + json);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.url + "/v1/customers/" + businessId)
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        if (!response.isSuccessful()) {
            throw new IOException(res);
        }
        Customer customer = customerAdapter.fromJson(res);
        Log.i("SmartCheck", "Customer update successfully : " + res);
        return customer;
    }

    public List<CustomerIdentification> searchCustomerByPhone(String phoneNumber) throws SmartCheckApiException, IOException {
        Request request = new Request.Builder()
                .url(this.url + "/v1/customers/search/cellphone/" + businessId + "/" + phoneNumber)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        if (!response.isSuccessful() && res.contains("messages")) {
            res = res.replaceAll("\n", "");
            throw new SmartCheckApiException(res);
        }

        List<CustomerIdentification> identifications = identificationAdapter.fromJson(res);
        Log.i("SmartCheck", "Customer update successfully : " + res);
        return identifications;
    }

    public IDOcrResult getIdOCR(CustomerIdentificationOCR customerOcrRequest) throws IOException {

        String json = customerOCRJsonAdapter.toJson(customerOcrRequest);
        Log.i("SmartCheck", "ID OCR request: " + json);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.url + "/v1/customers/id-ocr")
                .post(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        if (res.contains("error") || !response.isSuccessful() && res.contains("messages")) {
            throw new IOException(res);
        }

        IDOcrResult idOcrResult = idOcrResultAdapter.fromJson(res);
        if (idOcrResult != null) {
            idOcrResult.setAddress(idOcrResult.getAddress());
            idOcrResult.setCity(idOcrResult.getCity());
            idOcrResult.setFirstName(idOcrResult.getFirstName());
            idOcrResult.setLastName(idOcrResult.getLastName());
            idOcrResult.setMiddleName(idOcrResult.getMiddleName());
            idOcrResult.setState(idOcrResult.getState());
        }
        Log.i("SmartCheck", "ID OCR successfully : " + res);
        return idOcrResult;
    }

    public Customer getCustomerByIdNumber(String idNumber, String issuer) throws IOException, SmartCheckApiException {

        Request request = new Request.Builder()
                .url(this.url + "/v1/customers/id/" + businessId + "/" + idNumber + "/" + issuer)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        Customer customer;
        if (response.isSuccessful()) {
            customer = customerAdapter.fromJson(res);
            return customer;
        } else if (res.contains("messages")) {
            res = res.replaceAll("\n", "");
            throw new SmartCheckApiException(res);
        } else
            throw new IOException(res);
    }

    /////////////////////////
    public Maker getMaker(Check check) throws IOException, Exception {

        String abaNumber = check.getMicr().getTransitNumber();
        String accountNumber = check.getMicr().getAccountNumber();

        if (abaNumber == null) {
            throw new Exception("aba number not found");
        }

        if (accountNumber == null) {
            throw new Exception("account number not found");
        }

        Request request = new Request.Builder()
                .url(this.url + "/v1/makers/" + abaNumber + "/" + accountNumber + "/" + businessId)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();

        String resp = response.body().string();
        Log.i("SmartCheck", "maker: " + resp);
        Maker maker = makerAdapter.fromJson(resp);
        Log.i("SmartCheck", "maker: " + maker.toString());

        return maker;
    }

    public Micr parseMirc(String rawMicr) throws IOException, Exception {

        if (rawMicr == null) {
            throw new Exception("raw micr not found");
        }
        Log.i("SmartCheck", "before Mircline: " + rawMicr);
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
        Log.i("SmartCheck", "after mircline: " + rawMicr);

        Request request = new Request.Builder()
                .url(this.url + "/v1/checks/micr-parse/" + rawMicr)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        String resultStr = response.body().string();
        Log.i("SmartCheck", "mirc response: " + resultStr);
        Micr micr = micrAdapter.fromJson(resultStr);

        return micr;
    }

    public DecisioningResult getDecisioningResult(DecisioningRequest decisioningRequest) throws IOException, SmartCheckApiException {
        DecisioningResult decisioningResult = new DecisioningResult();

        String checkDate = decisioningRequest.getCheckDate();

        if (checkDate == null || checkDate.isEmpty()) {
            Log.i("SmartCheck", "throw exception : " + checkDate);
            throw new SmartCheckApiException("Check date is missing", "InvalidCheck");
        }

        if (!checkDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
            Log.i("SmartCheck", "throw exception : " + checkDate);
            throw new SmartCheckApiException("Check date has invalid format", "InvalidCheck");
        }

        Maker maker = decisioningRequest.getMaker();

        if (maker.getAbaNumber() == null) {
            throw new SmartCheckApiException("Aba number is missing", "InvalidCheck");
        }

        String json = scoreRequestAdapter.toJson(decisioningRequest);
        Log.i("SmartCheck", json);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(this.url + "/v1/decisioning/score")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();


        Log.i("SmartCheck", "decisioningResult response: " + res);

        if (response.isSuccessful()) {
            decisioningResult = recommendationAdapter.fromJson(res);
        } else {
            if (res.contains("messages")) {
                throw new SmartCheckApiException("Could not parse check", "InvalidCheck");
            } else {
                throw new SmartCheckApiException("Unknown exception", "InvalidCheck");
            }
        }

        Log.i("SmartCheck", "decisioningResult: " + decisioningResult.toString());
        return decisioningResult;
    }

    public Transaction createTransaction(Transaction transactionRequest) throws IOException {
        String jsonReq = transactionAdapter.toJson(transactionRequest);
        Log.i("SmartCheck", "transaction request: " + jsonReq);
        RequestBody body = RequestBody.create(JSON, jsonReq);
        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions")
                .post(body)
                .build();


        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        Transaction transaction = new Transaction();

        if (response.isSuccessful()) {
            transaction = transactionAdapter.fromJson(res);
        } else {
            Log.i("SmartCheck", res);
            Log.i("SmartCheck", response.toString());


            if (res.contains("messages")) {
                throw new IOException(res);
            } else {
                try {
                    JSONObject json = new JSONObject(res);
                    Log.i("SmartCheck", "Get transaction by id");
                    transaction = getTransactionById(json.getString("transaction_id"));
                } catch (JSONException e) {

                }
            }
        }

        Log.i("SmartCheck", "transaction response: " + transaction.toString());
        return transaction;
    }

    public List<Transaction> getTransactionByCustomer(String customerId, int limit) throws IOException {
        HttpUrl.Builder httpBuider = HttpUrl.parse(this.url + "/v1/transactions/customer/" + customerId).newBuilder();
        httpBuider.setQueryParameter("limit", String.valueOf(limit));
        List<Transaction> listTrans = new ArrayList<>();
        Request request = new Request.Builder()
                .url(httpBuider.build())
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        listTrans.clear();
        listTrans.addAll(listTransactionAdapter.fromJson(response.body().string()));
        return listTrans;
    }

    public Transaction getTransactionById(String transactionId) throws IOException {
        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions/" + transactionId)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        Transaction transaction = transactionAdapter.fromJson(response.body().string());

        return transaction;
    }

    public String cancelTransactionReview(String transactionId) throws IOException {
        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions/in-review/cancel/" + transactionId)
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String bodyString = response.body().string();
        return bodyString;
    }

//    /**
//     * Get fees for given check
//     *
//     * @param check current check object
//     * @return fees or null if fees are not present for given check
//     * @see com.alltrustnetworks.smartcheck.models.Check
//     * @see com.alltrustnetworks.smartcheck.models.fees.Fees
//     */
//    public Fees getFees(Check check) throws IOException {
//
//        String feeType = check.getKind();
//        if (feeType == null) {
//            feeType = CheckType.PAYROLL;
//        }
//
//        Request request = new Request.Builder()
//                .url(this.url + "/v1/fees/" + businessId + "/" + feeType)
//                .get()
//                .build();
//        Response response = httpClient.newCall(request).execute();
//
//        String feesStr = response.body().string();
//        Log.i("SmartCheck", "feesStr: " + feesStr);
//        Fees fees = null;
//
//        if (response.isSuccessful()) {
//            fees = feesAdapter.fromJson(feesStr);
//        }
//
//        return fees;
//    }

    public Fee getFee(Check check) throws IOException {
        String feeType = check.micr.checkType.toLowerCase();
        if (feeType == null) {
            feeType = CheckType.PAYROLL;
        }
        Request request = new Request.Builder()
                .url(this.url + "/v1/fees/calculate/" + locationId + "/" + feeType + "/" + check.amount)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String feeStr = response.body().string();
        Log.i("SmartCheck", "feesStr: " + feeStr);

        Fee fee = null;
        if (response.isSuccessful()) {
            fee = feeAdapter.fromJson(feeStr);
        }
        return fee;
    }


//    public int calculateFee(Fees fees, Check check) {
//        int result = 0;
//
//        if (fees == null) return result;
//
//        Ranges ranges = fees.getRanges();
//        int amount = check.getAmount();
//
//        if (ranges == null) return result;
//
//        LowHighRange least = ranges.getLeast();
//
//        if (least != null && least.isActive() && least.getAmount() >= amount) {
//            return calculateFee(least.getPercentage(), amount, least.getFlat());
//        }
//
//        LowHighRange greatest = ranges.getGreatest();
//
//        if (greatest != null && greatest.isActive() && greatest.getAmount() <= amount) {
//            return calculateFee(greatest.getPercentage(), amount, greatest.getFlat());
//        }
//
//        BetweenRange[] between = ranges.getBetween();
//
//        if (between != null) {
//            for (int i = 0; i < between.length; i++) {
//                if (between[i].isActive() && between[i].getLow() >= amount && between[i].getHigh() <= amount) {
//                    return calculateFee(between[i].getPercentage(), amount, between[i].getFlat());
//                }
//            }
//        }
//
//        return result;
//    }

//    private int calculateFee(int percentage, int amount, int flat) {
//        return (int) ((double) percentage / (double) 10000 * amount) + flat;
//    }

    public ReviewStatus getReviewStatus(Transaction transaction) throws IOException {

        Request request = new Request.Builder()
                .url(this.url + "/v1/review/status/" + transaction.getId())
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();

        String status = response.body().string();

        if (response.isSuccessful()) {
            return reviewStatusAdapter.fromJson(status);
        } else {
            return null;
        }
    }

    public boolean checkDuplicate(Check check) throws IOException {
        CheckDuplicateRequest duplicateRequest = new CheckDuplicateRequest(check);
        String json = checkDuplicateRequestJsonAdapter.toJson(duplicateRequest);
        Log.i("SmartCheck", "check duplicate request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.url + "/v1/checks/is-duplicate")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "check duplicate response: " + responseStr);
        CheckDuplicateResponse response1 = checkDuplicateResponseJsonAdapter.fromJson(responseStr);
        boolean isDuplicate = response1.is_duplicate;
        if (response1.duplicate_check_result != null) {
            String duplicate_result = response1.duplicate_check_result.toLowerCase();
            if (isDuplicate && (
                    duplicate_result.equals("voided") ||
                            duplicate_result.equals("cancelled") ||
                            duplicate_result.equals("declined"))
            ) {
                return false;
            }
        }
        return isDuplicate;

    }

    public String declineTransaction(Transaction transaction) throws IOException, Exception {
        RequestBody body = RequestBody.create(JSON, "");
        Log.i("SmartCheck", "decline transaction: " + transaction.getId());
        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions/" + transaction.getId() + "/declined")
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();

        String transactionId = response.body().string();
        Log.i("SmartCheck", "decline transaction: " + transactionId);

        if (response.isSuccessful()) {
            return transactionId.replaceAll("\"", "");
        } else {
            return null;
        }
    }

    public String approveTransaction(Transaction transaction) throws IOException, Exception {
        RequestBody body = RequestBody.create(JSON, "");
        Log.i("SmartCheck", "approve transaction: " + transaction.getId());
        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions/" + transaction.getId() + "/preapproved")
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String transactionId = response.body().string();
        Log.i("SmartCheck", "approve transaction: " + transactionId);
        if (response.isSuccessful()) {
            return transactionId.replaceAll("\"", "");
        } else {
            return null;
        }
    }

    public String cancelTransaction(Transaction transaction) throws IOException, Exception {
        RequestBody body = RequestBody.create(JSON, "");
        Log.i("SmartCheck", "cancel transaction: " + transaction.getId());

        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions/cancel/" + transaction.id)
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String result = response.body().string();
        Log.i("SmartCheck", "cancel transaction: " + result);

        if (!response.isSuccessful()) {
            throw new Exception(result);
        }

        return result.replaceAll("\"", "");
    }

    public CheckOcrResponse ocrCheck(CheckOcrRequest checkOcrRequest) throws Exception {
        String json = checkOcrAdapter.toJson(checkOcrRequest);
        Log.i("SmartCheck", "check OCR request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.url + "/v2/checks/ocr/" + locationId + "?detect_check_type=true")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        return checkOcrResponseAdapter.fromJson(responseStr);
    }

    public List<LocationsBusiness> getListInfoLocation() throws Exception {
        Request request = new Request.Builder()
                .url(this.url + "/v1/locations/business/" + businessId)
                .get()
                .build();
        Response response;
        String responseStr;
        List<LocationsBusiness> locationsBusiness;
        response = httpClient.newCall(request).execute();
        responseStr = response.body().string();
        locationsBusiness = locationBusinessAdapter.fromJson(responseStr);
        return locationsBusiness;
    }

    public List<LocationsStore> getListInfoLocationByDistance(double latitude, double longitude, int distance) throws Exception {
        Request request = new Request.Builder()
                .url(this.url + "/v1/locations/within/" + businessId + "/" + latitude + "/" + longitude + "/" + distance)
                .get()
                .build();
        Response response;
        String responseStr;
        List<LocationsStore> locationsStores;
        response = httpClient.newCall(request).execute();
        responseStr = response.body().string();
        Log.i("SmartCheck", "confirmPhone response = " + responseStr);
        locationsStores = locationStoreAdapter.fromJson(responseStr);
        return locationsStores;
    }

    public FrontBackImages getImageCheck(String checkId) throws Exception {
        Request request = new Request.Builder()
                .url(this.url + "/v1/deposit/images/" + checkId)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "get image check response = " + responseStr);
        return frontBackImagesResponseJsonAdapter.fromJson(responseStr);
    }

    // Consumer
    public PhoneConfirmResponse confirmPhone(String phoneNumber) throws Exception {
        String json = phoneConfirmJsonAdapter.toJson(new PhoneConfirm(phoneNumber));
        Log.i("SmartCheck", "Phone confirm: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/textconfirmation")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "confirmPhone response = " + responseStr);
        return phoneConfirmResponseJsonAdapter.fromJson(responseStr);
    }

    public PhoneResponse verifyPhone(PhoneRequest phoneRequest) throws Exception {
        String json = phoneRequestJsonAdapter.toJson(phoneRequest);
        Log.i("SmartCheck", "Phone request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/phoneverification")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "verifyPhone response = " + responseStr);
        return phoneResponseJsonAdapter.fromJson(responseStr);
    }

    public ProfileResponse createProfile(CreateProfileRequest createProfileRequest) throws Exception {
        String json = createProfileRequestJsonAdapter.toJson(createProfileRequest);
        Log.i("SmartCheck", "Create profile request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/profile/create")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "update Profile response = " + responseStr);
        return profileResponseJsonAdapter.fromJson(responseStr);
    }

//    public ProfileResponse updateProfile(FormConsumer formConsumer) throws Exception {
//        String json = formConsumerJsonAdapter.toJson(formConsumer);
//        Log.i("SmartCheck", "Update profile request: " + json);
//
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(this.urlConsumer + "/profile/update")
//                .post(body)
//                .build();
//
//        Response response = httpClientConsumer.newCall(request).execute();
//        String responseStr = response.body().string();
//        Log.i("SmartCheck", "create Profile response = " + responseStr);
//        return profileResponseJsonAdapter.fromJson(responseStr);
//    }

    public StatusResponse sendVerifyToEmail(EmailRequest emailRequest) throws Exception {
        String json = emailRequestJsonAdapter.toJson(emailRequest);
        Log.i("SmartCheck", "Email request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/confirm")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "status response = " + responseStr);
        return statusResponseResponseJsonAdapter.fromJson(responseStr);
    }

    public StatusResponse checkEmailActive(EmailRequest emailRequest) throws Exception {
        String json = emailRequestJsonAdapter.toJson(emailRequest);
        Log.i("SmartCheck", "Email active request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/active")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "status response = " + responseStr);
        return statusResponseResponseJsonAdapter.fromJson(responseStr);
    }


    // helpers
    public DecisioningRequest buildDecisioningRequest(Customer customer, Check check) throws Exception {
        Maker maker = new Maker(check);
        return new DecisioningRequest(customer, check, maker, businessId, locationId);
    }

    public Transaction buildTransactionRequest(Check check, Customer customer, DecisioningResult decisioningResult, String feeAmount) throws Exception {
        return new Transaction(check, customer, decisioningResult, locationId, businessId, feeAmount);
    }

    public List<Maker> getListMarkers(String acctNumber, String abbaNumber) throws Exception {

        Request request = new Request.Builder()
                .url(this.url + "/v1/makers/" + abbaNumber + "/" + acctNumber + "/" +
                        businessId)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "list makers response = " + responseStr);
        return listMakersAdapter.fromJson(responseStr);
    }

    public EnrollmentResponse signUp(SignUpRequest signUpRequest) throws Exception {
        String json = signUpRequestJsonAdapter.toJson(signUpRequest);
        Log.i("SmartCheck", "Create sign up request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/signup")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "signUp response = " + responseStr);
        return enrollmentResponseJsonAdapter.fromJson(responseStr);
    }

    public EnrollmentResponse signIn(SignInRequest signInRequest) throws Exception {
        String json = signInRequestJsonAdapter.toJson(signInRequest);
        Log.i("SmartCheck", "Create sign up request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/signin")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "sign In response = " + responseStr);
        return enrollmentResponseJsonAdapter.fromJson(responseStr);
    }

    public EnrollmentResponse addNewDevice(SignInRequest signInRequest) throws Exception {
        String json = signInRequestJsonAdapter.toJson(signInRequest);
        Log.i("SmartCheck", "Create sign up request: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.urlConsumer + "/new-device")
                .post(body)
                .build();

        Response response = httpClientConsumer.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "sign In response = " + responseStr);
        return enrollmentResponseJsonAdapter.fromJson(responseStr);
    }

    public void addTransactionPhoto(String idTransaction, Image photo) throws Exception {
        Log.d("SmartCheck", " call api addTransactionPhoto");
        String json = imageJsonAdapter.toJson(photo);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(this.url + "/v1/transactions/customer/photo/" + idTransaction)
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "transaction Photo response = " + responseStr);
    }


    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    private static OkHttpClient createAuthenticatedClient(final String apiToken) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor tokenInterceptor = chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Authorization", Credentials.basic(apiToken, ""))
                    .addHeader("Content-Type", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        builder.addInterceptor(tokenInterceptor)
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    private static OkHttpClient createAuthenticatedClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor tokenInterceptor = chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Authorization", Credentials.basic("ABC", ""))
                    .addHeader("Content-Type", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        builder.addInterceptor(tokenInterceptor)
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    private String getCredentials() {
        return Credentials.basic(apiToken, "");
    }

    public TemporaryResponse sendTemporaryPassword(TemporaryRequest temporaryRequest) throws IOException {
        RequestBody body = RequestBody.create(JSON, temporaryRequestJsonAdapter.toJson(temporaryRequest));
        Request request = new Request.Builder()
                .url(urlConsumer + "/temporary")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        return temporaryResponseJsonAdapter.fromJson(res);
    }

    public TemporaryResponse signInWithTemporaryPassword(SigninWithTemporaryPasswordRequest signInRequest) throws IOException {
        RequestBody body = RequestBody.create(JSON, signinWithTemporaryPasswordRequestJsonAdapter.toJson(signInRequest));
        Request request = new Request.Builder()
                .url(urlConsumer + "/temporary/password")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        return temporaryResponseJsonAdapter.fromJson(res);
    }

    public ForgotPasswordResponse resetWithNewPassword(ForgotPasswordRequest forgotPasswordRequest) throws IOException {
        RequestBody body = RequestBody.create(JSON, forgotPasswordRequestJsonAdapter.toJson(forgotPasswordRequest));
        Request request = new Request.Builder()
                .url(urlConsumer + "/forgotPassword")
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        return forgotPasswordResponseJsonAdapter.fromJson(res);
    }

    public IdVerificationResponse getListVerificationQuestions(String customerID) throws IOException {
        final JsonAdapter<IdVerificationResponse> idVerificationResponseAdapter = moshi.adapter(IdVerificationResponse.class);
        Request request = new Request.Builder()
                .url(url + "/v1/customers/verify/" + customerID + "/" + locationId)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "getListVerificationQuestions = " + responseStr);
        return idVerificationResponseAdapter.fromJson(responseStr);
    }

    public IdVerificationResponse submitVerificationQuestion(IdVerificationChallenge verificationChallenge, String customerID) throws IOException {
        final JsonAdapter<IdVerificationResponse> idVerificationResponseAdapter = moshi.adapter(IdVerificationResponse.class);

        String json = idVerificationChallenge.toJson(verificationChallenge);
        Log.i("SmartCheck", "submitVerificationQuestion: " + json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url + "/v1/customers/verify/" + customerID + "/" + locationId)
                .post(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "submitVerificationQuestion = " + responseStr);
        return idVerificationResponseAdapter.fromJson(responseStr);
    }

    public StatusResponse updateProfile(UpdateEmailProfileRequest updateEmailProfileRequest) throws IOException {
        JsonAdapter<UpdateEmailProfileRequest> jsonAdapter = moshi.adapter(UpdateEmailProfileRequest.class);
        RequestBody body = RequestBody.create(JSON, jsonAdapter.toJson(updateEmailProfileRequest));
        Log.i("SMARTCHECK", "updateEmailProfileRequest body = " + body.toString());
        Request request = new Request.Builder()
                .url(urlConsumer + "/consumers/update")
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "updateEmail = " + responseStr);
        return statusResponseResponseJsonAdapter.fromJson(responseStr);
    }

    /**
     * search customer by phone + idNumber
     *
     * @param phone
     * @param idNumber
     * @return
     * @throws SmartCheckApiException if customer doesn't found
     * @throws IOException
     */
    public String searchCustomer(String phone, String idNumber) throws SmartCheckApiException, IOException {
        Request request = new Request.Builder()
                .url(url + "/v1/customers/search/" + phone + "/" + businessId + "/" + idNumber)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "searchCustomer = " + responseStr);
        if (!response.isSuccessful())
            throw new SmartCheckApiException("customer not found");
        return responseStr;
    }

    public EnrollmentResponse getConsumerByIDCustomer(String idCustomer) throws SmartCheckApiException, IOException {
        JsonAdapter<EnrollmentResponse> responseJsonAdapter = moshi.adapter(EnrollmentResponse.class);
        Request request = new Request.Builder()
                .url(urlConsumer + "/consumers/cusid/" + idCustomer)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        Log.i("SmartCheck", "getConsumerByIDCustomer = " + responseStr);
        EnrollmentResponse enrollmentResponse = responseJsonAdapter.fromJson(responseStr);
        if (!response.isSuccessful()) {
            throw new SmartCheckApiException(enrollmentResponse.msg);
        }
        return enrollmentResponse;

    }

}
