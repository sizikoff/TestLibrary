package info.fandroid.quizapp.quizapplication.constants;

public class InAppBillingResources {
    private static final String RSA_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxbq1QcRHwM0dU0BTyznIw5mnwg6p2Qz9rr5IGvuQORs47aeLUknsbgN0Ly1DaVqqJY0foBweorRcrbAjnol+sqj4HG6a0TmqXJEu3ntMpMp8JRyZ6G25GqTZ4/vy9t8cmPhlSEfUMjv/GpI23JCNj/7aVVP8SQn10RY+42EccfrUr2/GGvaTGHdfIEnoGUR9J9+yRVWH7+XeSbWo09wyqFWCcj41BeB0ePoTP1Sen/Qig5YGlakt/cpuvcqXdDejF4r6YLPa33pCi44E9mE8ukWXtztiFe7jFkaou8jDqyqXFPTTXsDlWHCnX1W9qME7vVilUkOBm4MWXd+E719B4wIDAQAB"; // Ваш `RSA` ключ из `Google Play Developer Console`
    private static final String MERCHANT_ID = "12538744805189849137";           // Ваш `MERCHANT_ID` из `Google Play Developer Console`
    private static final String idi = "android.test.purchased";          // Ваш `product_id`, создается в `Google Play Developer Console`


    public static String getRsaKey() {
        return RSA_KEY;
    }

    public static String getMerchantId() {
        return MERCHANT_ID;
    }

    public static String getIdi() {
        return idi;
    }
}
