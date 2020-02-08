package sch.id.aqilah4.elearning.network;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sch.id.aqilah4.elearning.models.RequestReport;
import sch.id.aqilah4.elearning.models.ResponseAuth;
import sch.id.aqilah4.elearning.models.ResponseCategory;
import sch.id.aqilah4.elearning.models.ResponseDetailCategory;
import sch.id.aqilah4.elearning.models.ResponseHistory;
import sch.id.aqilah4.elearning.models.ResponseLatest;
import sch.id.aqilah4.elearning.models.ResponsePackage;
import sch.id.aqilah4.elearning.models.ResponsePassedExam;
import sch.id.aqilah4.elearning.models.ResponseReport;


public interface RequestAPI {
    @FormUrlEncoded
    @POST("auth/signin")
    Observable<ResponseAuth> login(@Field("username") String username,
                                   @Field("password") String password);
    @FormUrlEncoded
    @POST("auth/signup")
    Observable<ResponseAuth> register(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("fullname") String fullname,
                                      @Field("stage") String level);
    @GET("category")
    Observable<ResponseCategory> category(@Header("Authorization") String token);

    @GET("package/latest")
    Observable<ResponseLatest> latestpackage(@Header("Authorization") String token);
    @GET("category/detail")
    Observable<ResponseDetailCategory> detailcategory(@Header("Authorization") String token,
                                                      @Query("id") String categoryId);
    @GET("package")
    Observable<ResponsePackage> examination(@Header("Authorization") String token,
                                            @Query("id") String examId);
    @GET("package/passexam")
    Observable<ResponsePassedExam> passedexam(@Header("Authorization") String token,
                                              @Query("user") String userId,
                                              @Query("exam") String examId);
    @POST("package/passexam")
    Observable<ResponseReport> passreport(@Header("Authorization") String token,
                                          @Body RequestReport requestReport);

    @GET("package/history")
    Observable<ResponseHistory> myhistory(@Header("Authorization") String token,
                                          @Query("user") String userKon);

    @FormUrlEncoded
    @POST("auth/change")
    Observable<ResponseAuth> changepassword(@Header("Authorization") String token,
                                            @Field("username") String username,
                                            @Field("oldpassword") String oldpassword,
                                            @Field("newpassword") String newpassword);
}
