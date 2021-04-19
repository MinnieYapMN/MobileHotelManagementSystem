package com.example.mobilehotelmanagementsystem

class Guest {
    var GName = ""
    var GPhone = ""
    var ERoom:Int = 0
    var DRoom:Int = 0
    var CheckInDate = ""
    var CheckOutDate = ""
    var NoOfDay:Int = 0
    var NoOfAdult = ""
    var NoOfChild = ""
    var ERM: Int =0
    var DRM: Int =0
    var total:Int = 0
    var RoomNo = ""

    constructor(GName:String, GPhone:String, ERoom:Int, DRoom:Int, CheckInDate:String,CheckOutDate:String, NoOfDay: Int, NoOfAdult:String, NoOfChild:String, ERM:Int, DRM:Int, total:Int, RoomNo:String){

        this.GName = GName
        this.GPhone = GPhone
        this.ERoom = ERoom
        this.DRoom = DRoom
        this.CheckInDate = CheckInDate
        this.CheckOutDate = CheckOutDate
        this.NoOfDay = NoOfDay
        this.NoOfAdult = NoOfAdult
        this.NoOfChild = NoOfChild
        this.ERM = ERM
        this.DRM= DRM
        this.total = total
        this.RoomNo =RoomNo

    }
}