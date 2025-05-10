package ie.setu.donationx.data.api

import ie.setu.donationx.data.TravelModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT


interface TravelService {
    @GET(ServiceEndPoints.TRAVELS_ENDPOINT+ "/{email}")
    suspend fun getall(@Path("email") email: String): Response<List<TravelModel>>

    @GET(ServiceEndPoints.TRAVELS_ENDPOINT+ "/{email}" + "/{id}")
    suspend fun get(@Path("email") email: String,
                    @Path("id") id: String): Response<List<TravelModel>>

    @DELETE(ServiceEndPoints.TRAVELS_ENDPOINT+ "/{email}" + "/{id}")
    suspend fun delete(@Path("email") email: String,
                       @Path("id") id: String): travelWrapper

    @POST(ServiceEndPoints.TRAVELS_ENDPOINT+ "/{email}")
    suspend fun post(@Path("email") email: String,
                     @Body review: TravelModel): travelWrapper

    @PUT(ServiceEndPoints.TRAVELS_ENDPOINT+ "/{email}" + "/{id}")
    suspend fun put(@Path("email") email: String,
                    @Path("id") id: String,
                    @Body review: TravelModel
    ): travelWrapper
}
