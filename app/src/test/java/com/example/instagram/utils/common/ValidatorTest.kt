package com.example.instagram.utils.common

import com.example.instagram.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Test

class ValidatorTest {

    @Test
    fun givenValidEmailAndPassword_whenValidate_shouldReturnSuccess() {
        val email = "test@masum.com"
        val password = "password"
        val validation = Validator.validateLoginFields(email, password)
        assertThat(validation, hasSize(2))
        assertThat(
            validation,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenValidFullNameAndEmailAndPassword_whenValidate_shouldReturnSuccess() {
        val fullName = "Masum Modi"
        val email = "test@masum.com"
        val password = "password"
        val validation = Validator.validateSignupFields(fullName, email, password)
        assertThat(validation, hasSize(3))
        assertThat(
            validation,
            contains(
                Validation(Validation.Field.FULLNAME, Resource.success()),
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenInvalidEmailAndValidPassword_whenValidate_shouldReturnEmailError() {
        val email = "test.com"
        val password = "password"
        val validation = Validator.validateLoginFields(email, password)
        assertThat(validation, hasSize(2))
        assertThat(
            validation,
            contains(
                Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

}