package com.example.mobilehotelmanagementsystem

class Guest {
    var GName = ""
    var GPhone = ""
    var ERoom:Int = 0
    var DRoom:Int = 0
    var CheckInDate = ""
    var NoOfAdult = ""
    var NoOfChild = ""
    var ERM: Int =0
    var DRM: Int =0
    var total:Int = 0

    constructor(GName:String, GPhone:String, ERoom:Int, DRoom:Int, CheckInDate:String, NoOfAdult:String, NoOfChild:String, ERM:Int, DRM:Int, total:Int){

        this.GName = GName
        this.GPhone = GPhone
        this.ERoom = ERoom
        this.DRoom = DRoom
        this.CheckInDate = CheckInDate
        this.NoOfAdult = NoOfAdult
        this.NoOfChild = NoOfChild
        this.ERM = ERM
        this.DRM= DRM
        this.total = total

    }
}