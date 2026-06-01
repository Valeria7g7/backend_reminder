package com.valeria.backend.response;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

public class PaginationMapper {

    public static <T> PaginatedResponse<T> map(
            Page<T> page,
            HttpServletRequest request
    ) {

        PaginatedResponse<T> response = new PaginatedResponse<>();

        response.setData(
                page.getContent()
        );

        String path =request.getRequestURL().toString();

        Meta meta = new Meta();

        meta.setCurrent_page(
                page.getNumber() + 1
        );

        meta.setPer_page(
                page.getSize()
        );

        meta.setTotal(
                page.getTotalElements()
        );

        meta.setLast_page(
                page.getTotalPages()
        );

        meta.setPath(path);

        int from =
                page.getNumber() *
                page.getSize() + 1;

        int to =
                from +
                page.getNumberOfElements() - 1;

        meta.setFrom(from);

        meta.setTo(to);

        response.setMeta(meta);

        Links links = new Links();

        links.setFirst(
                path + "?page=0"
        );

        links.setLast(
                path + "?page=" +
                (page.getTotalPages() - 1)
        );

        if (page.hasNext()) {

            links.setNext(
                    path + "?page=" +
                    (page.getNumber() + 1)
            );
        }

        if (page.hasPrevious()) {

            links.setPrev(
                    path + "?page=" +
                    (page.getNumber() - 1)
            );
        }

        response.setLinks(links);

        return response;
    }
}