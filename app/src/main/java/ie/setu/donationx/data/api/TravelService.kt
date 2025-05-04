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
    @GET(ServiceEndPoints.TRAVELS_ENDPOINT)
    suspend fun getall(): Response<List<TravelModel>>

    @GET(ServiceEndPoints.TRAVELS_ENDPOINT + "/{id}")
    suspend fun get(@Path("id") id: String): Response<List<TravelModel>>

    @DELETE(ServiceEndPoints.TRAVELS_ENDPOINT + "/{id}")
    suspend fun delete(@Path("id") id: String): travelWrapper

    @POST(ServiceEndPoints.TRAVELS_ENDPOINT)
    suspend fun post(@Body donation: TravelModel): travelWrapper

    @PUT(ServiceEndPoints.TRAVELS_ENDPOINT + "/{id}")
    suspend fun put(@Path("id") id: String,
                    @Body donation: TravelModel
    ): travelWrapper
}
