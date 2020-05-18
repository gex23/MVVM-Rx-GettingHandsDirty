package com.mdjdfd.mvvmhandsdirty.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.mdjdfd.mvvmhandsdirty.R
import com.mdjdfd.mvvmhandsdirty.ScreenState
import com.mdjdfd.mvvmhandsdirty.model.CurrencyModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(LoadItemInteractor())
        )[MainViewModel::class.java]

        mViewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(screenState: ScreenState<MainState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: MainState) {
        hideProgress()
        when (renderState) {
            is MainState.ShowItems -> setItems(renderState.items)
            is MainState.ShowMessage -> showMessage(renderState.message)
        }
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        list.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun setItems(items: List<CurrencyModel>) {
        list.adapter = MainAdapter(items, mViewModel::onItemClicked)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
