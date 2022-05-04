package br.com.erudio.integrationtests.controller.withyml

import br.com.erudio.integrationtests.TestConfigs
import br.com.erudio.integrationtests.controller.withyml.mapper.YMLMapper
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest
import br.com.erudio.integrationtests.vo.AccountCredentialsVO
import br.com.erudio.integrationtests.vo.BookVO
import br.com.erudio.integrationtests.vo.TokenVO
import br.com.erudio.integrationtests.vo.wrappers.WrapperBookVO
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.EncoderConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerYamlTest : AbstractIntegrationTest() {

    private lateinit var specification: RequestSpecification
    private lateinit var objectMapper: YMLMapper
    private lateinit var book: BookVO

    @BeforeAll
    fun setup() {
        objectMapper = YMLMapper()
        book = BookVO()
    }

    @Test
    @Order(1)
    fun authorization() {
        val user = AccountCredentialsVO()
        user.username = "leandro"
        user.password = "admin123"
        val token = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .basePath("/auth/signin")
            .port(TestConfigs.SERVER_PORT)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .body(user, objectMapper)
            .`when`()
            .post()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(TokenVO::class.java, objectMapper)
            .accessToken
        specification = RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer $token")
            .setBasePath("/api/book/v1")
            .setPort(TestConfigs.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()
    }

    @Test
    @Order(2)
    @Throws(JsonMappingException::class, JsonProcessingException::class)
    fun testCreate() {
        mockBook()
        book = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .body(book, objectMapper)
            .`when`()
            .post()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(BookVO::class.java, objectMapper)
        assertNotNull(book.id)
        assertNotNull(book.title)
        assertNotNull(book.author)
        assertNotNull(book.price)
        assertTrue(book.id > 0)
        assertEquals("Docker Deep Dive", book.title)
        assertEquals("Nigel Poulton", book.author)
        assertEquals(55.99, book.price)
    }

    @Test
    @Order(3)
    @Throws(JsonMappingException::class, JsonProcessingException::class)
    fun testUpdate() {
        book.title = "Docker Deep Dive - Updated"
        val bookUpdated: BookVO = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .body(book, objectMapper)
            .`when`()
            .put()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(BookVO::class.java, objectMapper)
        assertNotNull(bookUpdated.id)
        assertNotNull(bookUpdated.title)
        assertNotNull(bookUpdated.author)
        assertNotNull(bookUpdated.price)
        assertEquals(bookUpdated.id, book.id)
        assertEquals("Docker Deep Dive - Updated", bookUpdated.title)
        assertEquals("Nigel Poulton", bookUpdated.author)
        assertEquals(55.99, bookUpdated.price)
    }

    @Test
    @Order(4)
    @Throws(JsonMappingException::class, JsonProcessingException::class)
    fun testFindById() {
        val foundBook = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .pathParam("id", book.id)
            .`when`()
            .get("{id}")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(BookVO::class.java, objectMapper)

        assertNotNull(foundBook.id)
        assertNotNull(foundBook.title)
        assertNotNull(foundBook.author)
        assertNotNull(foundBook.price)
        assertEquals(foundBook.id, book.id)
        assertEquals("Docker Deep Dive - Updated", foundBook.title)
        assertEquals("Nigel Poulton", foundBook.author)
        assertEquals(55.99, foundBook.price)
    }

    @Test
    @Order(5)
    fun testDelete() {
        given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .pathParam("id", book.id)
            .`when`()
            .delete("{id}")
            .then()
            .statusCode(204)
    }

    @Test
    @Order(6)
    @Throws(JsonMappingException::class, JsonProcessingException::class)
    fun testFindAll() {
        val wrapper = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .queryParams(
                "page", 0,
                "size", 12,
                "direction", "asc")
            .`when`()
            .get()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(WrapperBookVO::class.java, objectMapper)

        val books = wrapper.embedded!!.books

        val foundBookOne = books?.get(0)

        assertNotNull(foundBookOne!!.id)
        assertNotNull(foundBookOne.title)
        assertNotNull(foundBookOne.author)
        assertNotNull(foundBookOne.price)
        assertTrue(foundBookOne.id > 0)
        assertEquals("Big Data: como extrair volume, variedade, velocidade e valor da avalanche de informação cotidiana", foundBookOne.title)
        assertEquals("Viktor Mayer-Schonberger e Kenneth Kukier", foundBookOne.author)
        assertEquals(54.00, foundBookOne.price)

        val foundBookFive: BookVO = books[4]
        assertNotNull(foundBookFive.id)
        assertNotNull(foundBookFive.title)
        assertNotNull(foundBookFive.author)
        assertNotNull(foundBookFive.price)
        assertTrue(foundBookFive.id > 0)
        assertEquals("Domain Driven Design", foundBookFive.title)
        assertEquals("Eric Evans", foundBookFive.author)
        assertEquals(92.0, foundBookFive.price)
    }

    @Test
    @Order(7)
    fun testHATEOAS() {
        val content = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_YML)
            .queryParams(
                "page", 0,
                "size", 12,
                "direction", "asc")
            .`when`()
            .get()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()

        assertTrue(content.contains(""""_links":{"self":{"href":"http://localhost:8888/api/book/v1/12"}}}"""))
        assertTrue(content.contains(""""_links":{"self":{"href":"http://localhost:8888/api/book/v1/3"}}}"""))
        assertTrue(content.contains(""""_links":{"self":{"href":"http://localhost:8888/api/book/v1/5"}}}"""))

        assertTrue(content.contains(""""first":{"href":"http://localhost:8888/api/book/v1?direction=asc&page=0&size=12&sort=title,asc"}"""))
        assertTrue(content.contains(""""self":{"href":"http://localhost:8888/api/book/v1?direction=asc&page=0&size=12&sort=title,asc"}"""))
        assertTrue(content.contains(""""next":{"href":"http://localhost:8888/api/book/v1?direction=asc&page=1&size=12&sort=title,asc"}"""))
        assertTrue(content.contains(""""last":{"href":"http://localhost:8888/api/book/v1?direction=asc&page=1&size=12&sort=title,asc"}"""))
    }

    private fun mockBook() {
        book.title = "Docker Deep Dive"
        book.author = "Nigel Poulton"
        book.price = 55.99
        book.launchDate = Date()
    }
}