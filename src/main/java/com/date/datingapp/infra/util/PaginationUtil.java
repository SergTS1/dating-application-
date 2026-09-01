package com.date.datingapp.infra.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UtilityClass
public class PaginationUtil {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 15;

    public static Pageable getPageable(PageParam pageParam) {
        return PageRequest.of(
                resolvePage(pageParam),
                resolveSize(pageParam)
        );
    }

    private static int resolvePage(PageParam pageParam) {
        if (pageParam == null || pageParam.getPage() == null || pageParam.getPage() <= 0) {
            return DEFAULT_PAGE;
        }
        return pageParam.getPage() - 1;
    }

    private static int resolveSize(PageParam pageParam) {
        if (pageParam == null || pageParam.getSize() == null || pageParam.getSize() <= 0) {
            return DEFAULT_PAGE_SIZE;
        }
        return pageParam.getSize();
    }

    public static <T> Page<T> getPageFromList(PageParam pageParam, List<T> list) {
        Pageable pageable = getPageable(pageParam);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        if (start > end) {
            return Page.empty(pageable);
        }
        List<T> pageContent = list.subList(start, end);
        return new PageImpl<>(pageContent, pageable, list.size());
    }
}
