package org.example.jdvback.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Entity
@Table(name = "user")
data class UserBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,
    @Column(name = "user_pseudo")
    var userPseudo: String,
    @Column(name = "user_grid1")
    var userGrid1: String? = null,
    @Column(name = "user_grid2")
    var userGrid2: String? = null,
    @Column(name = "user_grid3")
    var userGrid3: String? = null,
    @Column(name = "user_grid4")
    var userGrid4: String? = null,
    @Column(name = "user_grid5")
    var userGrid5: String? = null,
    @Column(name = "user_gridnumber")
    var userGridnumber: Int = 1
){
    // Constructeur par défaut
    constructor() : this(null, "", null, null, null, null, null, 1)
}

@Repository
interface UserRepository : JpaRepository<UserBean, Long> {
    fun findByUserPseudo(pseudo:String):UserBean?
    fun findByid(id:Long):UserBean
}
@Service
class UserService(val userRep:UserRepository) {
    fun createUser(pseudo:String){
        if (pseudo.isBlank()){
            throw Exception("le pseudo ne peut etre nul ou vide")
        }else{
            val checkPseudo=userRep.findByUserPseudo(pseudo)
            if (checkPseudo!=null){
                throw Exception("Ce pseudo existe déjà")
            } else{
                val user=UserBean(userPseudo = pseudo)
                userRep.save(user)
            }
        }
    }
    fun loadUser(pseudo:String):UserBean{
        if (pseudo.isBlank()){
            throw Exception("le pseudo ne peut etre nul ou vide")
        }else{
            val user=userRep.findByUserPseudo(pseudo)
            if (user==null){
                throw Exception("Ce pseudo n'existe pas en base de données")
            } else{
                return user
            }
        }
    }
    fun addGrid(id:Long,grid:String){
        val user=userRep.findByid(id)
        if (user.userGrid1==null){
            user.userGrid1=grid
        }else if(user.userGrid2==null){
            user.userGrid2=grid
        }else if(user.userGrid3==null){
            user.userGrid3=grid
        }else if(user.userGrid4==null){
            user.userGrid4=grid
        }else if(user.userGrid5==null){
            user.userGrid5=grid
        }else {
            if(user.userGridnumber==1){
                user.userGrid1=grid
                user.userGridnumber++
            }else if (user.userGridnumber==2){
                user.userGrid2=grid
                user.userGridnumber++
            }else if (user.userGridnumber==3){
                user.userGrid3=grid
                user.userGridnumber++
            }else if (user.userGridnumber==4){
                user.userGrid4=grid
                user.userGridnumber++
            }else if (user.userGridnumber==5){
                user.userGrid5=grid
                user.userGridnumber=1
            }
        }
        userRep.save(user)
    }
    fun deleteGrid(id: Long,gridNumber: Int){
        val user=userRep.findByid(id)
        if (gridNumber==1){
            user.userGrid1=null
        }else if(gridNumber==2){
            user.userGrid2=null
        }else if(gridNumber==3){
            user.userGrid3=null
        }else if(gridNumber==4){
            user.userGrid4=null
        }else if(gridNumber==5){
            user.userGrid5=null
        }
        userRep.save(user)
    }
}
