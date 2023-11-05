package com.example.as_prm_thien;

import static org.junit.Assert.assertEquals;

import com.example.as_prm_thien.Net.BackendService;
import com.example.as_prm_thien.Net.Config;
import com.example.as_prm_thien.Net.GetProductRequest;
import com.example.as_prm_thien.Net.GetProductResponse;
import com.example.as_prm_thien.Net.LoginUserRequest;
import com.example.as_prm_thien.Net.LoginUserVerifyRequest;
import com.example.as_prm_thien.Net.LoginUserVerifyResponse;
import com.example.as_prm_thien.Net.RegisterUserRequest;
import com.example.as_prm_thien.Net.RegisterVerifyOTPRequest;
import com.example.as_prm_thien.Net.Result;
import com.example.as_prm_thien.Net.ResultCallBack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BackendServiceUnitTest {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    BackendService backendService = new BackendService(executorService);

    @Test
    public void RegisterUser() {
        backendService.RegisterUser(new RegisterUserRequest("9999", "touristversion2@gmail.com", "Son Le"), new ResultCallBack<Boolean>() {
            @Override
            public void onComplete(Result<Boolean> result) {
                assertEquals(result instanceof Result.Success, true);
            }
        });
//        executorService.shutdown();
//        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
//        }
    }

    @Test
    public void VerifyRegistrationUser() throws InterruptedException {
        backendService.VerifyRegisterUser(new RegisterVerifyOTPRequest("touristversion2@gmail.com", "345888"), new ResultCallBack<Boolean>() {
            @Override
            public void onComplete(Result<Boolean> result) {
                assertEquals(result instanceof Result.Success, true);
            }
        });

//        executorService.shutdown();
//        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
//        }
    }

    @Test
    public void LoginUser() throws InterruptedException {
        backendService.LoginUser(new LoginUserRequest("touristversion2@gmail.com"), new ResultCallBack<Boolean>() {
            @Override
            public void onComplete(Result<Boolean> result) {
                assertEquals(result instanceof Result.Success, true);
            }
        });

        executorService.shutdown();
        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
        }
    }

    @Test
    public void VerifyLoginUser() throws InterruptedException {
        backendService.VerifyLoginUser(new LoginUserVerifyRequest("touristversion2@gmail.com", "145389"), new ResultCallBack<LoginUserVerifyResponse>() {
            @Override
            public void onComplete(Result<LoginUserVerifyResponse> result) {
                assertEquals(result instanceof Result.Success, true);
                if (result instanceof Result.Success) {
                    LoginUserVerifyResponse resp = ((Result.Success<LoginUserVerifyResponse>) result).data;
                    System.out.println(resp);
                }
            }
        });

//        executorService.shutdown();
//        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
//        }
    }

    @Test
    public void FromImageUuidToURL() {
        assertEquals("http://35.187.224.84:9003/image?uuid=91bb428cac704d91908f156b90e56981", BackendService.FromImageUuidToURL("91bb428cac704d91908f156b90e56981"));
    }

    @Test
    public void GetProducts() throws InterruptedException {
        backendService.GetProducts(new GetProductRequest(0, 1000), new ResultCallBack<ArrayList<GetProductResponse>>() {
            @Override
            public void onComplete(Result<ArrayList<GetProductResponse>> result) {
                assertEquals(true, result instanceof Result.Success);
                if (result instanceof Result.Success) {
                    ArrayList<GetProductResponse> products = ((Result.Success<ArrayList<GetProductResponse>>) result).data;
                    assertEquals(2, products.size());
                    assertEquals("91bb428cac704d91908f156b90e56981", products.get(0).getImage_url());

                    System.out.println("products " + products);
                }
            }
        });

        executorService.shutdown();
        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
        }
    }
}