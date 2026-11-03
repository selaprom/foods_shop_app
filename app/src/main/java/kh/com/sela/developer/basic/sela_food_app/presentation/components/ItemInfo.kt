package kh.com.sela.developer.basic.sela_food_app.presentation.components

data class ItemInfo(val number:Int,val title: String,val subtitle: String)

val itemInfo = listOf<ItemInfo>(
    ItemInfo(12,"Completed","Orders"),
    ItemInfo(number = 4,title = "Active",subtitle = "Coupons"),
    ItemInfo(number = 1250,title = "ShopVibe",subtitle = "Points"),


)