package com.example.to_do

import java.io.Serializable

class Event : Serializable{

    var name: String? = ""
    var date: String? = ""
    var date2: String = ""
    var hour: String? = ""
    var description: String? = ""
    var priority: String? = ""
    var done: Boolean = false
    var lastPosition: Int = 0
    var type: String = ""

        get() = field
        set(value) {
            field = value
        }
}