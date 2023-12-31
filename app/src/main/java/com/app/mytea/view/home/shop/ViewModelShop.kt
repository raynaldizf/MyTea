package com.app.mytea.view.home.shop

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.response.DataX
import com.app.mytea.data.model.response.DataXXXX
import com.app.mytea.data.model.response.DataXXXXXXXXXXX
import com.app.mytea.data.model.response.DataXXXXXXXXXXXX
import com.app.mytea.data.model.response.ProductXX
import com.app.mytea.data.model.response.ResponseAddCart
import com.app.mytea.data.model.response.ResponseGetCategory
import com.app.mytea.data.model.response.ResponseGetDetailCategory
import com.app.mytea.data.model.response.ResponseGetDetailProduct
import com.app.mytea.data.model.response.ResponseGetProduct
import com.app.mytea.data.model.response.ResponseSaved
import com.app.mytea.data.network.ApiClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelShop: ViewModel() {
    private val product = MutableLiveData<List<DataX>?>()
    private val detailProduct = MutableLiveData<DataXXXX?>()
    private val addCart = MutableLiveData<ResponseAddCart?>()
    private val addSaved = MutableLiveData<ResponseSaved?>()
    private val categories = MutableLiveData<List<DataXXXXXXXXXXX>?>()
    private val categoriesDetail = MutableLiveData<List<ProductXX>?>()



    fun allLiveDataProduct() : MutableLiveData<List<DataX>?> {
        return product
    }

    fun allLiveDataDetailProduct() : MutableLiveData<DataXXXX?> {
        return detailProduct
    }

    fun allLiveDataAddCart() : MutableLiveData<ResponseAddCart?> {
        return addCart
    }

    fun allLiveDataAddSaved() : MutableLiveData<ResponseSaved?> {
        return addSaved
    }

    fun allLiveDataCategories() : MutableLiveData<List<DataXXXXXXXXXXX>?> {
        return categories
    }

    fun allLiveDataCategoriesDetail(): MutableLiveData<List<ProductXX>?> {
        return categoriesDetail
    }
    fun getProduct(token : String ){
        ApiClient.instance.product("Bearer $token").enqueue(object : Callback<ResponseGetProduct>{
            override fun onResponse(
                call: Call<ResponseGetProduct>,
                response: Response<ResponseGetProduct>
            ) {
                Log.d("TAG", "onResponse: ${response.body()?.data}")

                if (response.isSuccessful){
                    product.postValue(response.body()?.data)
                }else{
                    product.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                product.postValue(null)
            }

        })

    }

    fun getDetailProduct(token : String, id : String){
        ApiClient.instance.detailProduct("Bearer $token", id).enqueue(object : Callback<ResponseGetDetailProduct>{
            override fun onResponse(
                call: Call<ResponseGetDetailProduct>,
                response: Response<ResponseGetDetailProduct>
            ) {
                Log.d("TAG", "onResponse: ${response.body()?.data}")

                if (response.isSuccessful){
                    detailProduct.postValue(response.body()?.data)
                }else{
                    detailProduct.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetDetailProduct>, t: Throwable) {
                detailProduct.postValue(null)
            }


        })

    }

    fun addCart(token : String, product_id : RequestBody, qty : RequestBody) {
        ApiClient.instance.addCart("Bearer $token", product_id, qty)
            .enqueue(object : Callback<ResponseAddCart> {
                override fun onResponse(
                    call: Call<ResponseAddCart>,
                    response: Response<ResponseAddCart>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        addCart.postValue(response.body())
                    } else {
                        addCart.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseAddCart>, t: Throwable) {
                    addCart.postValue(null)
                }
            })
    }

    fun addSaved(token : String, product_id : RequestBody) {
        ApiClient.instance.addSaved("Bearer $token", product_id)
            .enqueue(object : Callback<ResponseSaved> {
                override fun onResponse(
                    call: Call<ResponseSaved>,
                    response: Response<ResponseSaved>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        addSaved.postValue(response.body())
                    } else {
                        addSaved.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseSaved>, t: Throwable) {
                    addSaved.postValue(null)
                }
            })
    }

    fun getCategory(token : String ){
        ApiClient.instance.categories("Bearer $token").enqueue(object : Callback<ResponseGetCategory>{
            override fun onResponse(
                call: Call<ResponseGetCategory>,
                response: Response<ResponseGetCategory>
            ) {
                Log.d("TAG", "onResponse: ${response.body()?.data}")

                if (response.isSuccessful){
                    categories.postValue(response.body()?.data)
                }else{
                    categories.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetCategory>, t: Throwable) {
                categories.postValue(null)
            }

        })

    }

    fun getDetailCategory(token: String, categoryId: String) {
        ApiClient.instance.categoriesDetail("Bearer $token", categoryId)
            .enqueue(object : Callback<ResponseGetDetailCategory> {
                override fun onResponse(
                    call: Call<ResponseGetDetailCategory>,
                    response: Response<ResponseGetDetailCategory>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")
                    if (response.isSuccessful) {
                        categoriesDetail.postValue(response.body()?.data?.products)
                    } else {
                        categoriesDetail.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetDetailCategory>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}", t)
                    categoriesDetail.postValue(null)
                }
            })
    }



    fun searchProduct(query: String): List<DataX>? {
        val allProduct = product.value
        if (allProduct.isNullOrEmpty() || query.isBlank()) {
            return product.value
        }

        val filteredProduct = mutableListOf<DataX>()

        for (product in allProduct) {
            if (product.name!!.contains(query, ignoreCase = true)) {
                filteredProduct.add(product)
            }
        }

        return filteredProduct
    }

}