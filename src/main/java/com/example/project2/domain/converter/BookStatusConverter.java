package com.example.project2.domain.converter;

import com.example.project2.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // autoApply: 해당 entity가 필드에 있으면 그게 저절로 convert가됨(따라서 @convert 생략가능)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {


    // 꼭 정방향 역방향 두개다 구현해야됨
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
