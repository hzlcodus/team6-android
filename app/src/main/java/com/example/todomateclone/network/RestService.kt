package com.example.todomateclone.network

import com.example.todomateclone.network.dto.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*
import java.time.LocalDate

interface RestService {
    // Login, SignUp, Social Login API
    @POST("/accounts/login/")
    suspend fun login(@Body() request: LoginRequest): LoginResult

    @POST("/accounts/logout/")
    suspend fun logout(
        @Header("authorization") accessToken: String,
    ): Response<Unit>

    @POST("/accounts/registration/")
    suspend fun signup(@Body() request: SignupRequest): SignupResult

    @POST("/accounts/registration/confirm/")
    suspend fun signupConfirm(@Body() request: SignUpConfirmRequest): SignUpConfirmResult

    @POST("/accounts/resend-email/")
    suspend fun resendEmail(@Body() request: ResendEmailRequest): ResendEmailResult

    @GET("/accounts/user/")
    suspend fun getUser(): GetUserResult

    @PUT("/accounts/user/")
    suspend fun updateUser(
        @Body() request: UpdateUserRequest
    ): UpdateUserResult

    @DELETE("/accounts/user/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ): Response<Unit>

    @POST("/accounts/kakao/login")
    suspend fun kakaoLogin(@Body() request: SocialLoginRequest): LoginResult

    @POST("/accounts/google/login/")
    suspend fun googleLogin(@Body() request: SocialLoginRequest): LoginResult


    // Task API

    @POST("/task/list/{date}/")
    suspend fun createTask(@Body() request: CreateTaskRequest, @Path("date") date: String): TaskDTO

    @GET("/task/list/{date}/")
    suspend fun getTasksByDateFirstPage(@Path("date") date: String): GetTasksByDateResponse

    @GET("/task/list/{date}/")
    suspend fun getTasksByDatePaged(@Path("date") date: String, @Query("page") page: Int): GetTasksByDateResponse

    @GET("/task/search/{uid}/list/{date}/")
    suspend fun getSearchedTasksByDateFirstPage(@Path("date") date: String, @Path("uid") uid: Int): GetTasksByDateResponse

    @GET("/task/search/{uid}/list/{date}/")
    suspend fun getSearchedTasksByDatePaged(@Path("date") date: String, @Path("uid") uid: Int, @Query("page") page: Int): GetTasksByDateResponse

//    @GET("/task/list/")
//    suspend fun getAllTasksPaged(): GetAllTasksResponse

    @GET("/task/detail/{tid}/check/")
    suspend fun checkTask(@Path("tid") tid: Int): TaskDTO

    @DELETE("/task/detail/{tid}/")
    suspend fun deleteTask(@Path("tid") tid: Int): TaskDTO?

    @GET("/task/detail/{tid}/delay/")
    suspend fun delayTask(@Path("tid") tid: Int): TaskDTO?

    @PUT("/task/detail/{tid}/update/")
    suspend fun changeTask(@Body() request: ChangeTaskRequest, @Path("tid") tid: Int): TaskDTO?

    //Search API
    @GET("/search/{email}")
    suspend fun searchUser(@Path("email") email: String): UserDTO?

    //Follow API
    @POST("/follow/")
    suspend fun followUser(@Body() request: FollowRequest): FollowResponse?
//
//    @POST("/follow/followee/{uid}")
//    suspend fun checkFollow(@Path("uid") uid: Int): CheckFollowResponse
//
//    @GET("/follow/followee/")
//    suspend fun getFolloweeFirstPage(): GetFolloweeListResponse
//
//    @GET("/follow/followee/")
//    suspend fun getFolloweePaged(@Query("page") page: Int): GetFolloweeListResponse
//
//    @GET("/follow/follower/")
//    suspend fun getFollowerFirstPage(): GetFollowerListResponse
//
//    @GET("/follow/follower/")
//    suspend fun getFollowerPaged(@Query("page") page: Int): GetFollowerListResponse



    // Diary API
    // 해당 사용자의 모든 일기 불러오기
    @GET("/diary/mydiary/")
    suspend fun getDiaryList(): GetDiaryListResponse
    // 해당 날짜의 다이어리 불러오기
    @GET("/diary/mydiary/{date}/")
    suspend fun getDateDiary(@Path("date") date: String): GetDateDiaryResponse
    // 해당 날짜에 일기 등록하기
    @POST("/diary/mydiary/{date}/create/")
    suspend fun createDateDiary(@Body() request: CreateDiaryRequest, @Path("date") date: String): DiaryDTO
    // 해당 날짜의 일기 수정하기
    @PATCH("/diary/mydiary/{date}/update/")
    suspend fun updateDateDiary(@Body() request: UpdateDiaryRequest, @Path("date") date: String): DiaryDTO
    // 해당 날짜의 일기 삭제하기
    @DELETE("/diary/mydiary/{date}/update/")
    suspend fun deleteDateDiary(@Path("date") date: String): Response<Unit>
    // 해당 id의 일기 불러오기
    @GET("/diary/watch/{did}/")
    suspend fun getIdDiary(@Path("did") did: Int): DiaryDTO
    // 해당 id의 일기 수정하기
    @PATCH("/diary/watch/{did}/")
    suspend fun updateIdDiary(@Body() request: UpdateDiaryRequest, @Path("did") did: Int): DiaryDTO
    // 해당 id의 일기 삭제하기
    @DELETE("/diary/watch/{did}/")
    suspend fun deleteIdDiary(@Path("did") did: Int): Response<Unit>
}