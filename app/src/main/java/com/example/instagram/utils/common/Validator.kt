package com.example.instagram.utils.common

import com.example.instagram.R
import java.util.regex.Pattern

object Validator {

    private val EMAIL_ADDRESS = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private const val MIN_PASSWORD_LENGTH = 6
    private const val MIN_FULLNAME_LENGTH = 2

    fun validateLoginFields(email: String?, password: String?): List<Validation> =
        ArrayList<Validation>().apply {
            when {
                email.isNullOrBlank() ->
                    add(Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_empty)))
                !EMAIL_ADDRESS.matcher(email).matches() ->
                    add(Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)))
                else ->
                    add(Validation(Validation.Field.EMAIL, Resource.success()))
            }
            when {
                password.isNullOrBlank() ->
                    add(Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_empty)))
                password.length < MIN_PASSWORD_LENGTH ->
                    add(Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_small_length)))
                else -> add(Validation(Validation.Field.PASSWORD, Resource.success()))
            }
        }

    fun validateSignupFields(fullName: String?, email: String?, password: String?): List<Validation> =
        ArrayList<Validation>().apply {
            when {
                fullName.isNullOrBlank() ->
                    add(Validation(Validation.Field.FULLNAME, Resource.error(R.string.fullname_field_empty)))
                fullName.length < MIN_FULLNAME_LENGTH ->
                    add(Validation(Validation.Field.FULLNAME, Resource.error(R.string.fullname_field_small_length)))
                else -> add(Validation(Validation.Field.FULLNAME, Resource.success()))
            }
            when {
                email.isNullOrBlank() ->
                    add(Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_empty)))
                !EMAIL_ADDRESS.matcher(email).matches() ->
                    add(Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)))
                else ->
                    add(Validation(Validation.Field.EMAIL, Resource.success()))
            }
            when {
                password.isNullOrBlank() ->
                    add(Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_empty)))
                password.length < MIN_PASSWORD_LENGTH ->
                    add(Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_small_length)))
                else -> add(Validation(Validation.Field.PASSWORD, Resource.success()))
            }
        }
}

data class Validation(val field: Field, val resource: Resource<Int>) {

    enum class Field {
        FULLNAME,
        EMAIL,
        PASSWORD
    }
}
