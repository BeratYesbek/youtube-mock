package beratyesbek.youtube.mock.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDTO<T> {
    List<T> content;
    int totalPages;
    int totalElements;
    int size;
    int page;
    int nextPage;
    int previousPage;
    boolean isNext;
    boolean isPrevious;
    boolean first;
    boolean last;

    public PageDTO(List<T> content, int totalElements, int size, int page) {
        this.content = content;
        this.totalElements = totalElements;
        this.size = size;
        this.page = page;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
        this.isNext = page < totalPages && page + 1 != totalPages;
        this.isPrevious = page > 0;
        this.first = page == 0;
        this.last = page == totalPages - 1;
        this.nextPage = isNext ? page + 1 : page;
        this.previousPage = isPrevious ? page - 1 : page;
    }
}
