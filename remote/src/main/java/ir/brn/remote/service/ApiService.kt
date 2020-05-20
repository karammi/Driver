package ir.brn.remote.service

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.brn.remote.model.*
import ir.brn.remote.model.address.AddressModel
import ir.brn.remote.model.brokerLoad.LoadModel
import ir.brn.remote.model.load.TripModel
import ir.brn.remote.model.product.CategoryModel
import ir.brn.remote.model.product.ProductModel
import ir.brn.remote.model.wall.WallLikeStateModel
import ir.brn.remote.model.wall.WallModel
import retrofit2.http.*

interface ApiService {

    //regionWall

    @GET("walls?type=all")
    fun getWalls(@Header("token") token: String): Observable<ResponseModel<List<WallModel>>>

    @FormUrlEncoded
    @POST("walls")
    fun addWall(@Header("token") token: String,
                @Field("body") body: String?,
                @Field("lat") lat: Double?,
                @Field("lng") lng: Double?,
                @Field("province_id") provinceId: Int?,
                @Field("city_id") cityId: Int?,
                @Field("carrier_type_id") carrierTypeId: Int?,
                @Field("truck_type_id") truckTypeId: Int?,
                @Field("hashtags") hashTags: String?): Observable<ResponseModel<DoneResponseModel>>


    @FormUrlEncoded
    @POST("walls/{wallId}")
    fun editWall(@Header("token") token: String,
                 @Field("_method") method: String = "PATCH",
                 @Path("wallId") wallId: Int,
                 @Field("body") body: String?,
                 @Field("hashtags") hashTags: String?): Observable<ResponseModel<DoneResponseModel>>


    @FormUrlEncoded
    @POST("walls/{wallId}")
    fun deleteWall(@Header("token") token: String,
                   @Field("_method") method: String = "DELETE",
                   @Path("wallId") wallId: Int): Observable<ResponseModel<Nothing>>

    @FormUrlEncoded
    @POST("walls/{wallId}/like")
    fun likeDisLikeWall(@Header("token") token: String,
                        @Path("wallId") wallId: Int,
                        @Field("type") type: String? = "like"): Observable<ResponseModel<WallLikeStateModel>>

    //endregion

    //region product

    @GET("product_categories")
    fun getCategories(@Header("token") token: String): Observable<ResponseModel<List<CategoryModel>>>


    @GET("v2/product_categories/{id}")
    fun getChildCategories(@Header("token") token: String,
                           @Path("id") categoryId: Int?): Observable<ResponseModel<List<CategoryModel>>>


    @GET("products")
    fun getProducts(@Header("token") token: String): Observable<ResponseModel<List<ProductModel>>>


    @GET("products/{id}")
    fun getProductDetail(@Header("token") token: String,
                         @Path("id") id: Int?): Observable<ResponseModel<ProductModel>>

    @GET("product_categories/{id}")
    fun getProductsForSpecificCategory(@Header("token") token: String,
                                       @Path("id") categoryId: Int): Observable<ResponseModel<List<ProductModel>>>

    //endregion


}