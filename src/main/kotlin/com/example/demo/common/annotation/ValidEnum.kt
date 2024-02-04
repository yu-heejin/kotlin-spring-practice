package com.example.demo.common.annotation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ValidEnumValidator::class])
annotation class ValidEnum(
    val message: String = "Invalid enum value",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val enumClass: KClass<out Enum<*>>
)

class ValidEnumValidator : ConstraintValidator<ValidEnum, Any> {
    private lateinit var enumValues: Array<out Enum<*>>

    override fun initialize(annotation: ValidEnum) {
        enumValues = annotation.enumClass.java.enumConstants
    }

    // 사용자로부터 받은 값(value)을 검증
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true

        // any: 뒤에 있는 조건이 하나라도 true인 경우 true를 반환한다.
        return enumValues.any { it.name == value.toString() }
    }
}