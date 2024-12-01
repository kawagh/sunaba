package jp.kawagh.sunaba.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    fun findUserById(id: Int): User?

    fun getUsers(): List<User>

    fun update(user: User)

    fun deleteById(id: Int)
}

data class User(val id: Int, val name: String)