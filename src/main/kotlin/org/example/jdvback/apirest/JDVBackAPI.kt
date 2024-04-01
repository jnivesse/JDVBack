package org.example.jdvback.apirest

import org.example.jdvback.model.UserBean
import org.example.jdvback.model.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/JDVBack")
class JDVBackAPI(val userService: UserService){
    @GetMapping("/test")
    fun testHello(){
        println("hello la team")
    }
    @PostMapping("/createUser")
    fun createUser(@RequestBody userPseudo: String){
        if (userPseudo.isNullOrBlank()){
            throw Exception("le pseudo ne peut etre nul ou vide")
        }else{
            userService.createUser(userPseudo)
        }
    }
    @PostMapping("/loadUser")
    fun loadUser(@RequestBody userPseudo: String):UserBean{
        if (userPseudo.isNullOrBlank()){
            throw Exception("le pseudo ne peut etre nul ou vide")
        }else{
            return userService.loadUser(userPseudo)
        }
    }
    @PostMapping("/addGrid")
    fun addGrid(@RequestParam userId:Long,@RequestBody grid:String){
        userService.addGrid(userId,grid)
    }
    @PostMapping("/deleteGrid")
    fun deleteGrid(@RequestParam userId:Long,@RequestBody number:Int){
        userService.deleteGrid(userId,number)
    }

}