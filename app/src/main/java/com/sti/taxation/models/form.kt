package com.sti.taxation.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class form (
    var area: String? = "",
    var assessed: String? = "",
    var barangay: String? = "",
    var classification: String? = "",
    var date: String? = "",
    var location: String? = "",
    var market: String? = "",
    var owner: String? = "",
    var street: String? = "",
    var type: String? = ""
)