package com.project.libraryms.dto.authorDto;

import com.project.libraryms.dto.authorDto.BaseRequestAuthor;

public class CreateRequestAuthorDto extends BaseRequestAuthor {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
