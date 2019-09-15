SmartCheck SDK v1.0.0
===

SmartCheck SDK is an Android library which serves two primary functions:

1.  Scan and OCR checks and IDs.
2.  Integration with ATWS Server API in order to perform customer enrollment and check processing.

Integration with existing Android project
--

SmartCheck SDK is built as an AAR file which can be integrated with the exisiting Android app. Please follow the steps below in order to integrate it with your project.

1. Copy `aar` files from `./release/` into your `app/libs` folder.

2. Add `compileOptions` to `app/build.gradle` under `buildscript`:
    ```js
    android {
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
    }

    ```

3. And new dependencies to your module `app/build.gradle` :
    ```js
    dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar'])

        // support dependencies
            implementation "com.android.support:appcompat-v7:26.1.0"
            implementation "com.android.support:percent:26.1.0"
            implementation 'com.android.support.constraint:constraint-layout:1.1.3'

            // MISC dependencies
            implementation 'gun0912.ted:tedpermission:2.2.2'
            implementation 'com.squareup.okio:okio:1.14.0'
            implementation 'com.squareup.okhttp3:okhttp:3.11.0'
            implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'
            implementation 'com.squareup.moshi:moshi:1.5.0'
            implementation 'org.parceler:parceler-api:1.1.10'
            implementation(name:'Android-TiffBitmapFactory', ext:'aar')
            annotationProcessor 'org.parceler:parceler:1.1.10'

            // Regula dependencies
            implementation 'com.regula.documentreader.fullrfid:core:+@aar'
            implementation('com.regula.documentreader:api:+@aar') {
                transitive = true;
            }

            // Mitek dependencies
            implementation (name:'api-release', ext:'aar')
            implementation (name:'sanselan-release', ext:'aar')
            implementation(name: 'misnapcamera-release', ext: 'aar') {
                exclude module: 'appcompat-v7'
            }
            implementation(name: 'misnapscience-release', ext: 'aar') {
                exclude module: 'appcompat-v7'
            }
            implementation (name:'misnapworkflow-release', ext:'aar')
            implementation "de.greenrobot:eventbus:2.4.0"

            // SmartCheck SDK
            implementation(name:'SmartCheckSDK', ext:'aar')

        // your other dependencies
    }
    ```

4. Update project's `build.gradle` allprojects.repositories with:
    ```js
    flatDir {
        dirs 'libs'
    }
     maven {
        url "http://maven.regulaforensics.com/RegulaDocumentReader"
    }
    ```

    for example:
    ```js
    allprojects {
        repositories {
            google()
            jcenter()
            flatDir {
                dirs 'libs'
            }
            maven {
                url "http://maven.regulaforensics.com/RegulaDocumentReader"
            }
        }
    }
    ```

5. Modify your `app/src/main/AndroidManifest.xml`

    - add new permissions
    ```xml
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-feature android:name="android.hardware.camera" android:required="false" />
    <uses-feature android:name="android.hardware.camera.autofocus" android:required="true" />
    ```

That should be it in order to start using the SDK.

Usage via SmartCheck Activity
--

The SDK comes with couple preexisting activities which can be used to open the camera, take a photo, perform the OCR and return the result to your starting activity.

Example usage (driver license / passport ocr):

```java
Intent intent = new Intent(this, SmartCheckActivity.class);
startActivityForResult(intent, DocumentType.DOC_ID);
```

Example usage (check ocr):

```java
Intent intent = new Intent(this, SmartCheckActivity.class);
startActivityForResult(intent, DocumentType.CHECK);
```

After OCR is performed the result can be captured via

```java
onActivityResult(int requestCode, int resultCode, Intent intent)
```

for example:

```java
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    if (requestCode == DocumentType.DOC_ID) {
        DocId docId = Parcels.unwrap(intent.getParcelableExtra(SmartCheckActivity.DOC_ID));
    }

    if (requestCode == DocumentType.CHECK) {
        Check check = Parcels.unwrap(intent.getParcelableExtra(SmartCheckActivity.CHECK));
    }
}
```


Access Images
--

Images are stored as `transient` and `static` fields to avoid memory issues when passing parcel objects between activities.
After OCR process is done the images can be accessed via `DocId` or `Check` models.

DocId images:

- DocId.frontProcessedImage - cropped color image before OCR processing
- DocId.rearProcessedImage - cropped color image before OCR processing
- DocId.portraitImage - cropped color customer image

Check images:

- Check.frontOriginalImage - originial image taken with the camera
- Check.frontProcessedImage - black and white image after OCR processing and filters were applied
- Check.rearOriginalImage - originial image taken with the camera
- Check.rearProcessedImage - black and white image after OCR processing and filters were applied


The images are stored as byte arrays. In order to show them on the screen you can use:

```java
if (Check.frontOriginalImage != null && Check.frontOriginalImage.length > 0) {
    Bitmap bitmap = BitmapFactory.decodeByteArray(Check.frontOriginalImage, 0, Check.frontOriginalImage.length);
    ImageView imageView = (ImageView) findViewById(R.id.image_preprocessed);
    imageView.setImageBitmap(bitmap);
}
```

Usage via SmartCheck API
--

SmartCheck SDK comes with the built-in API which can be used to interact with the server or OCR documents directly.

Current API
--

```java

// Create new customer
public Customer createCustomer(CustomerRequest customerRequest)

// Update Customer
public Customer updateCustomerById(Customer customerRequest)

// get Id OCR
public IDOcrResult getIdOCR(CustomerIdentificationOCR customerOcrRequest)

//get Check OCR
public CheckOcrResponse ocrCheck(CheckOcrRequest checkOcrRequest)
// Parse micr string
public Mirc parseMirc(String mircStr)

// Get decisioningResult
public DecisioningResult getDecisioningResult(DecisioningRequest decisioningRequest)

// Get fees
public Fees getFee(Check check)

// Create transaction
public Transaction createTransaction(Transaction transactionRequest)

// Get review status
public String getReviewStatus(Transaction transaction)

// Cancel transaction
public String /* transactionId */ cancelTransaction(Transaction transaction)

// Decline transaction
public String /* transactionId */ declineTransaction(Transaction transaction)

// Approve transaction
public String /* transactionId */ approveTransaction(Transaction transaction)


// helper methods

// create DecisioningRequest object based on the given customer and check
DecisioningRequest buildDecisioningRequest(Customer customer, Check check)

// create Transaction request object which can be used in createTransaction based on given check and decisioningResult
 Transaction buildTransactionRequest(Check check, Customer customer, DecisioningResult decisioningResult)

````

In order to initialize a `SmartCheckAPI` you will need to add SmartCheckSDK.getInstance() :


```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SmartCheckSDK.getInstance();
    }
}
```

 Usage
--

### Enrollment

###### You  use `createCustomer` to create a customer.

```java
// enroll new customer
Customer customer = api.createCustomer(customerRequest);
```
The argument to `createCustomer` is a CustomerRequest object. A CustomerRequest contains the following fields:

- `firstName` (string)
- `middleName` (string, optional)
- `lastName` (string)
- `dateOfBirth` (string, optional): if present should be in ISO-8601 date format (eg. 1955-03-26)
- `gender` (string, optional) = ['male' or 'female' or 'unknown']
- `address` (Address)
- `cellPhone` (string)
- `homePhone` (string, optional)
- `email` (string, optional)
- `ssn` (string, optional)
- `status` (string) = ['good' or 'neutral' or 'bad' or 'high-risk']
- `recMarketingMessages` (boolean)
- `loyaltyNumber` (LoyaltyNumber, optional)
- `customerIdentification` (CustomerIdentification, optional)
- `customerImage` (Image, optional)
- `noDuplicateCheck` (boolean, optional): used only during customer creation and update - this field being set to 'true' indicates that duplicate identification and/or SSN is allowable. If this is not present during creation or update, it defaults to 'false'. Setting this to 'true' is not a good idea.

A `Address` contains the following fields:
- `address1` (string),
- `address2` (string, optional),
- `city` (string),
- `state` (string),
- `zip` (string)

A `LoyaltyNumber` contains the following fields:
- loyaltyNumber (string)

A `CustomerIdentification` contains the following fields:
- `id_type` (string),
- `id_number` (string),
- `issuer` (string),
- `expiration_date` (string, optional),
- `front_image` (Image, optional),
- `back_image` (Image, optional)

Implementation Notes
In case a duplicate is found (an identification and/or SSN that belongs to another customer), the response string in the 409 response will indicate
what element was duplicate in the "duplicate_reason" field. These will be one of:

- "id-dupe" for duplicate identification
- "ssn-dupe" for duplicate SSN
- "id-ssn-dupe" for duplicate identification and SSN
The id of the customer that is duplcated will be present in the "duplicate_customer_id" field.


###### Update Customer

You should submit all required parameters if you want to update customer profile

- `id` (String)
- `firstName` (String)
- `middleName` (String, optional)
- `lastname` (String)
- `dateOfBirth` (String)
- `gender` (String) Gender should be ["male", "female", "unknown"]
- `cellPhone` (String)
- `homePhone` (String, optional)
- `email` (String, optional)
- `ssn` (String, optional)
- `reMarketingMessages` (Boolean)
- `hasFingerPrint` (Boolean)
- `noDuplicateCheck` (Boolean)
- `address` (Address)
- `loyaltyNumber` (LoyaltyNumber)
- `customerIdentification` (CustomerIdentification)
- `customerImage` (CustomerImage)

If you want to update customer identification `CustomerIdentification` is required
`CustomerIdentification` contains the following fields:
- `id_type` (string)
- `id_number` (string)
- `issuer` (string)
- `expiration_date` (string)
- `front_image` (Image)
- `back_image` (Image)

If Customer has profile image, `CustomerProfile` is required.
`CustomerImage` contains the following fields:
- `id` (string)
- `customer_id` (string)
- `imageType` (string)
- `image` (string)


### Check processing


The `createTransaction` method allows you to create a transaction after having Customer and Check.
 Here's a sample implementation of getting a Check after scanning:
```java
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    if (requestCode == DocumentType.CHECK) {
        Check check = Parcels.unwrap(intent.getParcelableExtra(SmartCheckActivity.CHECK));
    }
}
```
###### Check Model
With APIs require Check Model, you can get it from scanning check result.

`Check` contains the following fields:

- `car` (Int)
- `amount` (Int)
- `rawMicr` (String)
- `payeeName` (String)
- `payerName` (String)
- `payerAddress` (String)
- `checkDate` (String)
- `checkNumber` (String)
- `micr` (Micr)
- `maker` (Maker)
- `kind` (String)
- `frontOriginalImage` (byte[])
- `rearOriginalImage` (byte[])
- `frontProcessedImage` (byte[])
- `rearProcessedImage` (byte[])
- `status` (String)
- `error`

To create a `transactionRequest` you use this tutorial:

```java
DecisioningRequest decisioningRequest = api.buildDecisioningRequest(customer, check);
DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);
Transaction transactionRequest = api.buildTransactionRequest(check, customer, decisioningResult);
```

```java

// create transaction
Transaction trasaction = api.createTransaction(transactionRequest);

// get decisioningResult
DecisioningResult decisioningResult = api.getDecisioningResult(decisioningRequest);

// cancel transaction
String trasactionId = api.cancelTransaction(transaction);

// decline transaction
String trasactionId  = api.declineTransaction(transaction);

// approve transaction
String trasactionId  = api.approveTransaction(transaction);

```
