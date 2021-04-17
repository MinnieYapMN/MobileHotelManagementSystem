package com.example.mobilehotelmanagementsystem

class Services {
        var roomno = ""
        var housekeep = "Housekeeping"
        var spa = "SPA Services"
        var breakfast = "Breakfast"
        var laundry = "Dry cleaning and laundry"
        var serCharge : Int = 0

        constructor(roomno:String,housekeep:String,spa:String,breakfast:String,laundry:String,serCharge:Int){
            this.roomno = roomno
            this.housekeep = housekeep
            this.spa = spa
            this.breakfast = breakfast
            this.laundry = laundry
            this.serCharge = serCharge
        }


}