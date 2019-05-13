package io.jetpack.ysan.gankio.mvp.presenter

import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.ReadContract
import io.jetpack.ysan.gankio.mvp.model.ReadModel


/**
 * Created by YSAN on 2019-05-13
 */
class ReadPresenter : BasePresenter<ReadContract.View>(), ReadContract.Presenter {

    private val readModel: ReadModel by lazy {
        ReadModel()
    }

    override fun requestCategories() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestSubCategories(category: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestReadData(category: String, page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}