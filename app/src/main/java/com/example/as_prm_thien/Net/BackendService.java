package com.example.as_prm_thien.Net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class BackendService {
    private final Executor executor;

    public BackendService(Executor executor) {
        this.executor = executor;
    }

    public static String FromImageUuidToURL(String uuid) {
        return String.format("%s/image?uuid=%s", Config.URL, uuid);
    }

    // Register user: call this function to trigger OTP service (send to email)
    // To complete registration process, must call VerifyRegisterUser()
    public void RegisterUser(RegisterUserRequest request, final ResultCallBack<Boolean> callBack) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format("%s/register", Config.URL));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    String requestBody = new Gson().toJson(request);
                    OutputStream os = conn.getOutputStream();
                    os.write(requestBody.getBytes("utf-8"));

                    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        callBack.onComplete(new Result.Error<>(new Exception("failed")));
                        return;
                    }

                    callBack.onComplete(new Result.Success<>(new Boolean(true)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // VerifyRegisterUser: confirm OTP for completing the registration process
    public void VerifyRegisterUser(RegisterVerifyOTPRequest request, final ResultCallBack<Boolean> callBack) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format("%s/register/otp", Config.URL));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    String requestBody = new Gson().toJson(request);
                    OutputStream os = conn.getOutputStream();
                    os.write(requestBody.getBytes("utf-8"));

                    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        callBack.onComplete(new Result.Error<>(new Exception("failed")));
                        return;
                    }

                    callBack.onComplete(new Result.Success<>(new Boolean(true)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Call this function to send OTP to user's email. Then call VerifyLoginUser() to complete login process
    public void LoginUser(LoginUserRequest request, final ResultCallBack<Boolean> callBack) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format("%s/login", Config.URL));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    String requestBody = new Gson().toJson(request);
                    OutputStream os = conn.getOutputStream();
                    os.write(requestBody.getBytes("utf-8"));

                    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        callBack.onComplete(new Result.Error<>(new Exception("failed")));
                        return;
                    }

                    callBack.onComplete(new Result.Success<>(new Boolean(true)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void VerifyLoginUser(LoginUserVerifyRequest request, final ResultCallBack<LoginUserVerifyResponse> callBack) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format("%s/login/otp", Config.URL));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    String requestBody = new Gson().toJson(request);
                    OutputStream os = conn.getOutputStream();
                    os.write(requestBody.getBytes("utf-8"));

                    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        callBack.onComplete(new Result.Error<>(new Exception("failed")));
                        return;
                    }

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }

                        LoginUserVerifyResponse resp = new Gson().fromJson(response.toString(), LoginUserVerifyResponse.class);
                        callBack.onComplete(new Result.Success<>(resp));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void GetProducts(GetProductRequest request, final ResultCallBack<ArrayList<GetProductResponse>> callBack) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format("%s/product?offset=%d&limit=%d", Config.URL, request.getOffset(), request.getLimit()));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(false);

                    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        callBack.onComplete(new Result.Error<>(new Exception("failed")));
                        return;
                    }

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }

                        Type listProducts = new TypeToken<ArrayList<GetProductResponse>>() {
                        }.getType();
                        ArrayList<GetProductResponse> products = new Gson().fromJson(response.toString(), listProducts);

                        callBack.onComplete(new Result.Success<>(products));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
