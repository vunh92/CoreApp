package com.vunh.coreapp.common

const val DEVICE_TYPE = 1 /* ANDROID */

const val PACKAGE_NAME = "com.vunh.coreapp"
const val PREFERENCE_NAME = "core_preference"
const val KEY_FIRST_BOOT = "first_boot"
const val GRANT_TYPE_OAUTH = "password"
const val GRANT_TYPE_REFRESH = "refresh_token"
const val KEY_OAUTH_TOKEN = "oauth_token"
const val CURRENT_LANGUAGE = "current_language"




const val STOP_FOREGROUND_ACTION = "STOP_FOREGROUND_ACTION"
const val START_FOREGROUND_ACTION = "START_FOREGROUND_ACTION"

annotation class INTENT_KEY {
    companion object {
        var EXTRA_EMAIL = "put_email"
        var OPERATION_TYPE = "put_operation_type"
        var PRODUCT = "put_product"
        var PRODUCT_DETAILS = "put_product_details"
        var EXTRA_ITEM_MENU = "put_item_menu"
        var PRODUCT_SERIALS = "product_serials"
    }
}

annotation class TIME_DELAY {
    companion object {
        var TIME_SPLASH = 20
    }
}

annotation class PAGE {
    companion object {
        var PAGE_START = 1
        var PAGE_SIZE = 100
    }
}

annotation class HTTP_CODE {
    companion object {
        val SUCCESS = "200"
        val CREATE_SUCCESS = "201"
        val AUTHENTICATION_ERROR = "401"
        val NOT_FOUND_ERROR = "404"
        val FORBIDDEN_ERROR = "403"
        val SERVER_ERROR = "500"
    }
}

annotation class FIREBASE {
    companion object {
        val COMMON_ERROR = "com.google.firebase"
        val USER_INVALID = "no user record"
        val PASSWORD_INVALID = "password is invalid"
    }
}

annotation class LANGUAGE {
    companion object {
        val JAPANESE = "ja"
        val VIETNAMESE = "vi"
        val ENGLISH = "en"
    }
}

annotation class OPERATIONS {
    companion object {
        val GOODS_RECEIPT = "goods_receipt"
        val GOODS_ISSUES = "goods_issues"
        val PICKING_LIST = "picking_list"
        val LOCATION_MOVEMENT = "location_movement"
        val STOCKTAKING = "stocktaking"
        val STOCK_MODIFICATION = "stock_modification"
        val DEFECTIVE_GOODS = "defective_goods"
        val OPERATION_RESTRICTION = "operation_restriction"
    }
}

annotation class SETTINGS {
    companion object {
        val SETTINGS_APP = "setting_app"
        val SETTINGS_LANGUAGE = "setting_language"
        val SETTINGS_APP_INFO = "setting_app_info"
    }
}

annotation class MANAGE_SERIAL {
    companion object {
        val YES = "YES"
        val NO = "NO"
    }
}

annotation class TRANSACTION {
    companion object {
        val NEW = "NEW"
        val INPROGESS= "INPROGESS"
        val PRINTED = "PRINTED"
        val COMPLETED = "COMPLETED"
        val CANCELLED = "CANCELLED"
        val VALID = "VALID"
    }
}