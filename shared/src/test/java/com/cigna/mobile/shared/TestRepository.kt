package com.cigna.mobile.shared

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TestRepository {
    suspend fun getTrees() : Result<List<Tree>>
}

class TestRepositoryImpl(private val api: TreeApi,
                         private val db: TreeDao) : TestRepository {
    override suspend fun getTrees(): Result<List<Tree>> {
        return fullServiceCall(
            dbCall = { db.getTrees() },
            apiCall = {
                api.getTreesFromWeb("www.faketrees.com").await()
            },
            networkCallSuccess = { it?.let { networkData -> db.addTrees(networkData) }}
        )
    }
}

interface TreeApi {
    @GET
    fun getTreesFromWeb(@Url url: String): Deferred<Response<Tree>>
}

class TreeDao {
    private val fakeDB = HashMap<String, Any?>()

    fun addTrees(vararg trees: Tree){
        trees.forEach { tree -> fakeDB[TREE_KEY] = tree }
    }

    fun getTrees() : List<Tree>?{
        return fakeDB[TREE_KEY] as List<Tree>
    }

    companion object{
        const val TREE_KEY = "tree_key"
    }
}

data class Tree(val numLimbs: Int, val numOfLeaves: Int)