package jp.kawagh.sunaba.mapper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.jdbc.Sql

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest(@Autowired private val userMapper: UserMapper) {
    @Nested
    @DisplayName("学習用")
    inner class LearningTest {
        @Test
        @DisplayName("存在しないデータに対してのSELECTはnullを返す")
        fun a() {
            val response = userMapper.findUserById(Int.MIN_VALUE)
            Assertions.assertNull(response)
        }

        @Test
        @Sql("/insert_user.sql")
        @DisplayName("テスト用にレコードを追加してからクエリの結果を確認する")
        fun b() {
            val expected = User(-1, "minus1")
            val response = userMapper.findUserById(-1)
            Assertions.assertEquals(response, expected)
        }
    }
}