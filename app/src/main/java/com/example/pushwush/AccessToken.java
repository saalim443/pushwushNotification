package com.example.pushwush;

import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AccessToken {
    private static final String firebaseMessagingScope =
            "https://www.googleapis.com/auth/firebase.messaging";

    public String getAccessToken() {
        try {
            String jsonString = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"pushwush-3aaba\",\n" +
                    "  \"private_key_id\": \"dcaaec31cbb6b7bede5fa460544f8bc4baa86224\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDUBMxm2w5fw614\\n6gvXSSpVL1HhG3L/6bMOrs5a5AanvIhNYY3n8rRnZX2JPkutJJkum1mlMucy+x+r\\nzjHbA6H6USbN0a9rQBxAo+bXi3pPIsnMfnaZuruWE49wleDI3lIjzXh96498vRJx\\nCOKr/yJeBGRzgjDdcBIUikAqOWHVaCJxTwZ+5nX/ovciR8ZEt0bXPn/lVBbWhzjd\\ncJ6h2mPHot5cCUgLrdI+i7yM6TzKQH40f7HkfwmE32SsmtNpCxSJVz4BqmLyQC4q\\naoae6FhfMteMMgzxs+/5q1Yde+A3i7B30/g51OiJnU3Dc7KmaqdVm6pjFnKcMspu\\ndjbWDmYTAgMBAAECggEAE/CFlrDIV8pL4MDGL0+SuyMxpKWTuaytadaX0qV6WMEa\\n4ogns+Y4ADdjyIoCzPxRqbfzhN1WMA29C0ffmXTsgWGrcBTl1gTYA15GwgO8k3+2\\nkdRc7BVLOhCJ33FBDDMYb4SN7e2+92DCwL2wr7NVdJkeJ1zZoqG9GZBs+2udH1g5\\nd7ykBaOI0D81H3IfnuBqDIqGdkl0jZjn9L+AVAn1XQcpsj2E66C3GhimzaM37GiR\\nnrGkrGe1gbI/GrJSKpoAJuPjo0Il9szyB5dkdoQE4edX65sSnps2f/0nwt5JGQBp\\ncls3vlesASsQL1sMFeNoMs4WSL6zLb72TnCeyU6cSQKBgQDxYX43nPgj2RDSz3bU\\nyGypkaH4naGWt4K0xgrz2+hkbmgDAYizjXzihM9/WFe0UzCA0TrBuDFr/vTJEfRc\\n5zUjT9LMbLQmp3dR99/DuctC6+4jv+oDfp1sksMiVRRKzXYn+lK1xgu8CZ8cQmkv\\n6Er8riyss2h8zPbcY2SovakKGQKBgQDg3A8Jj0Y16oK3lDPbE9RzaBpg/iR6jaW7\\nM6SBkalrXEgY3uK4X3hzK50CUVSZUq2iVR2hhlsI5QVNYCbKXoYvUuS4ZIU1wKS7\\nXNPm44PoU2wq8Vn9yqZ9Sbjs3HF4aUDF6L21mupIkDrxtb03gUaE0+fP5qwddO53\\nF22g3z+PCwKBgFWTzg7jfOogtCE5YpTv9e5sbnNZV4e6ygFTUWDBr3Oj3QZrzc5y\\ntDsPAQ3SAU4yTDjvdRhnOEZknt6Gmg8x/ZnmXAMT6w7lQp1x9whKX7D7OnO8P7g7\\ndI6gvcP3S/l1PJXR3u8JJvHkq192gBp3Ddbl3XrhJINSm5bpOi/FBiBJAoGBAKLU\\neEcx62PruFD8Dzkb+QLBZijKoNvmaxdO5Fe3hXnrT4JDFObyq6Sq9n3RK3QD/RzK\\nijSHuLCON/K6KBzvqjfmzR4CHLy6DhBnvkM6ijDZCEFomQRBlaKwmitbzwelIHSw\\nf4Bc+D8nwqd6NdVV1h0l3n4cypJiNLUk0Axo3fvVAoGAdsobS8CsnE4j4amPL+i2\\nxwO+PqpkBf8ZdBuROUwqjFnAwWv+fzpYfq3XWM6mAh7vkZX6TKAduQM7Y5SeKvUN\\nBaXqkHgVrMLrg2Jsg2cDGjrc2mbqFxP0f2c+LmQ4OBonr0oQ9/eOd5Ps3YPy+Qj8\\nyrZ03Oz5agDDjSLSBBg9UqM=\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-nnoyz@pushwush-3aaba.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"112784345795114258850\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-nnoyz%40pushwush-3aaba.iam.gserviceaccount.com\",\n" +
                    "  \"universe_domain\": \"googleapis.com\"\n" +
                    "}\n";

            InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(stream)
                    .createScoped(firebaseMessagingScope);
            googleCredentials.refresh();
            return googleCredentials.getAccessToken().getTokenValue();
        } catch (Exception e) {
            Log.e("AccessToken", "getAccessToken failed", e);
            return null;
        }
    }
}

