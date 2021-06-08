package pl.rafalmiskiewicz.ui.hours

interface ClickType

sealed class ProductDetailsClickType : ClickType {
    object PlusAmount : ProductDetailsClickType()
    object MinusAmount : ProductDetailsClickType()
    object ToggleFavoriteProduct : ProductDetailsClickType()
}

sealed class DumpedClickType : ClickType {
    object PlusAmount : DumpedClickType()
    object MinusAmount : DumpedClickType()
    object ToggleFavoriteProduct : DumpedClickType()
}

sealed class SupplyClickType : ClickType {
    object PlusAmount : SupplyClickType()
    object MinusAmount : SupplyClickType()
    object ToggleFavoriteProduct : SupplyClickType()
}

sealed class FavouriteClickType : ClickType {
    object RemoveFromFavourite : FavouriteClickType()
    object PlusAmount : FavouriteClickType()
    object MinusAmount : FavouriteClickType()
}

sealed class ProductListClick : ClickType {
    object PlusAmount : ProductListClick()
    object MinusAmount : ProductListClick()
    object ToggleFavoriteProduct : ProductListClick()
    object ProductClick : ProductListClick()
}

object CommonClick : ClickType

sealed class ScannerListClick : ClickType {
    object PlusAmount : ScannerListClick()
    object MinusAmount : ScannerListClick()
}


sealed class CartConfirmationClick : ClickType {
    object Select : CartConfirmationClick()
}