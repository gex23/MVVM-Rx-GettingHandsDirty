package com.mdjdfd.mvvmhandsdirty.main

import com.mdjdfd.mvvmhandsdirty.model.CurrencyModel

sealed class MainState {
    class ShowItems(val items: List<CurrencyModel>) : MainState()
    class ShowMessage(val message: String) : MainState()
}